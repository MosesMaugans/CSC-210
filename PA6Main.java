import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PA6Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner readfile = new Scanner(
                new File("publicTestcases/mammalMammalEat.in"));
        makeScreen(readfile);
        while (readfile.hasNextLine()) {
            String line = readfile.nextLine();
            if (line.contains("CREATE")) {
                create(line);
            } else if(line.contains("MOVE")) {
                move
            }
        }
        // readfile.nextLine();
        // createMammal(readfile.nextLine());
        // createMammal(readfile.nextLine());
        printScreen();
    }

    private static Animal[][] screen;

    public static void create(String obj) {
        Animal newAnimal = new Animal();
        String mammals = "erlcgz";
        String birds = "towds";
        String insects = "mbfha";
        String[] input = obj.split(" ");
        String[] coords = input[1].split(",");
        int x = Integer.parseInt(coords[0].substring(1));
        int y = Integer.parseInt(coords[1].substring(0, 1));
        String type = input[2].substring(0, 1);
        if (mammals.contains(type)) {
            newAnimal = createMammal(type, x, y, input);
        } else if (birds.contains(type)) {
            newAnimal = createBird(type, x, y, input);
        } else if (insects.contains(type)) {
            newAnimal = createInsect(type, x, y, input);
        }
        screen[x][y] = newAnimal;
    }

    public static Animal createInsect(String type, int x, int y,
            String[] params) {
        boolean val = Boolean.parseBoolean(params[3]);
        Animal i = new Insect(type, x, y, val);
        return i;
    }

    public static Animal createBird(String type, int x, int y,
            String[] params) {
        int num = Integer.parseInt(params[3]);
        Animal b = new Bird(type, x, y, num);
        return b;
    }

    public static Animal createMammal(String type, int x, int y,
            String[] params) {
        String sex = params[3];
        String direction = params[4];
        Animal m = new Mammal(type, x, y, sex, direction);
        return m;
    }

    public static void makeScreen(Scanner readfile) {
        String[] row = readfile.nextLine().split(" ");
        String[] col = readfile.nextLine().split(" ");
        int rowLength = Integer.parseInt(row[1]);
        int colLength = Integer.parseInt(col[1]);
        screen = new Animal[rowLength][colLength];
    }

    private static void printScreen() {
        for (Animal[] row : screen) {
            for (Animal animal : row) {
                if (animal == null) {
                    System.out.print(".");
                } else {
                    System.out.print(animal.getType());
                }
            }
            System.out.println();
        }
    }
}

