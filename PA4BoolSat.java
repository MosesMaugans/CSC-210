import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
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
        TreeSet<String> children = new TreeSet<String>();
        // System.out.println(root.toDotString());
        treeTraversal(root, children);
        TreeMap<String, Boolean> guesses = new TreeMap<String, Boolean>();
        Boolean[] options = { false, true };
        ArrayList<String> childset = new ArrayList<String>(children);
        bools(childset, guesses, options, root);
    }

    public static void treeTraversal(ASTNode node, TreeSet<String> children) {
        if (node != null) {
            treeTraversal(node.child1, children);
            treeTraversal(node.child2, children);
            if (!node.isNand()) {
                children.add(node.getId());
            }
        }
    }

    public static void bools(ArrayList<String> childset,
            TreeMap<String, Boolean> guesses, Boolean[] options, ASTNode node) {
        if (childset.size() == 0) {
            printGuesses(guesses);
            // System.out.println(eval(node, guesses));
        } else {
            for (int i = 0; i <= 1; i++) {
                guesses.put(childset.get(childset.size() - 1), options[i]);
                String id = childset.remove(childset.size() - 1);
                bools(childset, guesses, options, node);
                childset.add(id);
            }
        }
    }

    public static void printGuesses(TreeMap<String, Boolean> guesses) {
        for (String id : guesses.keySet()) {
            System.out.print(id + ": " + guesses.get(id) + ", ");
        }
    }

    public static boolean eval(ASTNode node, TreeMap<String, Boolean> guesses) {
        if (!node.child1.isNand() && !node.child2.isNand()) {
            boolean child1 = guesses.get(node.child1.getId());
            boolean child2 = guesses.get(node.child2.getId());
            boolean val = !(child1 && child2);
            return val;
        } else {
            eval(node.child1, guesses);
            eval(node.child2, guesses);
        }
        return false;
    }
}