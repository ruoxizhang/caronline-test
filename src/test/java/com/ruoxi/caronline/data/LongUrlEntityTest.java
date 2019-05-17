package com.ruoxi.caronline.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class LongUrlEntityTest {

    @Autowired
    private JacksonTester<UrlData> json;

    private UrlData longUrl = new UrlData();
    private String result = "{\"longUrl\":\"http://this-is-a-very-very-long-url.com\"}";

    @Before
    public void initialize() {
        longUrl.setLongUrl("http://this-is-a-very-very-long-url.com");
    }

    @Test
    public void serialize() throws IOException {
        assertThat(this.json.write(longUrl)).isEqualToJson(result);
    }

    @Test
    public void deserialize() throws IOException {
        assertThat(this.json.parse(result)).isEqualTo(longUrl);
    }
}