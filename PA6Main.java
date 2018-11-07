
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PA6Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner readfile = new Scanner(new File(args[0]));
        int[] dimensions = parseScreenSize(readfile);
        int rowLength = dimensions[0];
        int colLength = dimensions[1];
        screen = makeScreen(rowLength, colLength);
        // System.out.println(screen.get(1).get(1).get(1));
        while (readfile.hasNextLine()) {
            String line = readfile.nextLine();
            if (line.contains("CREATE")) {
                create(line);
            } else if (line.contains("PRINT")) {
                System.out.println("> PRINT");
                printScreen();
            } else if (line.contains("MOVE")) {
                System.out.println("> MOVE\n");
                tempScreen = makeScreen(rowLength, colLength);
                moveScreen();
            } 
        }

    }

    public static void parseReproduce() {
        for (List<List<Animal>> row : screen) {
            for (List<Animal> col : row) {
                if (col.size() > 1) {
                    Animal.reproduce(col.get(0), col.get(1));
                }
            }
        }
    }

    public static void parseEat() {
        for (List<List<Animal>> row : screen) {
            for (List<Animal> col : row) {
                if (col.size() > 1) {
                    col.get(0).eat(col.get(1));
                }
            }
        }
    }

    public static void moveScreen() {
        for (List<List<Animal>> row : screen) {
            for (List<Animal> tile : row) {
                for (Animal animal : tile) {
                    animal.move();
                }
            }
        }
        screen = tempScreen;
    }

    public static List<List<List<Animal>>> tempScreen;
    public static List<List<List<Animal>>> screen;
    public static List<Animal> animals = new ArrayList<Animal>();
    public static String mammals = "erlcgz";
    public static String birds = "towds";
    public static String insects = "mbfha";

    public static void create(String obj) {
        Animal newAnimal = new Animal();
        String[] input = obj.split(" ");
        String[] coords = input[1].split(",");

        int x = Integer.parseInt(coords[0].substring(1));
        int y = Integer.parseInt(coords[1].substring(0, 1));
        String type = input[2].substring(0, 1);
        String sex = input[3];
        if (mammals.contains(type)) {
            newAnimal = createMammal(type, x, y, sex, input);
        } else if (birds.contains(type)) {
            newAnimal = createBird(type, x, y, sex, input);
        } else if (insects.contains(type)) {
            newAnimal = createInsect(type, x, y, sex, input);
        }

        screen.get(y).get(x).add(newAnimal);
        animals.add(newAnimal);
    }

    public static Animal createInsect(String type, int x, int y, String sex,
            String[] params) {
        boolean val = Boolean.parseBoolean(params[4]);
        Insect i = new Insect(type, x, y, sex, val);
        return i;
    }

    public static Animal createBird(String type, int x, int y, String sex,
            String[] params) {
        int num = Integer.parseInt(params[4]);
        Bird b = new Bird(type, x, y, sex, num);
        return b;
    }

    public static Animal createMammal(String type, int x, int y, String sex,
            String[] params) {
        String direction = params[4];
        Mammal m = new Mammal(type, x, y, sex, direction);
        return m;
    }

    public static int[] parseScreenSize(Scanner readfile) {
        String[] row = readfile.nextLine().split(" ");
        String[] col = readfile.nextLine().split(" ");
        int rowLength = Integer.parseInt(row[1]);
        int colLength = Integer.parseInt(col[1]);
        int[] dimensions = { rowLength, colLength };
        return dimensions;
    }

    public static List<List<List<Animal>>> makeScreen(int rowLength,
            int colLength) {
        List<List<List<Animal>>> newScreen = new ArrayList<List<List<Animal>>>();
        for (int r = 0; r < rowLength; r++) {
            newScreen.add(new ArrayList<List<Animal>>(colLength));
            for (int c = 0; c < colLength; c++) {
                newScreen.get(r).add(new ArrayList<Animal>());
            }
        }
        return newScreen;
    }

    private static void printScreen() {
        for (List<List<Animal>> row : screen) {
            for (List<Animal> animal : row) {
                if (animal.size() == 0) {
                    System.out.print(".");
                } else {
                    System.out.print(animal.get(0));
                }

            }
            System.out.println();
        }
        System.out.println();
    }
}
