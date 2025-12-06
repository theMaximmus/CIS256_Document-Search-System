/**
 * CIS256 DOCUMENT SEARCH ENGINE Project
 * Authors: Maksym Stesev, Alex Menchtchikov, and Alex Bombita.
 */
package csm.cis256;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // 1. Initialize Search Engine
        SearchEngine engine = new SearchEngine();

        System.out.println("============================================================");
        System.out.println("             CIS256 Document Search Engine             ");
        System.out.println("      By Maksym Stesev, Alex Menchtchikov, and Alex Bombita");
        System.out.println("============================================================");

        // Index the 'documents' folder
        System.out.println("Indexing 'documents' folder...");
        // Setting up a folder named 'documents' in project root
        engine.indexDirectory("documents");
        System.out.println("Indexing complete.");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Main Command Loop
        while (running) {
            // Displaying current state
            String currentDoc = engine.getHistory().getCurrent();
            System.out.println("\n[Current View]: " + (currentDoc == null ? "(none)" : currentDoc));
            System.out.println("Commands: [search term] | :view <doc> | :back | :forward | :history | :quit");
            System.out.print(">> ");

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            // Handling Commands (starting with :)
            if (input.startsWith(":")) {
                handleCommand(input, engine, scanner);
                if (input.equalsIgnoreCase(":quit") || input.equalsIgnoreCase(":q")) {
                    running = false;
                }
            }
            // Handling Search Queries
            else {
                performSearch(input, engine);
            }
        }

        System.out.println("Exiting search engine.");
        scanner.close();
    }

    private static void handleCommand(String input, SearchEngine engine, Scanner scanner) {
        String[] parts = input.split("\\s+", 2);
        String command = parts[0].toLowerCase();
        String arg = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case ":quit":
            case ":q":
                // Loop in main will handle exit
                break;

            case ":view":
                if (arg.isEmpty()) {
                    System.out.println("Error: Usage is :view <document_name>");
                } else {
                    engine.getHistory().view(arg);
                    System.out.println("Now viewing: " + arg);
                }
                break;

            case ":back":
            case ":b":
                String prev = engine.getHistory().back();
                System.out.println("Went back to: " + (prev == null ? "(start)" : prev));
                break;

            case ":forward":
            case ":f":
                String next = engine.getHistory().forward();
                System.out.println("Went forward to: " + (next == null ? "(end)" : next));
                break;

            case ":history":
            case ":h":
                // Just prints the current item
                System.out.println("Current History Head: " + engine.getHistory().getCurrent());
                break;

            default:
                System.out.println("Unknown command. Try :help or just type words to search.");
                break;
        }
    }

    private static void performSearch(String query, SearchEngine engine) {
        System.out.println("Searching for: '" + query + "'...");

        LinkedList<String> results;

        // Multi-word search if spaces exist, otherwise single word
        if (query.contains(" ")) {
            results = engine.searchAllWords(query);
        } else {
            results = engine.searchOneWord(query);
        }

        if (results.isEmpty()) {
            System.out.println("  No results found.");
        } else {
            System.out.println("  Found in documents (Ranked):");
            ListNode<String> curr = results.getHead();
            int rank = 1;
            while (curr != null) {
                System.out.println("  " + rank + ". " + curr.data);
                curr = curr.next;
                rank++;
            }
        }
    }
}