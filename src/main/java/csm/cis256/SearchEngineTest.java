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
        assertEquals("hello", tokens.peekTail()); // if append order is maintained
    }

    @Test
    void testSearchEngineLogic() {
        SearchEngine engine = new SearchEngine();

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
        LinkedList<String> res1 = index.getDocuments("apple");
        assertEquals(1, res1.size());
        assertEquals("doc1", res1.peekHead());

        // Search "banana" -> doc1, doc2
        LinkedList<String> res2 = index.getDocuments("banana");
        assertEquals(2, res2.size());

        // Search "cherry" -> doc2
        LinkedList<String> res3 = index.getDocuments("cherry");
        assertEquals(1, res3.size());
        assertEquals("doc2", res3.peekHead());
    }
}