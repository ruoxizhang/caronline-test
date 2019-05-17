package com.ruoxi.caronline.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CreateShortURL.class)
public class CreateShortURLTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void test() throws Exception {
//        mvc.perform(post("/shorturl")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"longUrl\":\"shortURL\"}"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("shortURL"));
    }
}