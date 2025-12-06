package csm.cis256;

import org.junit.jupiter.api.Test;

import java.sql.Array;

import static org.junit.jupiter.api.Assertions.*;

public class DataStructuresTests {

    // LinkedList Tests

    @Test
    void testLinkedListAddAndSize() {
        LinkedList<String> list = new LinkedList<>();
        assertTrue(list.isEmpty());

        list.add("A");
        list.add("B");

        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertEquals("A", list.peekHead());
        assertEquals("B", list.peekTail());
    }

    @Test
    void testLinkedListRemove() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // Remove head (1)
        list.removeHead();
        assertEquals(2, list.peekHead());
        assertEquals(2, list.size());

        // Remove tail (3)
        list.removeTail();
        assertEquals(2, list.peekTail());
        assertEquals(1, list.size());

        // Remove last element
        list.removeHead();
        assertTrue(list.isEmpty());
    }

    @Test
    void testLinkedListRemoveAt() {
        LinkedList<String> list = new LinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        list.removeAt(1); // Remove "B"

        assertEquals(2, list.size());
        assertEquals("A", list.peekHead());
        assertEquals("C", list.peekTail());
    }

    // Stack Tests

    @Test
    void testStackPushPop() {
        ArrayStack<String> stack = new ArrayStack<>();
        assertTrue(stack.isEmpty());

        stack.push("First");
        stack.push("Second");

        assertEquals(2, stack.size());
        assertEquals("Second", stack.peek());

        assertEquals("Second", stack.pop());
        assertEquals("First", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testStackUnderflow() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        assertNull(stack.pop(), "Popping empty stack should return null or handle gracefully");
    }

    // Queue Tests

    @Test
    void testQueueEnqueueDequeue() {
        ListQueue<String> queue = new ListQueue<>();
        assertTrue(queue.isEmpty());

        queue.enqueue("First");
        queue.enqueue("Second");

        assertEquals(2, queue.size());
        assertEquals("First", queue.front());

        assertEquals("First", queue.dequeue());
        assertEquals("Second", queue.front());
        assertEquals("Second", queue.dequeue());
        assertTrue(queue.isEmpty());
    }
}
