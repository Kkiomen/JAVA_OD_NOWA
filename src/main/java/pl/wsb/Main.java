package pl.wsb;

public class Main {
    public static void main(String[] args) {

        ClientService clientService = new ClientService();
        String clientId = clientService.createNewClient("Jan", "Kowalski");
        String clientId2 = clientService.createNewClient("Anna", "Lizak");

        clientService.activatePremiumAccount(clientId);
        clientService.activatePremiumAccount(clientId2);



        System.out.println(clientService.getNumberOfPremiumClients());




//        System.out.println(clientService.createNewClient("Adam", "Gromadzki"));
    }
}