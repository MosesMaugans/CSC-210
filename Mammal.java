public class Mammal extends Animal {
    private static String type;
    private static int x;
    private static int y;
    private static String direction;
    private static String sex;
    private static int lastEaten;
    private static int age;
    private static boolean moveVertically;

    public Mammal(String type, int x, int y, String sex, String direction) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.sex = sex;
        this.direction = direction;
        lastEaten = 0;
        age = 0;
        moveVertically = true;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return type + " (" + x + ", " + y + ")";
    }

    public void eat(Animal prey) {
        if (mammals.contains(prey.getType())) {
            screen.get(y).get(x).remove(prey);
            animals.remove(prey);
        }
    }

    public void move() {
        if (direction.equals("right")) {
            if (moveVertically) {
                int moveDown = y + 1;
                if (moveDown > screen.size() - 1) {
                    moveDown = 0;
                }
                y = moveDown;
            } else {
                int moveRight = x + 1;
                if (moveRight > screen.get(y).size() - 1) {
                    moveRight = 0;
                }
                x = moveRight;
            }
        } else if (direction.equals("left")) {
            if (moveVertically) {
                int moveUp = y - 1;
                if (moveUp < 0) {
                    moveUp = screen.size() - 1;
                }
                y = moveUp;
            } else {
                int moveLeft = x - 1;
                if (moveLeft < 0) {
                    moveLeft = screen.get(y).size() - 1;
                }
                x = moveLeft;
            }
        }
        tempScreen.get(y).get(x).add(0, this);
        moveVertically = !moveVertically;
        age += 1;
        lastEaten += 1;
    }
}