import java.util.ArrayList;
import java.util.List;

public class MyHashMap {
    private List<ArrayList<Integer>> myList;
    private int numBuckets;

    public MyHashMap() {
        myList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 8; i++) {
            myList.add(new ArrayList<>());
        }
        numBuckets = myList.size();
    }

    public void hashPut(String key, int value) {
        int i = hash(key);
        myList.get(i).add(value);
    }

    public int hashGet(String key) {
        int i = hash(key);
        return myList.get(i).get(0);
    }

    private int hash(String key) {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        return Math.abs(index);
    }
}

