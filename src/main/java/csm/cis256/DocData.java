package csm.cis256;

public class DocData implements Comparable<DocData> {
    public String docId;
    public int frequency;

    public DocData(String docId, int frequency) {
        this.docId = docId;
        this.frequency = frequency;
    }

    // Sorts by frequency in DESCENDING order
    @Override
    public int compareTo(DocData other) {
        return Integer.compare(other.frequency, this.frequency);
    }

    @Override
    public String toString() {
        return docId + "(" + frequency + ")";
    }
}