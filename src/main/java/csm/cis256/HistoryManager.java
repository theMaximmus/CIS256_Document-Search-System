package csm.cis256;

public class HistoryManager {

    private ArrayStack<String> backStack;
    private ArrayStack<String> forwardStack;
    private String current;

    public HistoryManager() {
        backStack = new ArrayStack<>();
        forwardStack = new ArrayStack<>();
        current = null;
    }

    // User views a new page/search
    public void view(String item) {
        if (item == null) return;

        if (current != null) {
            backStack.push(current);
        }

        current = item;

        // Clear forward history when a new view occurs
        while (!forwardStack.isEmpty()) {
            forwardStack.pop();
        }
    }

    public String back() {
        if (backStack.isEmpty()) return current;

        forwardStack.push(current);
        current = backStack.pop();
        return current;
    }

    public String forward() {
        if (forwardStack.isEmpty()) return current;

        backStack.push(current);
        current = forwardStack.pop();
        return current;
    }

    public String getCurrent() {
        return current;
    }
}
