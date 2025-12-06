package csm.cis256;

import java.io.File;

public class SearchEngine {

    private InvertedIndex index;
    private HistoryManager history;

    public SearchEngine() {
        index = new InvertedIndex();
        history = new HistoryManager();
    }

    public void indexDirectory(String folderName) {
        File folder = new File(folderName);
        if (!folder.exists() || !folder.isDirectory()) {
            return;
        }

        File[] files = folder.listFiles();
        if (files == null) {
            return;
        }

        for (File f : files) {
            if (f.isFile()) {
                LinkedList<String> tokens = Tokenizer.tokenizeFile(f.getPath());
                index.addDocument(f.getName(), tokens);
            }
        }
    }

    public LinkedList<String> searchOneWord(String word) {
        if (word == null) {
            return new LinkedList<>();
        }
        history.view("SEARCH: " + word);

        // Get Raw Data
        LinkedList<DocData> rawResults = index.getDocuments(word);

        // Sort Results
        return rankAndFormat(rawResults);
    }

    public LinkedList<String> searchAllWords(String wordsInput) {
        if (wordsInput == null || wordsInput.trim().isEmpty()) {
            return new LinkedList<>();
        }
        history.view("SEARCH: " + wordsInput);

        String[] words = wordsInput.toLowerCase().split("\\s+");
        LinkedList<DocData> result = index.getDocuments(words[0]);

        // Intersect results for subsequent words
        for (int i = 1; i < words.length; i++) {
            LinkedList<DocData> nextDocs = index.getDocuments(words[i]);
            result = intersect(result, nextDocs);
        }

        return rankAndFormat(result);
    }

    // Sorts DocData and converts to List<String>
    private LinkedList<String> rankAndFormat(LinkedList<DocData> rawData) {
        if (rawData.isEmpty()) {
            return new LinkedList<>();
        }

        // Convert to Array for Sorting
        DocData[] arr = new DocData[rawData.size()];
        ListNode<DocData> curr = rawData.getHead();
        int i = 0;
        while (curr != null) {
            arr[i++] = curr.data;
            curr = curr.next;
        }

        DocDataSorter.sort(arr);

        // Convert back to LinkedList<String> (names only)
        LinkedList<String> sortedNames = new LinkedList<>();
        for (DocData d : arr) {
            sortedNames.add(d.docId);
        }
        return sortedNames;
    }

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