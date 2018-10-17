import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PA6Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner readfile = new Scanner(
                new File(args[0]));
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
            } else if (line.contains("EAT")) {
                System.out.println("> EAT\n");
                parseEat();
            }
        }

    }

    public static void parseEat() {
        for (List<List<Animal>> row : screen) {
            for (List<Animal> col : row) {
                if (col.size() > 1) {
                    col.get(1).eat(col.get(0));
                }
            }
        }
    }

    public static void moveScreen() {
        for (List<List<Animal>> row : screen) {
            for (List<Animal> col : row) {
                for (Animal a : col) {
                    a.move();
                }
            }
        }
        screen = tempScreen;
    }

    protected static List<List<List<Animal>>> tempScreen;
    protected static List<List<List<Animal>>> screen;
    protected static List<Animal> animals = new ArrayList<Animal>();
    protected static String mammals = "erlcgz";
    protected static String birds = "towds";
    protected static String insects = "mbfha";

    public static void create(String obj) {
        Animal newAnimal = new Animal();
        String[] input = obj.split(" ");
        System.out.println(input[0]);
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
        screen.get(y).get(x).add(0, newAnimal);
        animals.add(newAnimal);
    }

    public static Animal createInsect(String type, int x, int y, String sex,
            String[] params) {
        boolean val = Boolean.parseBoolean(params[4]);
        Animal i = new Insect(type, x, y, sex, val);
        return i;
    }

    public static Animal createBird(String type, int x, int y, String sex,
            String[] params) {
        int num = Integer.parseInt(params[4]);
        Animal b = new Bird(type, x, y, sex, num);
        return b;
    }

    public static Animal createMammal(String type, int x, int y, String sex,
            String[] params) {
        String direction = params[4];
        Animal m = new Mammal(type, x, y, sex, direction);
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
                newScreen.get(r).get(c).add(new Animal());
            }
        }
        return newScreen;
    }

    private static void printScreen() {
        for (List<List<Animal>> row : screen) {
            for (List<Animal> animal : row) {
                System.out.print(animal.get(0).getType());
            }
            System.out.println();
        }
        System.out.println();
    }
}