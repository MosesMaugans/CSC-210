
public class Bird extends Animal {
    private static String type;

    public Bird(String type, int x, int y, int num) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
