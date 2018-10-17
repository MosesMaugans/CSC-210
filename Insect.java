public class Insect extends Animal {
    private static String type;
    private static int x;
    private static int y;
    private static int direction;
    private static String sex;
    private static int lastEaten;
    private static int age;
    private static int distance;

    public Insect(String type, int x, int y, String sex, boolean val) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.sex = sex;
        direction = 1;
        lastEaten = 0;
        age = 0;
    }
    

    public void eat(Animal other) {
        if (birds.contains(other.getType())) {
            screen.get(y).get(x).remove(this);
        } else if (mammals.contains(other.getType())) {
            lastEaten = 0;
        }
    }

    public String getType() {
        return type;
    }
}