package course1;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public enum Abonement {

    ONE("08:00:00","22:00:00",true,true,false), // однодневный
    DAY("08:00:00","16:00:00", false,true,true),// дневной
    FULL("08:00:00","22:00:00",true,true,true); // полный

    //задаем свойства элементов
    private LocalTime enter;
    private LocalTime exit;
    private boolean swim;
    private boolean gym;
    private boolean group;

    Abonement(String enterToParse, String exitToParse, boolean swim, boolean gym, boolean group) {
        setEnter(enterToParse);
        setExit(exitToParse);
        this.swim = swim;
        this.gym = gym;
        this.group = group;
    }


    public void setEnter(String enterToParse) {
        this.enter = LocalTime.parse(enterToParse, DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public LocalTime getEnter() {
        return enter;
    }



    public void setExit(String exitToParse) {
        this.exit = LocalTime.parse(exitToParse, DateTimeFormatter.ofPattern("HH:mm:ss"));;
    }

    public LocalTime getExit() {
        return exit;
    }



    public boolean isSwim() {
        return swim;
    }
    public boolean isGym() {
        return gym;
    }
    public boolean isGroup() {
        return group;
    }
}

