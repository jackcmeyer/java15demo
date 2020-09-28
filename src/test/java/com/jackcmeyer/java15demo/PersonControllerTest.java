package com.jackcmeyer.java15demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturn200WhenPostRequestToPerson() throws Exception {
        String json = "{\n" +
                "    \"name\": \"test1\",\n" +
                "    \"occupation\": \"Software Engineer\"\n" +
                "}";

        mockMvc.perform(
                post("/person").contentType("application/json").content(json))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn400WhenMissingNameFromRequest() throws Exception {
        String json = "{\n" +
                "    \"occupation\": \"Software Engineer\"\n" +
                "}";

        mockMvc.perform(
                post("/person").contentType("application/json").content(json))
                .andExpect(status().isBadRequest());
    }
}
