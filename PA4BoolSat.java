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
        if (params.length > 1) {
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
        boolean[] bool = { false, true };
        TreeSet<String> termset = new TreeSet<String>();
        ArrayList<HashMap<String, Boolean>> truthTable = new ArrayList<HashMap<String, Boolean>>();

        treeTraversal(root, termset);

        ArrayList<String> terms = new ArrayList<String>(termset);

        getTruthTable(truthTable, new HashMap<String, Boolean>(), terms, bool);
        printTruthTable(expr, truthTable, root, debug);
    }

    /*
     * Traverses, in post order, a boolean expression tree and adds terms that
     * are not boolean operations to a set of terms.
     * 
     * @param node is an ASTNode object and the root node of the tree we will
     * be traversing and terms is an empty TreeSet that the terms with be
     * stored and sorted alphabetically in.
     * 
     * @return no return value, just fills the TreeSet with Strings
     */
    public static void treeTraversal(ASTNode node, TreeSet<String> terms) {
        // base case is node == null, this isn't spelled out since nothing
        // happens in the base case
        if (node != null) {// recursive case: there are unvisited children
            treeTraversal(node.child1, terms);// goes through left half
            treeTraversal(node.child2, terms);// the right half
            if (node.isId()) {// only adds non-operator terms
                terms.add(node.getId());
            }
        }
    }

    /*
     * Creates a truth table for the terms in the boolean expression and fills
     * a dictionary with all the possible boolean value combinations.
     * 
     * @param truthTable is a list of dictionaries containing the terms mapped
     * to a boolean value, guesses is a dictionary containing terms mapped to
     * a boolean value(truthTable stores copies of guesses), terms is a list
     * of all the non-operator terms in the boolean expression, and bool is an
     * array with all the possible boolean values(either true or false).
     * 
     * @return No return value. This function just modifies the contents of
     * truthTable and guesses.
     */
    public static void getTruthTable(
            ArrayList<HashMap<String, Boolean>> truthTable,
            HashMap<String, Boolean> guesses, ArrayList<String> terms,
            boolean[] bool) {
        // base case: all terms have been assigned a boolean value and now the
        // terms list is empty
        if (terms.size() == 0) {
            // new dictionary is created so the choices we made aren't erased
            HashMap<String, Boolean> dict = new HashMap<String, Boolean>(
                    guesses);
            truthTable.add(dict);// adds current choices to list truthTable
        } else {// recursive cases: we haven't made a choice for all the terms
            // loops through all possible choices, in this case true or false
            for (int i = 0; i <= 1; i++) {
                String term = terms.remove(0);
                guesses.put(term, bool[i]);// make choice
                getTruthTable(truthTable, guesses, terms, bool);// explore
                terms.add(0, term);// undo choice
            }
        }
    }

    /*
     * This function prints the truth table for our boolean expression, along
     * with the help of the function eval. The first line is the boolean
     * expression from the input file, the second line is whether the
     * expression is satisfiable(SAT) or not(UNSAT), and all the lines below
     * it are the boolean values associated with each term along with whether
     * or not that combination of values evaluates to true or not(only combos
     * that evaluate to true are printed if debug mode is activated).
     * 
     * @param expr is the boolean expression provided in the input file,
     * truthTable is the list of dictionaries that represents the truth table
     * for the expression, node is the root node of the boolean expression
     * tree, and debug is a boolean value that is true when debug mode is
     * activated.
     * 
     * @return No return value. The function just prints out the contents of
     * it's parameters and whether or not the expression is satisfiable.
     */
    public static void printTruthTable(String expr,
            ArrayList<HashMap<String, Boolean>> truthTable, ASTNode node,
            boolean debug) {
        String sat = "UNSAT";
        String results = "";
        for (HashMap<String, Boolean> dict : truthTable) {
            ArrayList<String> sortedKeys = new ArrayList<String>(dict.keySet());
            Collections.sort(sortedKeys);
            boolean boolAns = eval(dict, node);
            if (boolAns || debug) {
                ArrayList<String> keys = new ArrayList<String>(sortedKeys);
                for (int i = 0; i < keys.size() - 1; i++) {
                    boolean value = dict.get(keys.get(i));
                    results += keys.get(i) + ": " + value + ", ";
                }
                results += keys.get(keys.size() - 1) + ": "
                        + dict.get(keys.get(keys.size() - 1));
                if (debug) {
                    results += ", " + boolAns;
                }
                results += "\n";
                if (boolAns) {
                    sat = "SAT";
                }
            }
        }
        results = "input: " + expr + "\n" + sat + "\n" + results;
        System.out.println(results);
    }

    public static boolean eval(HashMap<String, Boolean> values, ASTNode node) {
        if (node.child1 == null && node.child2 == null) {
            return values.get(node.getId());
        } else {
            ASTNode left = node.child1;
            ASTNode right = node.child2;
            boolean leftTerm = eval(values, left);
            boolean rightTerm = eval(values, right);
            return !(leftTerm && rightTerm);
        }
    }
}