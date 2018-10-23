import org.junit.Test;

public class PA7Test {
    // Tests put() adding one element
    @Test
    public void testPut1() {
        MyHashMap m = new MyHashMap<String, Integer>();
        m.put("One", 1);
        assert (m.size() == 1);
    }

    // Tests put() adding multiple elements
    @Test
    public void testPut2() {
        MyHashMap m = new MyHashMap<String, Integer>();
        m.put("Three", 3);
        m.put("Two", 2);
        m.put("One", 1);
        assert (m.size() == 3);
    }

    // Tests get() with key that's in the map
    @Test
    public void testGet1() {
        MyHashMap m = new MyHashMap<String, Integer>();
        m.put("One", 1);
        assert (m.get("One") == (Integer) 1);
    }

    // Tests get() to make sure it handles cases where the key isn't in the map
    @Test
    public void testGet2() {
        MyHashMap m = new MyHashMap<String, Integer>();
        m.put("One", 1);
        assert (m.get("Two") == null);
    }

    // Tests whether containsKey() works with a map with multiple elements
    @Test
    public void testContainsKey1() {
        MyHashMap m = new MyHashMap<String, Integer>();
        m.put("Three", 3);
        m.put("Two", 2);
        m.put("One", 1);
        assert (m.containsKey("Three"));
        assert (m.containsKey("Two"));
        assert (m.containsKey("One"));
    }

    // Tests whether containsKey() works when it's passed a key that's not in
    // the map
    @Test
    public void testContainsKey2() {
        MyHashMap m = new MyHashMap<String, Integer>();
        m.put("One", 1);
        assert (m.containsKey("Two") == false);
    }

    // Tests whether keySet() is correct size for a map with no elements
    @Test
    public void testKeySet1() {
        MyHashMap m = new MyHashMap<String, Integer>();
        assert (m.keySet().size() == 0);
    }

    // Tests whether keySet() is correct size for a map with one element
    @Test
    public void testKeySet2() {
        MyHashMap m = new MyHashMap<String, Integer>();
        m.put("One", 1);
        assert (m.keySet().size() == 1);
    }

    // Tests whether keySet() is correct size for a map with many elements
    @Test
    public void testKeySet3() {
        MyHashMap m = new MyHashMap<String, Integer>();
        m.put("Three", 3);
        m.put("Two", 2);
        m.put("One", 1);
        assert (m.keySet().size() == 3);
    }
}
