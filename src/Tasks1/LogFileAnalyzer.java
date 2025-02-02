package Tasks1;
import java.io.*;
import java.util.*;

public class LogFileAnalyzer {

    // Method to analyze the log file
    public void analyzeLogFile(String inputFile, String outputFile, List<String> keywords) throws IOException {
        // Read the input file
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        // Create a map to count occurrences of each keyword
        Map<String, Integer> keywordCounts = new HashMap<>();

        // Initialize counts for keywords
        for (String keyword : keywords) {
            keywordCounts.put(keyword, 0);
        }

        // Process each line of the log file
        String line;
        while ((line = reader.readLine()) != null) {
            // For each keyword, check if it exists in the current line
            for (String keyword : keywords) {
                if (line.contains(keyword)) {
                    keywordCounts.put(keyword, keywordCounts.get(keyword) + 1);
                }
            }
        }

        // Close the reader after processing the file
        reader.close();

        // Write the results to the output file
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write("Log File Analysis Results:\n");

        // Write each keyword and its count
        for (String keyword : keywords) {
            writer.write(keyword + ": " + keywordCounts.get(keyword) + " occurrences\n");
        }

        // Close the writer after writing results
        writer.close();

        System.out.println("Log file analysis complete. Results written to " + outputFile);
    }

    public static void main(String[] args) {
        LogFileAnalyzer analyzer = new LogFileAnalyzer();
        
        // List of keywords to look for
        List<String> keywords = Arrays.asList("ERROR", "WARNING", "INFO");

        try {
            // Analyze the log file with the specified input and output files
            analyzer.analyzeLogFile("input_log.txt", "output_analysis.txt", keywords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
