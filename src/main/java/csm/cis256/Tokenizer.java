package csm.cis256;

import java.io.File;
import java.util.Scanner;

/**
 * A utility class responsible for reading text files and converting them into
 * a sequence of normalized tokens (words).
 * Tokenization is the first step in indexing. It involves:
 * 1) Reading raw text from a file.
 * 2) Splitting text into individual words (using whitespace).
 * 3) Normalizing words (converting to lowercase, stripping punctuation)
 * so that "Apple", "apple!", and "apple" are treated as the same term.
 */
public class Tokenizer {

    /**
     * Reads a file and splits it into a list of normalized words.
     * This method uses a standard Scanner to parse the file token by token.
     * It relies on the custom LinkedList class to store the results sequentially.
     *
     * @param filename The relative or absolute path to the text file.
     * @return A LinkedList containing the clean words found in the file.
     */
    public static LinkedList<String> tokenizeFile(String filename) {
        LinkedList<String> list = new LinkedList<>();

        try {
            File f = new File(filename);
            // Scanner automatically splits by whitespace (spaces, tabs, newlines)
            Scanner sc = new Scanner(f);

            while (sc.hasNext()) {
                String word = sc.next().toLowerCase().replaceAll("[^a-z0-9]", "");
                // Only add the token if it's not empty
                if (!word.isEmpty()) {
                    list.append(word);
                }
            }

            sc.close();
        } catch (Exception e) {
            System.out.println("Error reading: " + filename);
        }

        return list;
    }
}
