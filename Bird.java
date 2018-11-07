/*
 * Moses Maugans
 * CSC 210, Fall 2018
 * PA6, PA6Main.java
 */
public class Bird extends Animal {
    private int distance;
    private int distanceTraveled;
    private int direction;

    public Bird(String type, int x, int y, String sex, int num) {
        super(type, x, y, sex);
        distance = num;
        distanceTraveled = 0;
        direction = 1;
    }

    public String getType() {
        return type;
    }

    public int getDirection() {
        return direction;
    }

    public void eat(Animal other) {
        if (insects.contains(other.getType())) {
            screen.get(y).get(x).remove(other);
            animals.remove(other);
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
        }
        int[] coords = checkBounds(x, y);
        x = coords[0];
        y = coords[1];
        tempScreen.get(y).get(x).add(this);
        distanceTraveled += 1;
        lastEaten += 1;
        age += 1;
        if (distanceTraveled == distance) {
            if (direction == 3) {
                direction = 1;
            } else {
                direction += 1;
            }
            distanceTraveled = 0;
        }
    }

    public String toString() {
        return type;
    }
}