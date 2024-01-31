package s26844_Bank.s26844_Bank.controller;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import s26844_Bank.s26844_Bank.model.Client;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create() throws Exception {

        Client client = new Client(1, "John", "Doe", 1000.0, "USD");


        String clientJson = objectMapper.writeValueAsString(client);


        MvcResult mvcResult = mockMvc.perform(post("/client/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();


        String content = mvcResult.getResponse().getContentAsString();
        Client createdClient = objectMapper.readValue(content, Client.class);

        assertNotNull(createdClient);
        assertNotNull(createdClient.getID());
        assertEquals(client.getName(), createdClient.getName());
        assertEquals(client.getSurname(), createdClient.getSurname());
        assertEquals(client.getSaldo(), createdClient.getSaldo());
        assertEquals(client.getCurrency(), createdClient.getCurrency());
    }

    @Test
    void getClientByPesel() throws Exception {

        Integer clientId = 1;


        MvcResult mvcResult = mockMvc.perform(get("/client/client-" + clientId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();


        String content = mvcResult.getResponse().getContentAsString();
        Client retrievedClient = objectMapper.readValue(content, Client.class);

        assertNotNull(retrievedClient);
        assertEquals(clientId, retrievedClient.getID());
    }
}