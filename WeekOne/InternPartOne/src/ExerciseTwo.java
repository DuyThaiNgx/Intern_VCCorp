import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ExerciseTwo {
    public static void main(String[] args) {
        try {
            String inputFilePath = "src/BaiTapPhan1/input_2/01.txt";
            String text = readTextFromFile(inputFilePath);
            Map<String, Integer> wordCountMap = countWords(text);
            String outputFilePath = "src/BaiTapPhan1/input_2/output.txt";
            // Ghi kết quả vào file output
            writeWordCountToFile(wordCountMap, outputFilePath);

            System.out.println("Word count completed. Results written to " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readTextFromFile(String filePath) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append(" ");
            }
        }
        return text.toString();
    }

    private static Map<String, Integer> countWords(String text) throws IOException {
        Map<String, Integer> wordCountMap = new HashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(text, " .,!=+-");
        FileWriter fileWriter = new FileWriter("src/BaiTapPhan1/input_2/output.txt");
        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken().toLowerCase();
            // trả về số lần xuất hiện hiện tại của từ nếu từ đã tồn tại trong wordCountMap, ngược lại trả về 0.
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        return wordCountMap;
    }

    private static void writeWordCountToFile(Map<String, Integer> wordCountMap, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        }
    }
}
