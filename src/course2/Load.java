package course2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Load implements Menu {
    private GameObject gameObject;
    public void execute() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("save"))) {
            this.gameObject = (GameObject) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        new Start(gameObject).execute();
    }
}
