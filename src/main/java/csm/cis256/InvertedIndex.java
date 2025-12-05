package csm.cis256;

public class InvertedIndex {

    private HashMap<String, LinkedList<String>> index;

    public InvertedIndex() {
        index = new HashMap<>();
    }

    public void addDocument(String docName, LinkedList<String> words) {
        if (docName == null || words == null || words.size() == 0) return;

        ListNode<String> curr = words.getHead();
        if (curr == null) return; // SAFETY — prevents NPE

        while (curr != null) {

            String w = curr.data;
            if (w != null) {
                w = w.toLowerCase();

                LinkedList<String> docs = index.get(w);
                if (docs == null) {
                    docs = new LinkedList<>();
                    index.put(w, docs);
                }

                // only add once
                if (!contains(docs, docName)) {
                    docs.add(docName);
                }
            }

            curr = curr.next;
        }
    }

    private boolean contains(LinkedList<String> list, String value) {
        if (list == null || list.size() == 0) return false;

        ListNode<String> n = list.getHead();
        while (n != null) {
            if (value.equals(n.data)) return true;
            n = n.next;
        }
        return false;
    }

    public LinkedList<String> getDocuments(String word) {
        if (word == null) return new LinkedList<>();

        LinkedList<String> result = index.get(word.toLowerCase());
        if (result == null) return new LinkedList<>();
        return result;
    }
}
