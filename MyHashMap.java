import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyHashMap<K, V> {
    private List<ArrayList<Pair<K, V>>> myList;
    private Set<K> keySet;
    private int numBuckets;

    public MyHashMap() {
        myList = new ArrayList<ArrayList<Pair<K, V>>>();
        keySet = new HashSet<K>();
        for (int i = 0; i < 8; i++) {
            myList.add(new ArrayList<Pair<K, V>>());
        }
        numBuckets = myList.size();
    }

    public void put(K key, V value) {
        int index = hash(key);
        if (containsKey(key)) {
            update(key, value);
        } else {
            myList.get(index).add(0, new Pair<K, V>(key, value));
            keySet.add(key);
        }
    }

    private void update(K key, V value) {
        int index = hash(key);
        for (Pair<K, V> pair : myList.get(index)) {
            if (pair.getKey().equals(key)) {
                pair.changeValue(value);
            }
        }
    }

    public V get(K key) {
        int index = hash(key);
        for (Pair<K, V> pair : myList.get(index)) {
            if (pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null;
    }

    private int hash(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        return Math.abs(index);
    }

    public boolean containsKey(K key) {
        for (ArrayList<Pair<K, V>> bucket : myList) {
            for (Pair<K, V> pair : bucket) {
                if (pair.getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<K> keySet() {
        return keySet;
    }

    public java.lang.String toString() {
        int index = 0;
        int conflicts = 0;
        java.lang.String dict = "";
        dict += "DEBUG output for MyTable\n";
        dict += "-------------------------------------\n";
        for (ArrayList<Pair<K, V>> bucket : myList) {
            dict += "Index " + index + ": ";
            int bucketSize = bucket.size() - 1;
            if (bucket.size() < 1) {
                dict += "(0 conflicts), ";
            } else {
                dict += "(" + bucketSize + " conflicts), ";
                conflicts += bucketSize;
            }
            dict += "[";
            for (Pair<K, V> pair : bucket) {
                dict += pair.getKey() + ", ";
            }
            dict += "]\n";
            index += 1;
        }
        dict += "Total # of conflicts: " + conflicts;
        return dict;
    }

    public int size() {
        return keySet.size();
    }
}
