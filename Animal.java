
public class Animal extends PA6Main {
    public Animal() {
    }

    public String getType() {
        return ".";
    }

    public String toString() {
        return "generic animal";
    }

    public void move() {

    }

    public void eat(Animal prey) {
    }

    public int[] checkBounds(int x, int y) {
        if (x < 0) {
            x = screen.get(y).size() - 1;
        } else if (x > screen.get(y).size() - 1) {
            x = 0;
        } else if (y < 0) {
            y = screen.size() - 1;
        } else if (y > screen.size() - 1) {
            y = 0;
        }
        int[] coords = { x, y };
        return coords;
    }
}