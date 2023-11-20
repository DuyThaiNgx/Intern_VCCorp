import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

class WordCountTask implements Callable<Map<String, Integer>> {
    private final File file;

    public WordCountTask(File file) {
        this.file = file;
    }

    @Override
    public Map<String, Integer> call() throws Exception {
        Map<String, Integer> wordCount = new HashMap<>();

//        try {
//            List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
//            for (String line : lines) {
//                String[] words = line.split("\\s+");
//                for (String word : words) {
//                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordCount;
    }
}

public class ExerciseThree {
    public static void main(String[] args) {
        String folderPath = "src/BaiTapPhan1/input_3";
        List<File> files = getFilesInFolder(folderPath);
        //Set toi da 6 luong chay
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        List<Future<Map<String, Integer>>> futures = new ArrayList<>();

        for (File file : files) {
            WordCountTask task = new WordCountTask(file);
            Future<Map<String, Integer>> future = executorService.submit(task);
            futures.add(future);
        }

        Map<String, Integer> totalWordCount = new HashMap<>();

        for (Future<Map<String, Integer>> future : futures) {
            try {
                Map<String, Integer> wordCount = future.get();
                mergeWordCount(totalWordCount, wordCount);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();

        // In ra top 10 tu
        printTopWords(totalWordCount, 10, true);
        printTopWords(totalWordCount, 10, false);
    }

    private static List<File> getFilesInFolder(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles(pathname -> pathname.getName().endsWith(".txt"));
        return files != null ? Arrays.asList(files) : Collections.emptyList();
    }

    private static void mergeWordCount(Map<String, Integer> totalWordCount, Map<String, Integer> wordCount) {
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            totalWordCount.put(entry.getKey(), totalWordCount.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }
    }

    private static void printTopWords(Map<String, Integer> wordCount, int topN, boolean mostFrequent) {
        Comparator<Map.Entry<String, Integer>> comparator =
                mostFrequent ? Map.Entry.comparingByValue(Comparator.reverseOrder()) :
                        Map.Entry.comparingByValue();

        List<Map.Entry<String, Integer>> sortedEntries = wordCount.entrySet()
                .stream()
                .sorted(comparator)
                .limit(topN)
                .collect(Collectors.toList());

        if (mostFrequent) {
            System.out.println("Top " + topN + " Most Frequent Words:");
        } else {
            System.out.println("Top " + topN + " Least Frequent Words:");
        }

        for (Map.Entry<String, Integer> entry : sortedEntries) {
            System.out.println("Word: "+ "'" +entry.getKey() + "'" + ": " + entry.getValue() + " times");
        }

    }
}
