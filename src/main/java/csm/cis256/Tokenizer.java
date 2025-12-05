package csm.cis256;

import java.io.File;
import java.util.Scanner;

public class Tokenizer {

    // MUST be static because SearchEngineTest calls tokenization statically
    public static LinkedList<String> tokenizeFile(String filename) {
        LinkedList<String> list = new LinkedList<>();

        try {
            File f = new File(filename);
            Scanner sc = new Scanner(f);

            while (sc.hasNext()) {
                String word = sc.next().toLowerCase().replaceAll("[^a-z0-9]", "");
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
