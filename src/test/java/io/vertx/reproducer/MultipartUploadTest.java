package io.vertx.reproducer;

import io.vertx.core.Vertx;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * A test to upload multipart form with three attachments.
 */
public class MultipartUploadTest {

    private Vertx vertx;
    private CloseableHttpClient client;

    @Before
    public void setUp() throws InterruptedException {
        vertx = Vertx.vertx();
        CountDownLatch latch = new CountDownLatch(1);
        vertx.deployVerticle(Server.class.getName(), completion -> {
            latch.countDown();
        });
        latch.await();
    }

    @After
    public void tearDown() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        vertx.close(completion -> latch.countDown());
        latch.await();
    }

    @Test
    public void test() throws Exception {

        // do several uploads.
        // The first one generally works okay,
        // but one of these is likely to fail...

        doUpload();

        doUpload();

        doUpload();

        doUpload();

        doUpload();

        doUpload();
    }

    private void doUpload() {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost post = new HttpPost("http://127.0.0.1:8080/post");

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        ClassLoader classLoader = getClass().getClassLoader();

        try {

            builder.addBinaryBody("one", IOUtils.toByteArray(classLoader.getResourceAsStream("file1.txt")), ContentType.APPLICATION_JSON, "myXml.file");
            builder.addBinaryBody("two", IOUtils.toByteArray(classLoader.getResourceAsStream("file2.xml")), ContentType.APPLICATION_XML, "myXml.file");
            builder.addBinaryBody("three", IOUtils.toByteArray(classLoader.getResourceAsStream("file3.xml")), ContentType.APPLICATION_XML, "myXml.file");

            HttpEntity multipart = builder.build();
            post.setEntity(multipart);

            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity responseEntity = response.getEntity();

            assertThat(IOUtils.toString(responseEntity.getContent(), "UTF-8"), is("OK"));

        } catch (IOException e) {
        }
    }
}
