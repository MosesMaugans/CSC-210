import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PA5Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File(args[0]));
        int size = findGraphSize(file);
        DGraph graph = new DGraph(size);
        fillGraph(graph, file);

        PrintWriter writer = new PrintWriter("graph.dot");
        writer.println(graph.toDotString());
        writer.close();
        System.out.println(graph.toDotString());
    }

    public static int findGraphSize(Scanner file) {
        String line = file.nextLine();
        char firstChar = line.charAt(0);
        while (firstChar == '%') {
            line = file.nextLine();
            firstChar = line.charAt(0);
        }
        String[] matrixDimensions = line.split(" ");
        int rows = Integer.parseInt(matrixDimensions[0]);
        int columns = Integer.parseInt(matrixDimensions[1]);
        return Math.max(rows, columns);
    }

    public static void fillGraph(DGraph graph, Scanner file) {
        while (file.hasNextLine()) {
            String line = file.nextLine();
            String[] edge = line.split(" ");
            int city1 = Integer.parseInt(edge[0]);
            int city2 = Integer.parseInt(edge[1]);
            double distance = Double.parseDouble(edge[2]);
            if (city1 != city2) {
                graph.addEdge(city1, city2, distance);
            }
        }
    }
}
