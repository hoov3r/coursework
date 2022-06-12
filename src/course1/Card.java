package course1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Card {


    private Abonement abonement;
    private Client client;
    private LocalDate enter;
    private LocalDate exit;

    public Card(Abonement abonement, Client client) {
        enter=LocalDate.now();
        this.abonement = abonement;
        setClient(client);
        if (abonement == Abonement.ONE){
            exit = LocalDate.now().plusDays(1);
        } else
            exit = LocalDate.now().plusDays(365);
    }

    public void setClient(Client client) {
        if(ChronoUnit.YEARS.between(client.getClientYear(), LocalDate.now()) < 18  ||
                ChronoUnit.YEARS.between(client.getClientYear(), LocalDate.now()) > 100) {
            throw new IllegalArgumentException("<18 или >100 ");}
        this.client = client;
    }




    public LocalDate getExit() {
        return exit;
    }


    public Abonement getAbonement() {
        return abonement;
    }


    public Client getClient() {
        return client;
    }
}