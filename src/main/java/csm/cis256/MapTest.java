package csm.cis256;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapTests {

    @Test
    void testBSTMap() {
        BSTMap<String, Integer> map = new BSTMap<>();
        runMapTests(map);
    }

    @Test
    void testAVLTreeMap() {
        AVLTreeMap<String, Integer> map = new AVLTreeMap<>();
        runMapTests(map);
    }

    @Test
    void testRedBlackTreeMap() {
        RedBlackTreeMap<String, Integer> map = new RedBlackTreeMap<>();
        runMapTests(map);
    }

    @Test
    void testHashMap() {
        HashMap<String, Integer> map = new HashMap<>();
        assertTrue(map.isEmpty());

        map.put("One", 1);
        assertEquals(1, map.get("One"));

        map.put("One", 100); // Update
        assertEquals(100, map.get("One"));

        map.remove("One");
        assertNull(map.get("One"));
    }

    private void runMapTests(Object mapObj) {
        if (mapObj instanceof BSTMap) {
            BSTMap<String, Integer> map = (BSTMap<String, Integer>) mapObj;
            assertTrue(map.isEmpty());
            map.put("A", 1);
            map.put("B", 2);
            assertEquals(1, map.get("A"));
            assertEquals(2, map.get("B"));
            assertNull(map.get("C")); // Miss
            map.put("A", 99); // Update
            assertEquals(99, map.get("A"));
        } else if (mapObj instanceof AVLTreeMap) {
            AVLTreeMap<String, Integer> map = (AVLTreeMap<String, Integer>) mapObj;
            assertTrue(map.isEmpty());
            map.put("A", 1);
            assertEquals(1, map.get("A"));
            map.put("A", 99);
            assertEquals(99, map.get("A"));
        } else if (mapObj instanceof RedBlackTreeMap) {
            RedBlackTreeMap<String, Integer> map = (RedBlackTreeMap<String, Integer>) mapObj;
            assertTrue(map.isEmpty());
            map.put("A", 1);
            assertEquals(1, map.get("A"));
            map.put("A", 99);
            assertEquals(99, map.get("A"));
        }
    }
}