package s26844_Bank.s26844_Bank.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import s26844_Bank.s26844_Bank.exception.ValidationException;
import s26844_Bank.s26844_Bank.model.Client;
import s26844_Bank.s26844_Bank.repository.ClientRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client register(Client client) {

        if (client.getName() == null || client.getName().isEmpty()) {
            throw new ValidationException("Name cannot be empty");
        }
        if (client.getSurname() == null || client.getSurname().isEmpty()) {
            throw new ValidationException("Surname cannot be empty");
        }
        if (client.getSaldo() == null || client.getSaldo() < 0) {
            throw new ValidationException("Wrong ammount");
        }



        return clientRepository.register(client);
    }

    public Client getByPesel(Integer ID) {
        return clientRepository.getByPesel(ID)
                .orElseThrow(() -> new ValidationException("WRONG " + ID));
    }



    public List<Client> getMoreThanBalance(double balance) {
        List<Client> clientsAboveBalance = clientRepository.findAboveBalance(balance);
        if (!clientsAboveBalance.isEmpty()) {
            return clientsAboveBalance;
        } else {
            throw new ValidationException("Empty List");
        }
    }


}

