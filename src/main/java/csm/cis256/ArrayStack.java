package csm.cis256;

public class ArrayStack<T> {

    private T[] data;
    private int top;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        data = (T[]) new Object[1000];
        top = 0;
    }

    public void push(T item) {
        if (top == data.length - 1) resize();
        data[top++] = item;
    }

    public T pop() {
        if (top == 0) return null;
        return data[--top];
    }

    public T peek() {
        if (top == 0) return null;
        return data[top - 1];
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public int size() {
        return top;
    }

    // ⭐ Add this — required for HistoryManager
    public void clear() {
        top = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newData = (T[]) new Object[data.length + 1000];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }
}
