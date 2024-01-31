package s26844_Bank.s26844_Bank.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import s26844_Bank.s26844_Bank.model.Client;
import s26844_Bank.s26844_Bank.service.ClientService;
import static org.mockito.Mockito.when;
import java.util.Optional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClientControllerUnitTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void create() {

        Client client = new Client(0, "John", "Doe", 1000.0, "USD");

        when(clientService.register(client)).thenReturn(client);


        Client createdClient = clientController.create(client).getBody();


        verify(clientService, times(1)).register(client);


        assertEquals(client.getName(), createdClient.getName());
        assertEquals(client.getSurname(), createdClient.getSurname());
        assertEquals(client.getSaldo(), createdClient.getSaldo());
        assertEquals(client.getCurrency(), createdClient.getCurrency());
    }

    @Test
    void getClientByPesel() {

        Integer clientId = 0;
        Client client = new Client(0, "John", "Doe", 1000.0, "USD");


        Client retrievedClient = clientController.getClientByPesel(clientId).getBody();


        verify(clientService, times(1)).getByPesel(clientId);


        assertEquals(client.getName(), retrievedClient.getName());
        assertEquals(client.getSurname(), retrievedClient.getSurname());
        assertEquals(client.getSaldo(), retrievedClient.getSaldo());
        assertEquals(client.getCurrency(), retrievedClient.getCurrency());
    }
}