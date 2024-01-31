package s26844_Bank.s26844_Bank.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import s26844_Bank.s26844_Bank.model.Client;
import s26844_Bank.s26844_Bank.repository.ClientRepository;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {
    private static ClientService clientService;
    private static ClientRepository clientRepository;

    @BeforeAll
    static void setUp() {
        clientRepository = new ClientRepository();
        clientService = new ClientService(clientRepository);
    }

    @AfterEach
    void cleanUp() {
        clientRepository.removeAll();
    }

    @Test
    void shouldRegisterClient() {
        Client client = new Client(1234, "Michal", "Tal", 525D, "EUR");

        Client result = assertDoesNotThrow(() -> clientService.register(client));

        assertEquals(client.getName(), result.getName());
        assertEquals(client.getSurname(), result.getSurname());
        assertEquals(client.getCurrency(), result.getCurrency());
        assertNotNull(result.getID());
        assertNotNull(result.getSaldo());
    }

    @Test
    void shouldPrintClientByPesel() {
        Client client = new Client(1234, "Michal", "Tal", 525D, "EUR");

        clientService.register(client);

        Client result = assertDoesNotThrow(() -> clientService.getByPesel(client.getID()));

        assertEquals(client.getID(), result.getID());
    }








}