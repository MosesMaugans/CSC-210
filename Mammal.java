
public class Mammal extends Animal {
    private static String type;

    public Mammal(String type, int x, int y, String sex, String direction) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
