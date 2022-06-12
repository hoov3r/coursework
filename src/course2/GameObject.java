package course2;

import java.io.Serializable;

public class GameObject implements Serializable {
    private int firstNode;
    private int secondNode;
    private String text;

    public GameObject(int firstNode, int secondNode, String text) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.text = text;
    }

    public int getFirstNode() {
        return firstNode;
    }

    public int getSecondNode() {
        return secondNode;
    }

    public String getText() {
        return text;
    }
}