package course1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Client {

    private String clientName;
    private LocalDate clientYear;


    public void setClientName(String clientName) {
        if (clientName == null) {
            throw new IllegalArgumentException("name need more then 1 symbol");
        }
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }



    public void setClientYear(String clientYearToParse) {
        if (ChronoUnit.YEARS.between(LocalDate.parse(clientYearToParse, DateTimeFormatter.ofPattern("dd.MM.yyyy")), LocalDate.now()) < 0) {
            throw new IllegalArgumentException("error birthday");
        }
        this.clientYear = LocalDate.parse(clientYearToParse, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public LocalDate getClientYear() {
        return clientYear;
    }


    public Client(String clientName, String clientYearToParse) {
        setClientName(clientName);
        setClientYear(clientYearToParse);
    }
}