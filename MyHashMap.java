import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyHashMap<String, Integer> {
    private List<ArrayList<Pair<String, Integer>>> myList;
    private Set<String> keySet;
    private int numBuckets;

    public MyHashMap() {
        myList = new ArrayList<ArrayList<Pair<String, Integer>>>();
        keySet = new HashSet<String>();
        for (int i = 0; i < 8; i++) {
            myList.add(new ArrayList<>());
        }
        numBuckets = myList.size();
    }

    public void put(String key, Integer value) {
        int index = hash(key);
        myList.get(index).add(new Pair<String, Integer>(key, value));
        keySet.add(key);
    }

    public Integer get(String key) {
        int index = hash(key);
        keySet.remove(key);
        for (Pair<String, Integer> pair : myList.get(index)) {
            if (pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null;
    }

    private int hash(String key) {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        return Math.abs(index);
    }

    public boolean containsKey(String key) {
        return keySet.contains(key);
    }

    public Set<String> keySet() {
        return keySet;
    }

    public int size() {
        return keySet.size();
    }
}
