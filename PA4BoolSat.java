import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

import bool_exp.ASTNode;
import bool_exp.BoolSatParser;

public class PA4BoolSat {
    public static void main(String[] params) {
        String filename = params[0];
        String expr = "";
        boolean debug = false;
        if (params.length >= 2) {
            debug = true;
        }

        try {
            Scanner file = new Scanner(new File(filename));
            expr += file.nextLine();
            file.close();
        } catch (Exception e) {
            System.out.println("INVALID FILE NAME.");
        }

        ASTNode root = BoolSatParser.parse(expr);

        // System.out.println(root.toDotString());

        boolean[] bool = { false, true };
        TreeSet<String> termset = new TreeSet<String>();
        ArrayList<HashMap<String, Boolean>> truthTable = new ArrayList<HashMap<String, Boolean>>();

        treeTraversal(root, termset);
        ArrayList<String> terms = new ArrayList<String>(termset);
        getTruthTable(truthTable, new HashMap<String, Boolean>(), terms, bool);
        printTruthTable(truthTable);
    }

    public static void treeTraversal(ASTNode node, TreeSet<String> terms) {
        if (node != null) {
            treeTraversal(node.child1, terms);
            treeTraversal(node.child2, terms);
            if (node.isId()) {
                terms.add(node.getId());
            }
        }
    }

    public static void getTruthTable(
            ArrayList<HashMap<String, Boolean>> truthTable,
            HashMap<String, Boolean> guesses, ArrayList<String> terms,
            boolean[] bool) {
        if (terms.size() == 0) {
            HashMap<String, Boolean> dict = new HashMap<String, Boolean>();
            for (String key : guesses.keySet()) {
                boolean value = guesses.get(key);
                dict.put(key, value);
            }
            truthTable.add(dict);
        } else {
            for (int i = 0; i <= 1; i++) {
                String term = terms.remove(0);
                guesses.put(term, bool[i]);
                getTruthTable(truthTable, guesses, terms, bool);
                terms.add(0, term);
            }
        }
    }

    public static void printTruthTable(
            ArrayList<HashMap<String, Boolean>> truthTable) {
        for (HashMap<String, Boolean> dict : truthTable) {
            ArrayList<String> sortedKeys = new ArrayList<String>(dict.keySet());
            Collections.sort(sortedKeys);
            for (String key : sortedKeys) {
                boolean value = dict.get(key);
                System.out.print(key + ": " + value + ", ");
            }
            System.out.println();
        }
    }
}