package pl.wsb;

import com.capgemini.programowanie.obiektowe.ClientNotFoundException;
import com.capgemini.programowanie.obiektowe.Clients;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ClientService implements Clients {
    private Map<String, Client> db;
    private int lastId;

    public ClientService() {
        this.db = new HashMap<>();
        this.lastId = 0;
    }

    private String generateNextId() {
        this.lastId += 1;
        return String.valueOf(this.lastId);
    }

    @Override
    public String createNewClient(String firstName, String lastName) {
        Client client = new Client();
        client.setId(this.generateNextId());
        client.setFirstname(firstName);
        client.setLastname(lastName);
        client.setStatus(ClientStatus.STANDARD);
        client.setCreatedAt(LocalDate.now());

        this.db.put(client.getId(), client);
        return client.getId();
    }

    @Override
    public String activatePremiumAccount(String clientId) {
        Client client = this.findClientById(clientId);
        client.setStatus(ClientStatus.PREMIUM);
        return client.getId();
    }

    @Override
    public String getClientFullName(String clientId) {
        Client client = this.findClientById(clientId);
        return client.getFirstname() + " " + client.getLastname();
    }

    @Override
    public LocalDate getClientCreationDate(String clientId) {
        Client client = this.findClientById(clientId);
        return client.getCreatedAt();
    }

    @Override
    public boolean isPremiumClient(String clientId) {
        Client client = this.findClientById(clientId);

        if(client.getStatus() == ClientStatus.PREMIUM){
            return true;
        }

        return false;
    }

    @Override
    public int getNumberOfClients() {
        return this.db.size();
    }

    @Override
    public int getNumberOfPremiumClients() {
         return (int) this.db.values()
                .stream()
                .filter(client -> client.getStatus() == ClientStatus.PREMIUM)
                .count();
    }

    private Client findClientById(String clientId){
        Client client = this.db.get(clientId);

        if(client == null){
            throw new ClientNotFoundException();
        }

        return client;
    }
}
