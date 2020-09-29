package com.jackcmeyer.java15demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturn200WhenPostRequestToPerson() throws Exception {
        String json = """
                {
                    "name": "test1",
                    "occupation": "Software Engineer"
                }
                """;

        mockMvc.perform(
                post("/person").contentType("application/json").content(json))
                .andExpect(status().isCreated());
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

    @Test
    void shouldRetrieveAPersonById() throws Exception {
        String json = """
                {
                    "name": "test1",
                    "occupation": "Software Engineer"
                }
                """;

        MvcResult mvcResult = mockMvc.perform(
                post("/person").contentType("application/json").content(json))
                .andExpect(status().isCreated())
                .andReturn();
        String location = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(
                get(location).accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andReturn();
    }
}
