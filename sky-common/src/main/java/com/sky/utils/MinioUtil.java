package com.sky.utils;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;

@Data
@AllArgsConstructor
@Slf4j
public class MinioUtil {

    private String endpoint;         // 如 http://localhost:9000
    private String accessKey;        // minio用户名
    private String secretKey;        // minio密码
    private String bucketName;       // 如 images

    /**
     * 上传文件到 MinIO
     *
     * @param bytes 文件二进制内容
     * @param objectName 存储对象名（如："image/2025-05/test.jpg"）
     * @return 可访问的文件 URL
     */
    public String upload(byte[] bytes, String objectName) {

        try {
            // 初始化 MinIO 客户端
            MinioClient minioClient = MinioClient.builder()
                                                 .endpoint(endpoint)
                                                 .credentials(accessKey, secretKey)
                                                 .build();

            // 检查桶是否存在
            boolean bucketExists = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(bucketName).build()
            );

            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                log.info("Bucket 不存在，已创建：{}", bucketName);
            }

            // 上传文件
            minioClient.putObject(
                    PutObjectArgs.builder()
                                 .bucket(bucketName)
                                 .object(objectName)
                                 .stream(new ByteArrayInputStream(bytes), bytes.length, -1)
                                 .contentType("application/octet-stream") // 可根据文件类型调整
                                 .build()
            );

            // 构建访问地址
            String fileUrl = String.format("%s/%s/%s", endpoint, bucketName, objectName);
            log.info("文件上传到: {}", fileUrl);
            return fileUrl;

        } catch (Exception e) {
            log.error("上传文件到 MinIO 失败", e);
            throw new RuntimeException("MinIO 文件上传失败：" + e.getMessage());
        }
    }
}