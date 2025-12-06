package csm.cis256;

import java.io.File;

/**
 * The core controller for the Document Search System.
 *
 * This class orchestrates the interaction between the data storage (InvertedIndex),
 * the user session state (HistoryManager), and the query processing logic.
 * It is responsible for:
 * 1) Crawling a directory and indexing files.
 * 2) Parsing user queries (single or multi-word).
 * 3) Retrieving raw data from the index and ranking it by relevance.
 * 4) Tracking the user's search history.
 */
public class SearchEngine {
    // The central database mapping words -> documents
    private InvertedIndex index;
    // Tracks navigation (back/forward) for the user session
    private HistoryManager history;

    public SearchEngine() {
        index = new InvertedIndex();
        history = new HistoryManager();
    }

    /**
     * Crawls the specified directory and populates the Inverted Index.
     *
     * This method reads every file in the folder, tokenizes its content using
     * the Tokenizer utility, and feeds the resulting word lists into the index.
     * It is typically called once at the start of the program.
     *
     * @param folderName The path to the directory containing text files.
     */
    public void indexDirectory(String folderName) {
        // Ensuring the folder actually exists and is a directory
        File folder = new File(folderName);
        if (!folder.exists() || !folder.isDirectory()) {
            return;
        }

        File[] files = folder.listFiles();
        if (files == null) {
            return;
        }

        // Process each file found
        for (File f : files) {
            if (f.isFile()) {
                LinkedList<String> tokens = Tokenizer.tokenizeFile(f.getPath());
                index.addDocument(f.getName(), tokens);
            }
        }
    }

    /**
     * Executes a search for a single term.
     *
     * @param word The search term.
     * @return A list of document names, sorted by relevance (frequency).
     */
    public LinkedList<String> searchOneWord(String word) {
        if (word == null) {
            return new LinkedList<>();
        }
        // Log this action in the history
        history.view("SEARCH: " + word);

        // Get Raw Data
        LinkedList<DocData> rawResults = index.getDocuments(word);

        // Sort Results
        return rankAndFormat(rawResults);
    }

    /**
     * Executes a search for multiple terms (AND query).
     *
     * For a document to be returned, it must contain ALL the words in the query.
     * The relevance score is calculated by summing the frequencies of all terms
     * within that document.
     *
     * @param wordsInput The raw query string (e.g., "quick brown fox").
     * @return A list of document names, sorted by combined relevance.
     */
    public LinkedList<String> searchAllWords(String wordsInput) {
        if (wordsInput == null || wordsInput.trim().isEmpty()) {
            return new LinkedList<>();
        }
        history.view("SEARCH: " + wordsInput);

        // Split query into individual terms
        String[] words = wordsInput.toLowerCase().split("\\s+");
        // Start with the result set of the first word
        LinkedList<DocData> result = index.getDocuments(words[0]);

        // Intersect this set with the results of every subsequent word.
        for (int i = 1; i < words.length; i++) {
            LinkedList<DocData> nextDocs = index.getDocuments(words[i]);
            result = intersect(result, nextDocs);
        }

        return rankAndFormat(result);
    }

    /**
     * Converts raw DocData objects into a user-friendly list of strings.
     * It also performs the ranking (sorting) based on frequency.
     *
     * @param rawData The list of Document+Frequency objects.
     * @return A sorted list of document names.
     */
    private LinkedList<String> rankAndFormat(LinkedList<DocData> rawData) {
        if (rawData.isEmpty()) {
            return new LinkedList<>();
        }

        // Dump everything into an array for sorting
        DocData[] arr = new DocData[rawData.size()];
        ListNode<DocData> curr = rawData.getHead();
        int i = 0;
        while (curr != null) {
            arr[i++] = curr.data;
            curr = curr.next;
        }

        DocDataSorter.sort(arr);

        // Convert back to a simple List of names for the caller
        LinkedList<String> sortedNames = new LinkedList<>();
        for (DocData d : arr) {
            sortedNames.add(d.docId);
        }
        return sortedNames;
    }

    /**
     * Intersects two lists of documents, keeping only those present in both.
     * It sums their frequencies to create a "Relevance Score".
     *
     * @param listA Results from previous words
     * @param listB Results from the current word
     * @return A new list containing documents found in both A and B, with combined scores.
     */
    private LinkedList<DocData> intersect(LinkedList<DocData> listA, LinkedList<DocData> listB) {
        LinkedList<DocData> out = new LinkedList<>();

        ListNode<DocData> nodeA = listA.getHead();
        while (nodeA != null) {
            // Check if this doc exists in listB
            ListNode<DocData> nodeB = listB.getHead();
            while (nodeB != null) {
                if (nodeA.data.docId.equals(nodeB.data.docId)) {
                    // Combine frequencies: Score = FreqA + FreqB
                    int combinedFreq = nodeA.data.frequency + nodeB.data.frequency;
                    out.add(new DocData(nodeA.data.docId, combinedFreq));
                    break;
                }
                nodeB = nodeB.next;
            }
            nodeA = nodeA.next;
        }
        return out;
    }

    public HistoryManager getHistory() {
        return history;
    }
}