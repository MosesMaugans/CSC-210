
public class Animal extends PA6Main {
    protected String type;
    protected int x;
    protected int y;
    protected int direction;
    protected String sex;
    protected int age;
    protected int lastEaten;

    public Animal() {

    }

    public Animal(String type, int x, int y, String sex) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.sex = sex;
        lastEaten = 0;
        age = 0;
    }

    public String getType() {
        return "a";
    }

    public void move() {

    }

    public void eat(Animal prey) {
    }

    public int[] checkBounds(int x, int y) {
        if (x < 0) {
            x = screen.size() - 1;
        } else if (x > screen.size() - 1) {
            x = 0;
        } else if (y < 0) {
            y = screen.size() - 1;
        } else if (y > screen.size() - 1) {
            y = 0;
        }
        int[] coords = { x, y };
        return coords;
    }

    public static void reproduce(Animal animal1, Animal animal2) {
        if (animal1.type.equals(animal2.type)
                && !(animal1.sex.equals(animal2.sex))) {
            Animal newAnimal = null;
            String newType = animal1.type;
            int newX = animal1.x;
            int newY = animal1.y;
            String newSex = animal1.sex;
            if (animal1 instanceof Mammal) {
                newAnimal = new Mammal(newType, newX, newY, newSex, "right");
            } else if (animal1 instanceof Insect) {
                newAnimal = new Insect(newType, newX, newY, newSex, true);
            } else if (animal1 instanceof Bird) {
                Bird bird = (Bird) animal1;
                if (bird.getDirection() == 2 || bird.getDirection() == 3) {
                    newAnimal = new Bird(newType, newX, newY, newSex, 5);
                }
            }
            if (newAnimal != null) {
                screen.get(newX).get(newY).add(newAnimal);
            }
        }

    }
}