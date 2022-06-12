package course2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Save implements Menu {
    private GameObject gameObject;
    public Save(GameObject gameObject){
        this.gameObject = gameObject;
    }
    @Override
    public void execute() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(new File("save")))) {
            outputStream.writeObject(gameObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new Start(gameObject).execute();

    }
}
