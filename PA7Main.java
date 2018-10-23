import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;


public class PA7Main {
    public static void main(String[] args) throws FileNotFoundException {
        String filename = args[0];// filename and command are always provided
        String command = args[1];
        Scanner file_reader = new Scanner(new File(filename));// read file
        ArrayList<String> file = fileToList(file_reader);// convert file
        file_reader.close();// Scanner isn't needed, reference the ArrayList
        if (command.equals("CATCOUNT")) {// processes file as CATCOUNT
            HashMap<String, Integer> catcount = processCatCount(file);
            printResults(catcount, command, "");
        } else if (command.equals("LOCATIONS")) {// processes as LOCATIONS
            String category = args[2];// this means a category was provided
            HashMap<String, Integer> locations = processLocations(file,
                        category);
            printResults(locations, command, category);
        }
        else {// the command isn't recognized, can't be processed
            System.out.println("Invalid Command");
        }
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
    public static void printResults(HashMap<String, Integer> results,
            String command, String category) {
        if (command.equals("CATCOUNT")) {// prints header for CATCOUNT command
            System.out.println("Number of positions for each category");
            System.out.println("-------------------------------------");
        }
        else {// prints LOCATIONS header(only other possible command)
            System.out.format("%s for category: %s\n", command, category);
            System.out.println("-------------------------------------");
        }
        ArrayList<String> keys = new ArrayList<String>(results.keySet());
        Collections.sort(keys);// sorts the keys in alphabetical order
        for (String key : keys) {// iterates through the sorted keys
            System.out.printf("%s, %s\n", key, results.get(key));
        } // prints out the key and value pairs
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
    public static ArrayList<String> fileToList(Scanner file_reader) {
        ArrayList<String> file = new ArrayList<String>();
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
    public static HashMap<String, Integer> processLocations(
            ArrayList<String> file,
            String category) {
        HashMap<String, Integer> locations = new HashMap<String, Integer>();
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
    public static HashMap<String, Integer> processCatCount(
            ArrayList<String> file) {
        HashMap<String, Integer> catcount = new HashMap<String, Integer>();
        for (int i = 0; i < file.size(); i++) {// iterates through whole file
            String[] line = file.get(i).split(",");// splits on comma
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
