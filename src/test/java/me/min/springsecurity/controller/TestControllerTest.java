package me.min.springsecurity.controller;

import com.sun.net.httpserver.Headers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void helloTest() {
        var actual = testRestTemplate
                .exchange(
                        "/hello/min",
                        HttpMethod.GET,
                        new HttpEntity<>(new Headers()),
                        String.class);

        assertThat(actual.getBody()).isNotEqualTo("hello min");
    }

    @Test
    void helloAuthTest() {
        var httpHeaders = new HttpHeaders() {{
            add("Authorization", "Basic bWluOjEyMzQ1");
        }};
        var httpEntity = new HttpEntity<>(httpHeaders);

        var actual = testRestTemplate
                .exchange(
                        "/hello/min",
                        HttpMethod.GET,
                        httpEntity,
                        String.class);

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getBody()).isEqualTo("hello min");
    }
}