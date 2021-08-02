package com.freemarket.seller.sellerapi.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SellerControllerV1EndToEndTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenAValidatedPostRequestMustReturnTheOutputWith201() throws Exception {
        String json = "{\n" +
                "  \"userName\": \"sellername\",\n" +
                "  \"storeName\": \"storename\",\n" +
                "  \"password\": \"my-secret-password\",\n" +
                "  \"email\": \"seller@mail.com\",\n" +
                "  \"phoneNumber\": \"55526256\",\n" +
                "  \"sellerPlan\": \"INDIVIDUAL\"\n" +
                "}";

        mockMvc.perform(post("/v1/sellers")
                .content(json).contentType("application/hal+json"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.userName").value("sellername"))
                .andExpect(jsonPath("$.storeName").value("storename"))
                .andExpect(jsonPath("$.email").value("seller@mail.com"))
                .andExpect(jsonPath("$.phoneNumber").value("55526256"))
                .andExpect(jsonPath("$.sellerPlan").value("INDIVIDUAL"));

    }

}