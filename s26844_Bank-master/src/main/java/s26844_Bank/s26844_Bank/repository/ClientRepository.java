package s26844_Bank.s26844_Bank.repository;

import org.springframework.stereotype.Repository;
import s26844_Bank.s26844_Bank.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {

    private List<Client> clientList = new ArrayList<>();

    public Client register(Client client) {
        client.setID(clientList.size());
        clientList.add(client);

        return client;
    }

    public Optional<Client> getByPesel(Integer ID) {
        return clientList.stream()
                .filter(client -> client.getID().equals(ID))
                .findFirst();
    }

    public List<Client> findAboveBalance(double balance) {
        return clientList.stream().filter(account -> account.getSaldo() > balance).toList();
    }



    public List<Client> getAll() {
        return clientList;
    }

    public void removeAll() {
        clientList = new ArrayList<>();
    }


}

