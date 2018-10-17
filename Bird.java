public class Bird extends Animal {
    private static String type;
    private static int x;
    private static int y;
    private static String sex;
    private static int lastEaten;
    private static int age;
    private static int distance;
    private static int distanceTraveled;
    private static int direction;

    public Bird(String type, int x, int y, String sex, int num) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.sex = sex;
        lastEaten = 0;
        age = 0;
        distance = num;
        distanceTraveled = 0;
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
        if (direction == 1) {
            y += 1;
        } else if (direction == 2) {
            x += 1;
        } else if (direction == 3) {
            y -= 1;
        } else if (direction == 4) {
            x -= 1;
        }
        tempScreen.get(y).get(x).add(0, this);
        distanceTraveled += 1;
        lastEaten += 1;
        age += 1;
        if (distanceTraveled == distance) {
            if (direction == 4) {
                direction = 1;
            } else {
                direction += 1;
            }
        }
    }

    public String getType() {
        return type;
    }
}