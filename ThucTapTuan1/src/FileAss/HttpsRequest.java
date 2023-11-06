package FileAss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpsRequest {
    public static void main(String[] args) {
        try {
            // Địa chỉ URL mà bạn muốn gửi yêu cầu GET
            String url = "https://www.facebook.com/dthai.nevershuy/";

            // Tạo kết nối HTTP
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            // Đọc phản hồi từ máy chủ
            int responseCode = connection.getResponseCode();
            //Kiểm tra phản hồi của server, nếu trả về 200 thì thành công
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                String responseBody = response.toString();
                System.out.println(responseBody);
            } else {
                System.out.println("Lỗi: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
