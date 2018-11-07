public class Mammal extends Animal {
    private boolean moveVertically;
    private String direction;
    public Mammal(String type, int x, int y, String sex, String direction) {
        super(type, x, y, sex);
        this.direction = direction;
        moveVertically = true;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return type;
    }

    public void reproduce() {

    }

    public void eat(Animal prey) {
        if (mammals.contains(prey.getType())) {
            screen.get(y).get(x).remove(this);
            animals.remove(prey);
        }
    }

    public void move() {
        if (direction.equals("right")) {
            if (moveVertically) {
                int moveDown = y + 1;
                y = moveDown;
            } else {
                int moveRight = x + 1;
                x = moveRight;
            }
        } else if (direction.equals("left")) {
            if (moveVertically) {
                int moveUp = y - 1;
                y = moveUp;
            } else {
                int moveLeft = x - 1;
                x = moveLeft;
            }
        }
        int[] coords = checkBounds(x, y);
        x = coords[0];
        y = coords[1];

        tempScreen.get(y).get(x).add(this);
        moveVertically = !moveVertically;
        age += 1;
        lastEaten += 1;
    }
}