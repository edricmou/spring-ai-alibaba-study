package com.edricmou.config;

import cn.hutool.crypto.SecureUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

@Configuration
public class InitVectorDatabaseConfig {


    @Autowired
    private VectorStore vectorStore;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Value("classpath:ops.txt")
    private Resource opsfile;

    private final String RAG_KEY = "RAG:";

    @PostConstruct
    public void init() {
        // 将数据导入到rag向量数据库

        // 读取文件
        TextReader textReader = new TextReader(opsfile);
        textReader.setCharset(Charset.defaultCharset());

        // 文件转为向量
        List<Document> list = new TokenTextSplitter().transform(textReader.read());

        String filename = (String) textReader.getCustomMetadata().get(TextReader.SOURCE_METADATA);

        // 获取文件名的md5
        String fileNameMd5 = SecureUtil.md5(filename);

        // 如果设置成功，如果数据库没有存过rag知识库
        // 如果设置失败，说明rag数据库已存在redis中
        Boolean setFlag = redisTemplate.opsForValue().setIfAbsent(RAG_KEY + fileNameMd5, "1");

        // 存入rag知识库
        if (Objects.equals(setFlag, Boolean.TRUE)) {
            vectorStore.add(list);
        }
    }
}
