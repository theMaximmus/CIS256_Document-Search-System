/**
 * CIS256 DOCUMENT SEARCH ENGINE Project
 * Authors: Maksym Stesev, Alex Menchtchikov, and Alex Bombita.
 *
 * The main entry point for the Document Search System.
 * This class handles the Command Line Interface.
 */
package csm.cis256;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Initialize Search Engine
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

        // The Main Loop
        // Keeps the application running until the user explicitly quits.
        while (running) {
            // Display context: Show the user what document they are currently "viewing"
            String currentDoc = engine.getHistory().getCurrent();
            System.out.println("\n[Current View]: " + (currentDoc == null ? "(none)" : currentDoc));
            // Print a helpful prompt so they know what commands are available
            System.out.println("Commands: [search term] | :view <doc> | :back | :forward | :history | :quit");
            System.out.print(">> ");

            String input = scanner.nextLine().trim();

            // Ignore accidental empty "Enter" key presses
            if (input.isEmpty()) {
                continue;
            }

            // Command Parsing
            // We distinguish between "Commands" (starting with :) and "Searches" (everything else).
            if (input.startsWith(":")) {
                handleCommand(input, engine, scanner);
                // Check if the command was to quit
                if (input.equalsIgnoreCase(":quit") || input.equalsIgnoreCase(":q")) {
                    running = false;
                }
            }
            // Handling Search Queries
            else {
                // If it's not a command, treat it as a search query
                performSearch(input, engine);
            }
        }

        System.out.println("Exiting search engine.");
        scanner.close();
    }

    /**
     * Logic for special commands (View, Back, Forward, History).
     *
     * @param input The raw user input line.
     * @param engine The search engine instance to control.
     * @param scanner The scanner (unused here, but passed for potential future expansion).
     */
    private static void handleCommand(String input, SearchEngine engine, Scanner scanner) {
        // Split command and argument (e.g., ":view doc1.txt" -> [":view", "doc1.txt"])
        String[] parts = input.split("\\s+", 2);
        String command = parts[0].toLowerCase();
        String arg = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case ":quit":
            case ":q":
                // Handled in the main loop condition
                break;

            case ":view":
                // "Opening" a document. This pushes it to the History stack.
                if (arg.isEmpty()) {
                    System.out.println("Error: Usage is :view <document_name>");
                } else {
                    engine.getHistory().view(arg);
                    System.out.println("Now viewing: " + arg);
                }
                break;

            case ":back":
            case ":b":
                // "Back" command
                String prev = engine.getHistory().back();
                System.out.println("Went back to: " + (prev == null ? "(start)" : prev));
                break;

            case ":forward":
            case ":f":
                // "Forward" command
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

    /**
     * Logic for processing search queries.
     * Handles the distinction between single-word and multi-word (AND) searches.
     */
    private static void performSearch(String query, SearchEngine engine) {
        System.out.println("Searching for: '" + query + "'...");

        LinkedList<String> results;

        // Multi-word search if spaces exist, otherwise single word. If there's a space, it's a multi-word query.
        if (query.contains(" ")) {
            results = engine.searchAllWords(query);
        } else {
            results = engine.searchOneWord(query);
        }
        // Display results nicely formatted
        if (results.isEmpty()) {
            System.out.println("  No results found.");
        } else {
            System.out.println("  Found in documents (Ranked):");
            ListNode<String> curr = results.getHead();
            int rank = 1;
            // Iterate through the result list
            while (curr != null) {
                System.out.println("  " + rank + ". " + curr.data);
                curr = curr.next;
                rank++;
            }
        }
    }
}