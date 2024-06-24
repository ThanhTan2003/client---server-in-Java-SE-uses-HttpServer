# Sử dụng hình ảnh OpenJDK chính thức
FROM openjdk:17-jdk-alpine

# Đặt thư mục làm việc
WORKDIR /app

# Sao chép nội dung thư mục hiện tại vào container tại /app
COPY ./src/main/java/org/example /app

# Biên dịch chương trình Java
RUN javac Main.java

# Chạy client
CMD ["java", "Main.java"]
