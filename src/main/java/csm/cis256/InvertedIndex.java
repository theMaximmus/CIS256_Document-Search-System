package csm.cis256;

public class InvertedIndex {

    private HashMap<String, LinkedList<DocData>> index;

    public InvertedIndex() {
        index = new HashMap<>();
    }

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