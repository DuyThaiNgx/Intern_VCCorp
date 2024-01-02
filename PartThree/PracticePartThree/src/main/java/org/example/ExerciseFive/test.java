//package org.example;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class EmployeeTransfer {
//    private DataSource dataSource;
//
//    public EmployeeTransfer(){
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/employees");
//        config.setUsername("root");
//        config.setPassword("haiphong1234");
//
//        // Cấu hình HikariCP
//        config.setMaximumPoolSize(10);
//        config.setAutoCommit(false); // Tắt auto-commit để sử dụng transaction
//
//        dataSource = new HikariDataSource(config);
//    }
//
//    public void transferEmployee(int employeeId, String newDepartment, String newTitle) {
//        try (Connection connection = dataSource.getConnection()) {
//            // Tạo PreparedStatement để gọi stored procedure
//            String query = "{ CALL TransferEmployee(?, ?, ?) }";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                // Thiết lập tham số cho stored procedure
//                preparedStatement.setInt(1, employeeId);
//                preparedStatement.setString(2, newDepartment);
//                preparedStatement.setString(3, newTitle);
//
//                // Thực thi stored procedure
//                preparedStatement.execute();
//            }
//            // Kết thúc và commit transaction
//            connection.commit();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        EmployeeTransfer employeeTransfer = new EmployeeTransfer();
//        employeeTransfer.transferEmployee(10001, "d010", "Staff");
//        System.out.println("Transfer successfully!");
//    }
//}