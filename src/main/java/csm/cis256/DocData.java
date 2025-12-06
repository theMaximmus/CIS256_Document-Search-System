package csm.cis256;

/**
 * A data container representing a document match.
 *
 * It holds the Document ID (filename).
 * It tracks the Frequency (how many times the search term appeared).
 */
public class DocData implements Comparable<DocData> {
    public String docId;
    public int frequency;

    public DocData(String docId, int frequency) {
        this.docId = docId;
        this.frequency = frequency;
    }

    /**
     * Defines the "natural ordering" for documents in search results.
     * Sorting Logic:
     * Negative if a < b
     * Positive if a > b
     * @param other The object to compare against.
     * @return A positive integer if this object has a lower frequency than 'other'.
     */

    @Override
    public int compareTo(DocData other) {
        // Sorts by frequency in DESCENDING order
        return Integer.compare(other.frequency, this.frequency);
    }

    @Override
    public String toString() {
        return docId + "(" + frequency + ")";
    }
}