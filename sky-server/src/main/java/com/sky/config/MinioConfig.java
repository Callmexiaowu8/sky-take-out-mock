package com.sky.config;

import com.sky.properties.MinioProperties;
import com.sky.utils.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MinioConfig {

    @Bean
    @ConditionalOnMissingBean
    public MinioUtil minioUtil(MinioProperties minioProperties) {
        log.info("开始创建Minio实例:{}", minioProperties);
        return new MinioUtil(
                minioProperties.getEndpoint(),  // MinIO 地址
                minioProperties.getAccessKey(),             // accessKey
                minioProperties.getSecretKey(),             // secretKey
                minioProperties.getBucketName()            // bucketName
        );
    }
}