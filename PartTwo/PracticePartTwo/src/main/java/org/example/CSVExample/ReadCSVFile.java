package CSVExample;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCSVFile {
    public static void main(String[] args) {
        String csvFilePath = "src/CSVExample/CSVFile.csv";

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            // Đọc tất cả dữ liệu từ file CSV thành List
            List<String[]> data = reader.readAll();

            // In dữ liệu từ List
            for (String[] row : data) {
                for (String cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }

            // Chuyển List thành List<List<String>>
            List<List<String>> dataList = new ArrayList<>();
            for (String[] row : data) {
                Arrays Arrays = null;
                dataList.add(Arrays.asList(row));
            }

            // In dữ liệu từ List<List<String>>
            for (List<String> row : dataList) {
                for (String cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
