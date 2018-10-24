import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PA7Main {
    public static void main(String[] args) throws FileNotFoundException {
        String filename = args[0];// filename and command are always provided
        String command = args[1];
        String category = "";
        if (args.length == 3) {
            category = args[2];
        }
        Scanner file_reader = new Scanner(new File(filename));// read file
        List<String> file = fileToList(file_reader);// convert file
        file_reader.close();// Scanner isn't needed, reference the ArrayList
        String results = processCommands(file, command, category);
        System.out.println(results);
    }

    /*
     * Prints the results of the command the program just executed.
     * 
     * @param results is the HashMap to be printed, command is the user
     * provided command, and category is the category specified for
     * LOCATIONS commands(this is an empty string if the command is not
     * LOCATIONS)
     * 
     * @return no returned value, just printing contents of results
     */
    public static String processCommands(List<String> file, String command,
            String category) {
        MyHashMap<String, Integer> results = new MyHashMap<String, Integer>();
        String toPrint = "";
        if (command.equals("CATCOUNT")) {// prints header for CATCOUNT command
            System.out.println("Number of positions for each category");
            System.out.println("-------------------------------------");
            results = processCatCount(file);
        } else if (command.equals("LOCATIONS")) {// prints LOCATIONS header(only
                                                 // other possible command)
            System.out.format("%s for category: %s\n", command, category);
            System.out.println("-------------------------------------");
            results = processLocations(file, category);
        } else if (command.equals("DEBUG")) {
            results = processCatCount(file);
            return results.toString();
        } else {
            return "Invalid Command";
        }
        ArrayList<String> keys = new ArrayList<String>(results.keySet());
        Collections.sort(keys);// sorts the keys in alphabetical order
        for (String key : keys) {// iterates through the sorted keys
            toPrint += key + ", " + results.get(key) + "\n";
        } // prints out the key and value pairs
        return toPrint;
    }

    /*
     * Converts the and returns the contents of the input file as an
     * ArrayList
     * 
     * @param file_reader is a Scanner object initialized with the input
     * file
     * 
     * @return the contents of the input_file as an ArrayList
     */
    public static List<String> fileToList(Scanner file_reader) {
        List<String> file = new ArrayList<String>();
        file_reader.nextLine();
        while (file_reader.hasNextLine()) {
            file.add(file_reader.nextLine());
        }
        return file;
    }

    /*
     * Process the contents of the input for the LOCATIONS command and returns
     * a HashMap with the job openings for the specified category at each
     * location
     * 
     * @param file is an array list with the contents of the input file, and
     * category is the job category to collect information for
     * 
     * @return a HashMap with each key being a location and the value being a
     * the open jobs in that location for the specified job category
     */
    public static MyHashMap<String, Integer> processLocations(
            List<String> file, String category) {
        MyHashMap<String, Integer> locations = new MyHashMap<String, Integer>();
        for (int i = 0; i < file.size(); i++) {// iterates through the file
            String[] line = file.get(i).split(",");// splits line on commas
            if (line.length == 7) {
                if (line[2].equals(category)) {// if the job is correct category
                    if (locations.get(line[3]) == null) {// if not entry
                        locations.put(line[3], 1);// make a new one
                    } else {// otherwise update the existing entry
                        locations.put(line[3], locations.get(line[3]) + 1);
                    }
                }
            }
        }
        return locations;
    }

    /*
     * Process the contents of the input file for CATCOUNT command and returns
     * a HashMap with the job openings in each job category.
     * 
     * @param file is an array list with the contents of the input file
     * 
     * @return a HasMap with each key being a job category and each value being
     * the number of job openings in that category.
     */
    public static MyHashMap<String, Integer> processCatCount(
            List<String> file) {
        MyHashMap<String, Integer> catcount = new MyHashMap<String, Integer>();
        for (String fileLine : file) {// iterates through whole file
            String[] line = fileLine.split(",");// splits on comma
            if (line.length == 7) {
                if (catcount.get(line[2]) == null) {// for new categories
                    catcount.put(line[2], 1);// adds new key and value pair
                } else {// if category already in catcount just the value is
                        // updated
                    catcount.put(line[2], catcount.get(line[2]) + 1);
                }
            }
        }
        return catcount;
    }

}