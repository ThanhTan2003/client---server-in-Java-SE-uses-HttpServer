package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            // Tạo một URI để xây dựng URL
            URI uri = new URI("http://server:8080/");
            URL url = uri.toURL();

            // Mở kết nối HTTP đến URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Thiết lập phương thức yêu cầu là GET
            connection.setRequestMethod("GET");

            // Đọc dữ liệu phản hồi từ máy chủ
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            // Đọc từng dòng dữ liệu và nối vào StringBuilder 'response'
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close(); // Đóng luồng đọc

            // Lấy ngày giờ hiện tại của hệ thống khi xử lý yêu cầu
            ZonedDateTime now = ZonedDateTime.now(ZoneId.of("GMT+7"));

            // Định dạng ngày giờ theo định dạng "dd-MM-yyyy HH:mm:ss"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            // Đặt múi giờ cho định dạng là GMT+7
            String datetime = now.format(formatter);

            // Lấy IP của server từ header X-Forwarded-For hoặc Remote-Address
            String serverIP = connection.getHeaderField("X-Forwarded-For");
            if (serverIP == null || serverIP.isEmpty()) {
                serverIP = connection.getHeaderField("Remote-Address");
            }

            // Log địa chỉ IP của server
            System.out.println("Server IP: " + serverIP);
            System.out.println("Time: " + datetime);
            // In ra nội dung phản hồi
            System.out.println("Response body: " + response);

            System.out.println("------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
