package csm.cis256;

/**
 * Manages the navigation history for the search engine (Back/Forward functionality).
 *
 * This class implements a "Two-Stack" architecture commonly used in web browsers:
 * Back Stack: Stores pages we have visited and left.
 * Forward Stack: Stores pages we popped off the Back Stack (revisited).
 */
public class HistoryManager {
    // Stack containing previous documents (History).
    private ArrayStack<String> backStack;
    // Stack containing documents we backed out of (Future).
    private ArrayStack<String> forwardStack;
    // The document currently being viewed.
    // This is separate from the stacks because the current page is "active," not "history."
    private String current;

    public HistoryManager() {
        backStack = new ArrayStack<>();
        forwardStack = new ArrayStack<>();
        current = null;
    }

    /**
     * Records a new page view.
     *
     * When a user navigates to a new page (not via back/forward), two things happen:
     * 1) The current page is pushed onto the Back Stack.
     * 2) The Forward Stack is cleared (because a new timeline has started).
     *
     * @param item The document name or search query to record.
     */
    public void view(String item) {
        if (item == null) return;
        // If we were looking at something, save it to history before switching
        if (current != null) {
            backStack.push(current);
        }

        current = item;

        // Clear forward history when a new view occurs
        while (!forwardStack.isEmpty()) {
            forwardStack.pop();
        }
    }

    /**
     * Navigates to the previous page.
     *
     * Logic:
     * 1) Push the current page onto the Forward Stack (so we can return to it).
     * 2) Pop the previous page from the Back Stack and make it active.
     *
     * @return The page we moved back to, or the current page if history is empty.
     */
    public String back() {
        if (backStack.isEmpty()) {
            return current; // Nowhere to go
        }
        // Save current state to "Forward" before leaving
        forwardStack.push(current);
        // Restore state from "Back"
        current = backStack.pop();
        return current;
    }

    /**
     * Navigates to the next page (if available).
     *
     * Logic:
     * 1) Push the current page onto the Back Stack.
     * 2) Pop the next page from the Forward Stack and make it active.
     *
     * @return The page we moved forward to, or the current page if forward history is empty.
     */
    public String forward() {
        if (forwardStack.isEmpty()) {
            return current; // Nowhere to go
        }
        // Save current state to "Back"
        backStack.push(current);
        // Restore state from "Forward"
        current = forwardStack.pop();
        return current;
    }

    /**
     * Returns the item currently being viewed.
     * @return The current document name or query string.
     */
    public String getCurrent() {
        return current;
    }
}
