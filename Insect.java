public class Insect extends Animal {
    private int direction;
    private boolean val;
    private int distance;
    private int distanceTraveled;

    public Insect(String type, int x, int y, String sex, boolean val) {
        super(type, x, y, sex);
        this.val = val;
        distance = 1;
        distanceTraveled = 0;
        direction = 1;
    }


    public void eat(Animal other) {
        if (birds.contains(other.getType())) {
            screen.get(y).get(x).remove(this);
        } else if (mammals.contains(other.getType())) {
            lastEaten = 0;
        }
    }

    public void move() {
        if (direction == 1) {
                x -= 1;
        } else if (direction == 2) {
            if (val) {
                y -= 1;
            } else {
                y += 1;
            }
        } else if (direction == 3) {
                x += 1;
        } else if (direction == 4) {
            if (val) {
                y += 1;
            } else {
                y -= 1;
            }
        }
        int[] coords = checkBounds(x, y);
        x = coords[0];
        y = coords[1];
        tempScreen.get(y).get(x).add(0, this);
        distanceTraveled += 1;
        lastEaten += 1;
        age += 1;
        if (distanceTraveled == distance) {
            if (direction == 4) {
                direction = 1;

            } else {
                direction += 1;
                distanceTraveled = 0;
            }
        }
    }

    public String toString() {
        return type;
    }

    public String getType() {
        return type;
    }
}