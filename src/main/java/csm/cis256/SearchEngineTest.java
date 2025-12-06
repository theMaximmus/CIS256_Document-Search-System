package csm.cis256;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class SearchEngineTest {

    @Test
    void testTokenizer() throws IOException {
        // Create a temporary file
        File temp = File.createTempFile("test_doc", ".txt");
        FileWriter writer = new FileWriter(temp);
        writer.write("Hello... World? HELLO!");
        writer.close();

        LinkedList<String> tokens = Tokenizer.tokenizeFile(temp.getAbsolutePath());

        // Expected: hello, world, hello
        assertEquals(3, tokens.size());
        assertEquals("hello", tokens.peekHead());
        assertEquals("hello", tokens.peekTail());
    }

    @Test
    void testSearchEngineLogic() {
        InvertedIndex index = new InvertedIndex();
        LinkedList<String> doc1Words = new LinkedList<>();
        doc1Words.add("apple");
        doc1Words.add("banana");

        LinkedList<String> doc2Words = new LinkedList<>();
        doc2Words.add("banana");
        doc2Words.add("cherry");

        index.addDocument("doc1", doc1Words);
        index.addDocument("doc2", doc2Words);

        // Search "apple" -> doc1
        LinkedList<DocData> res1 = index.getDocuments("apple");
        assertEquals(1, res1.size());
        // We must now access .docId because the list contains DocData objects
        assertEquals("doc1", res1.peekHead().docId);

        // Search "banana" -> doc1, doc2
        LinkedList<DocData> res2 = index.getDocuments("banana");
        assertEquals(2, res2.size());

        // Search "cherry" -> doc2
        LinkedList<DocData> res3 = index.getDocuments("cherry");
        assertEquals(1, res3.size());
        assertEquals("doc2", res3.peekHead().docId);
    }

    @Test
    void testSearchRanking() {
        InvertedIndex index = new InvertedIndex();

        LinkedList<String> doc1Words = new LinkedList<>();
        doc1Words.add("apple"); doc1Words.add("apple"); doc1Words.add("apple");

        LinkedList<String> doc2Words = new LinkedList<>();
        doc2Words.add("apple");

        LinkedList<String> doc3Words = new LinkedList<>();
        doc3Words.add("apple"); doc3Words.add("apple"); doc3Words.add("apple");
        doc3Words.add("apple"); doc3Words.add("apple");

        index.addDocument("doc1", doc1Words);
        index.addDocument("doc2", doc2Words);
        index.addDocument("doc3", doc3Words);

        LinkedList<DocData> results = index.getDocuments("apple");

        // Convert LinkedList to Array to use Sorter for verification
        DocData[] arr = new DocData[results.size()];
        int i = 0;
        ListNode<DocData> curr = results.getHead();
        while(curr != null) {
            arr[i++] = curr.data;
            curr = curr.next;
        }

        DocDataSorter.sort(arr);

        assertEquals("doc3", arr[0].docId);
        assertEquals("doc1", arr[1].docId);
        assertEquals("doc2", arr[2].docId);
    }
}