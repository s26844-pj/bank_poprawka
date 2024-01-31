package s26844_Bank.s26844_Bank.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s26844_Bank.s26844_Bank.model.Client;
import s26844_Bank.s26844_Bank.service.ClientService;

@RestController
@RequestMapping("/client")
@AllArgsConstructor

public class ClientController {
    private final ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<Client> create(@RequestBody Client client) {
        Client createdClient = clientService.register(client);

        return ResponseEntity
                .status(HttpStatusCode.valueOf(201))
                .body(createdClient);
    }

    @GetMapping("/client-{pesel}")
    public ResponseEntity<Client> getClientByPesel(@PathVariable Integer ID) {
        Client client = clientService.getByPesel(ID);

        return ResponseEntity.ok(client);
    }
}