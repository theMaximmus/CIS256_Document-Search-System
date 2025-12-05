package csm.cis256;

import java.io.File;

public class SearchEngine {

    private InvertedIndex index;
    private HistoryManager history;

    public SearchEngine() {
        index = new InvertedIndex();
        history = new HistoryManager();
    }

    // -------------------------------
    // INDEXING
    // -------------------------------
    public void indexDirectory(String folderName) {
        File folder = new File(folderName);
        if (!folder.exists() || !folder.isDirectory()) {
            return;
        }

        File[] files = folder.listFiles();
        if (files == null) return;

        for (File f : files) {
            if (f.isFile()) {
                LinkedList<String> tokens = Tokenizer.tokenizeFile(f.getPath());
                index.addDocument(f.getName(), tokens);
            }
        }
    }

    // -------------------------------
    // SINGLE WORD SEARCH
    // -------------------------------
    public LinkedList<String> searchOneWord(String word) {
        if (word == null) {
            history.view("SEARCH: null");
            return new LinkedList<>();
        }

        history.view("SEARCH: " + word);
        return index.getDocuments(word);
    }

    // -------------------------------
    // MULTI-WORD SEARCH
    // -------------------------------
    public LinkedList<String> searchAllWords(String wordsInput) {
        if (wordsInput == null || wordsInput.trim().isEmpty()) {
            history.view("SEARCH: (empty)");
            return new LinkedList<>();
        }

        // tests expect: convert string into individual tokens
        String[] words = wordsInput.toLowerCase().split("\\s+");

        history.view("SEARCH: " + wordsInput);

        // Start with documents that contain the FIRST word
        LinkedList<String> result = index.getDocuments(words[0]);

        // For each next word, intersect results
        for (int i = 1; i < words.length; i++) {
            String w = words[i];
            LinkedList<String> docs = index.getDocuments(w);
            result = intersect(result, docs);
        }

        return result;
    }

    // -------------------------------
    // INTERSECTION HELPER
    // -------------------------------
    private LinkedList<String> intersect(LinkedList<String> a, LinkedList<String> b) {
        LinkedList<String> out = new LinkedList<>();

        ListNode<String> n = a.getHead();
        while (n != null) {
            if (contains(b, n.data)) {
                out.append(n.data);
            }
            n = n.next;
        }

        return out;
    }

    private boolean contains(LinkedList<String> list, String target) {
        ListNode<String> n = list.getHead();
        while (n != null) {
            if (n.data.equals(target)) return true;
            n = n.next;
        }
        return false;
    }

    // -------------------------------
    // HISTORY ACCESS
    // -------------------------------
    public HistoryManager getHistory() {
        return history;
    }
}
