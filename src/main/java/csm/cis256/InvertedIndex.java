package csm.cis256;

/**
 * An Inverted Index implementation.
 *
 * Structure:
 * Key -> The word (String)
 * Value -> A list of "postings" (LinkedList of DocData), where each entry contains
 * the Document ID and the frequency of the word in that document.
 */
public class InvertedIndex {
    // Map from Word -> List of Document Occurrences.
    private HashMap<String, LinkedList<DocData>> index;

    public InvertedIndex() {
        index = new HashMap<>();
    }

    /**
     * Indexes a single document by processing its tokens.
     *
     * For every word in the document, we update the index:
     * 1) If the word is new to the index, we create a new entry.
     * 2) If the word exists, we check if this specific document is already recorded.
     * - If yes, we increment the frequency count for that document.
     * - If no, we add a new DocData entry for this document.
     *
     * @param docName The unique identifier (name) of the document.
     * @param words The list of tokens (words) extracted from the document.
     */
    public void addDocument(String docName, LinkedList<String> words) {
        if (docName == null || words == null || words.size() == 0) return;

        ListNode<String> curr = words.getHead();
        while (curr != null) {
            String w = curr.data;
            if (w != null) {
                w = w.toLowerCase();

                LinkedList<DocData> docs = index.get(w);
                if (docs == null) {
                    docs = new LinkedList<>();
                    index.put(w, docs);
                }

                // Check if document already exists in the list
                boolean found = false;
                ListNode<DocData> docNode = docs.getHead();
                while (docNode != null) {
                    if (docNode.data.docId.equals(docName)) {
                        docNode.data.frequency++; // Increment frequency
                        found = true;
                        break;
                    }
                    docNode = docNode.next;
                }

                // If not found, add new DocData entry
                if (!found) {
                    docs.add(new DocData(docName, 1));
                }
            }
            curr = curr.next;
        }
    }

    /**
     * Retrieves the list of documents containing the specified word.
     *
     * @param word The search term.
     * @return A list of DocData objects (Document ID + Frequency), or an empty list if not found.
     */
    public LinkedList<DocData> getDocuments(String word) {
        if (word == null) {
            return new LinkedList<>();
        }

        LinkedList<DocData> result = index.get(word.toLowerCase());
        if (result == null) {
            return new LinkedList<>();
        }

        return result;
    }
}