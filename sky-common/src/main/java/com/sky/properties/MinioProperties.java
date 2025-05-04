package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sky.minio")
@Data
public class MinioProperties {

    private String endpoint;         // 如 http://localhost:9000
    private String accessKey;        // minio用户名
    private String secretKey;        // minio密码
    private String bucketName;       // 如 images


}
