package com.sky.test;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpClientTest {

    // 测试用例，验证GET请求的处理
    @Test
    public void testGET() throws Exception {

        // 创建HttpClient实例，用于发送HTTP请求
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建HttpGet实例，指定请求的URL
        HttpGet httpGet = new HttpGet("http://localhost:8081/user/shop/status");

        // 执行GET请求并获取响应
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 日志记录，输出服务端返回的响应码
        log.info("服务端返回的响应码：{}", response.getStatusLine()
                                                  .getStatusCode());

        // 获取响应实体
        HttpEntity entity = response.getEntity();
        // 将响应体转换为字符串形式
        String body = EntityUtils.toString(entity);
        // 日志记录，输出服务端返回的响应体
        log.info("服务端返回的响应体：{}", body);

        // 关闭响应，释放资源
        response.close();
        // 关闭HttpClient实例，释放资源
        httpClient.close();
    }


    // 测试用例，验证POST请求的功能
    @Test
    public void testPOST() throws Exception {
        // 创建HttpClient实例，用于发送HTTP请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建HttpPost实例，指定请求的URL
        HttpPost httpPost = new HttpPost("http://localhost:8081/admin/employee/login");

        // 创建一个JSONObject对象，用于封装请求参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "admin");
        jsonObject.put("password", "123456");

        // 创建一个StringEntity对象，将JSONObject对象转换为JSON字符串，并设置字符集为UTF-8
        StringEntity entity = new StringEntity(jsonObject.toJSONString(), "UTF-8");
        // 设置Content-Type为application/json，表示发送的数据类型为JSON
        entity.setContentType("application/json");
        // 将请求实体设置到HttpPost对象中
        httpPost.setEntity(entity);

        // 使用HttpClient执行HttpPost请求，获得响应对象
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 日志记录，输出服务端返回的响应码
        log.info("服务端返回的响应码：{}", response.getStatusLine()
                                                      .getStatusCode());

        // 获取响应实体
        HttpEntity responseEntity = response.getEntity();
        // 将响应体转换为字符串形式
        String body = EntityUtils.toString(responseEntity);
        // 日志记录，输出服务端返回的响应体
        log.info("服务端返回的响应体：{}", body);

        // 关闭响应，释放资源
        response.close();
        // 关闭HttpClient实例，释放资源
        httpClient.close();

    }
}
