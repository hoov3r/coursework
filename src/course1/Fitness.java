package course1;
import java.util.Arrays;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.LocalTime;


public class Fitness {

    private final Card[] gym = new Card[20];
    private final Card[] swim = new Card[20];
    private final Card[] group = new Card[20];

    public void visit (String zone, Card card){
        if (ChronoUnit.DAYS.between(card.getExit(), LocalDate.now()) >= 0){
            System.out.println("Need new Abonement");

        } else if(ChronoUnit.MINUTES.between(card.getAbonement().getEnter(),LocalTime.now()) < 0 || ChronoUnit.MINUTES.between(card.getAbonement().getExit(),LocalTime.now()) > 0){
            System.out.println(card.getClient().getClientName()+", time off ");

        }  else if (zone.equalsIgnoreCase("gym")){
            if (card.getAbonement().isGym()) {
                for (int i = 0; i < gym.length; i++){
                    if(gym[i] == null){
                        gym[i] = card;
                        System.out.println(card.getClient().getClientName() + " time: " + LocalDate.now()+ " " + LocalTime.now());
                        return;
                    }
                } System.out.println("filled");
            }
        } else if (zone.equalsIgnoreCase("swim")){
            if(card.getAbonement().isSwim()){
                for (int i = 0; i < swim.length; i++){
                    if (swim[i] == null){
                        swim[i] = card;
                        System.out.println(card.getClient().getClientName() + " time: " + LocalDate.now()+ " " + LocalTime.now());
                        return;
                    }
                }System.out.println("filled");
            } else {
                System.out.println(card.getClient().getClientName()+", without gym");
            }
        } else if (zone.equalsIgnoreCase("group")){
            if(card.getAbonement().isGroup()){
                for (int i = 0; i < group.length; i++){
                    if (group[i] == null){
                        group[i] = card;
                        System.out.println(card.getClient().getClientName() + " time: " + LocalDate.now()+ " " + LocalTime.now());
                        return;
                    }
                } System.out.println("filled");
            } else {
                System.out.println(card.getClient().getClientName()+", without group ");
            }
        }
    }


    public  void printInfo(){
        System.out.println("\n gym: ");
        for (Card card : gym) {
            if (card != null){
                System.out.println(card.getClient().getClientName());
            }
        }
        System.out.println("\n pool: ");
        for (Card card : swim) {
            if (card != null){
                System.out.println(card.getClient().getClientName());
            }
        }
        System.out.println("\n group: ");
        for (Card card : group) {
            if (card != null){
                System.out.println(card.getClient().getClientName());
            }
        }
    }

    public void close(){
        Arrays.fill(gym, null);
        Arrays.fill(swim, null);
        Arrays.fill(group,null);
        System.out.println("Close");
    }
}