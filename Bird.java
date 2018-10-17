public class Bird extends Animal {
    private static String type;
    private static int x;
    private static int y;
    private static String sex;
    private static int lastEaten;
    private static int age;
    private static int distance;
    private static int direction;

    public Bird(String type, int x, int y, String sex, int num) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.sex = sex;
        lastEaten = 0;
        age = 0;
        distance = num;
        direction = 1;
    }

    public void eat(Animal other) {
        if (birds.contains(other.getType())
                || insects.contains(other.getType())) {
            screen.get(y).get(x).remove(other);
        }
        lastEaten = 0;
    }

    public void move() {
        screen.get(y).get(x).remove(this);
        if (direction == 1) {
            y += distance;
            direction += 1;
        } else if (direction == 2) {
            x += distance;
            direction += 1;
        } else if (direction == 3) {
            y -= distance;
            direction += 1;
        } else if (direction == 4) {
            x -= distance;
            direction = 1;
        }
        screen.get(y).get(x).add(0, this);
        lastEaten += 1;
        age += 1;
    }

    public String getType() {
        return type;
    }
}
