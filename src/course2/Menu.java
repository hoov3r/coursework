package course2;


import java.util.Scanner;

public interface Menu {
    void execute();

    static Menu userChoiceOne() {
        System.out.println("Меню:\n" +
                "1. Начать игру\n" +
                "2. Загрузить игру\n" +
                "3. Выйти");

        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        scanner.nextLine();

        switch (userChoice) {
            case (1):
                return new Start(new GameList().getGameList()[0]);
            case (2):
                return new Load();
            case (3):
                return new Exit();
            default:
                System.out.println("Ошибка. Неверная команда.");
                return Menu.userChoiceOne();
        }
    }

    static Menu userChoiceTwo(GameObject gameObject) {
        System.out.println("Меню:\n" +
                "1. Сохранить игру\n" +
                "2. Загрузить игру\n" +
                "3. Выйти\n");

        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        scanner.nextLine();

        switch (userChoice) {
            case (1):
                return new Save(gameObject);
            case (2):
                return new Load();
            case (3):
                return new Exit();
            default:
                System.out.println("Ошибка. Неверная команда.");
                return Menu.userChoiceTwo(gameObject);
        }
    }
}
