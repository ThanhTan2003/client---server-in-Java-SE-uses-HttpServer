package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws IOException {

        // Tạo một HTTP Server lắng nghe trên cổng 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Đăng ký một HTTP Handler cho đường dẫn "/"
        server.createContext("/", new MyHandler());

        // Thiết lập executor mặc định (null để sử dụng executor mặc định)
        server.setExecutor(null);

        // Khởi động server
        server.start();
    }

    // Class implement HttpHandler để xử lý request
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Lấy địa chỉ IP của máy Client
            String clientIP = exchange.getRemoteAddress().getAddress().getHostAddress();

            // Lấy ngày giờ hiện tại của hệ thống khi xử lý yêu cầu
            ZonedDateTime now = ZonedDateTime.now(ZoneId.of("GMT+7"));

            // Định dạng ngày giờ theo định dạng "dd-MM-yyyy HH:mm:ss"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            // Đặt múi giờ cho định dạng là GMT+7
            String datetime = now.format(formatter);

            // Chuẩn bị phản hồi cho client
            String response = "Hello World from Thanh Tan!";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());

            // Ghi logs khi phản hồi cho Client
            System.out.println("Request received from IP: " + clientIP);
            System.out.println("Request time: " + datetime);
            System.out.println("Response body: " + response);
            System.out.println("------------------------------------------------------");
            os.close();
        }
    }
}