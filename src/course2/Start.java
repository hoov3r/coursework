package course2;

public class Start implements Menu {
    private GameObject gameObject;

    public Start(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public void execute() {
        GameList gameList = new GameList();
        GameList.game(gameObject);
    }
}
