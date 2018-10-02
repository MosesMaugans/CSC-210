import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PA5Main {
    public static void main(String[] args) throws FileNotFoundException {
        String command = args[1];

        Scanner file = new Scanner(new File(args[0]));
        int size = findGraphSize(file);
        DGraph graph = new DGraph(size);
        fillGraph(graph, file);

        PrintWriter writer = new PrintWriter("graph.dot");
        writer.println(graph.toDotString());
        writer.close();
        // System.out.println(graph.toDotString());

        Trip currTrip = new Trip(size);
        Trip minTrip = new Trip(size);
        currTrip.chooseNextCity(1);
        if (command.equals("BACKTRACK")) {
            evaluate(graph, currTrip, minTrip);
            System.out.println(minTrip.toString(graph));
        } else if (command.equals("HEURISTIC")) {
            getTrip(graph, currTrip);
        }
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
            String[] edge = line.split("\\s+");
            int city1 = Integer.parseInt(edge[0]);
            int city2 = Integer.parseInt(edge[1]);
            double distance = Double.parseDouble(edge[2]);
            if (city1 != city2) {
                graph.addEdge(city1, city2, distance);
            }
        }
    }

    public static void evaluate(DGraph graph, Trip currTrip, Trip minTrip) {
        if (currTrip.citiesLeft().isEmpty()) {
            if (currTrip.tripCost(graph) < minTrip.tripCost(graph)) {
                minTrip.copyOtherIntoSelf(currTrip);
            }
        } else {
            for (int i = 0; i < currTrip.citiesLeft().size(); i++) {
                int city = currTrip.citiesLeft().get(i);
                currTrip.chooseNextCity(city);
                evaluate(graph, currTrip, minTrip);
                currTrip.unchooseLastCity();
            }
        }
    }

    public static void getTrip(DGraph graph, Trip currTrip) {
        for(int i = 2; i <= graph.getNumNodes(); i++) {
            List<Integer> cities = new ArrayList<Integer>(graph.getNeighbors(i));
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < cities.size(); j++) {
                if
            }
        }
    }
}
