package course1;

public class Application {
    public static void main(String[] args) {
        Client client1 = new Client("11 11", "18.09.1994");
        Client client2 = new Client("22 22", "19.10.1990");
        Client client3 = new Client("33 33", "20.11.1969");
        Client client4 = new Client("44 44", "21.12.2002");
        Client client5 = new Client("55 55", "17.08.1997");


        Card card1 = new Card(Abonement.FULL, client1);
        Card card2 = new Card(Abonement.ONE, client2);
        Card card3 = new Card(Abonement.DAY, client3);
        Card card4 = new Card(Abonement.FULL, client4);
        Card card5 = new Card(Abonement.DAY, client5);


        Fitness day = new Fitness();

        day.visit("swim", card1);
        day.visit("swim", card2);
        day.visit("gym", card3);
        day.visit("swim", card4);
        day.visit("group", card5);


        day.printInfo();
        System.out.println();
        day.close();

    }
}
