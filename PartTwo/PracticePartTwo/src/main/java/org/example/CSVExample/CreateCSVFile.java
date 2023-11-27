package CSVExample;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class CreateCSVFile {
    public static void main(String[] args) {
        String csvFilePath = "src/CSVExample/CSVFile.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {
            // Dòng tiêu đề
            String[] header = {"Name", "Age", "City"};
            writer.writeNext(header);

            // Dữ liệu
            List<String> data1 = Arrays.asList("John Doe", "25", "New York");
            List<String> data2 = Arrays.asList("Jane Smith", "30", "San Francisco");

            // Ghi dữ liệu từ List vào file CSV
            writer.writeNext(data1.toArray(new String[0]));
            writer.writeNext(data2.toArray(new String[0]));

            System.out.println("CSV file written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
