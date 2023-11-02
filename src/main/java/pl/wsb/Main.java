package pl.wsb;

public class Main {
    public static void main(String[] args) {

        Client client = new Client();
        client.setFirstname("Jarek");
        client.setStatus(ClientStatus.PREMIUM);

        System.out.println(client.getFirstname());
    }
}