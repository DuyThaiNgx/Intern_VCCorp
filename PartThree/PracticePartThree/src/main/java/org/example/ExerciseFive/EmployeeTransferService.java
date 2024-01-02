package org.example.ExerciseFour;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeTransferService {

    private HikariDataSource dataSource;

    public EmployeeTransferService() {
        // Khởi tạo HikariCP configuration
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/employees");
        config.setUsername("root");
        config.setPassword("23092002");

        // Khởi tạo HikariDataSource từ configuration
        dataSource = new HikariDataSource(config);
    }

    public void transferEmployee(int empId, String newDeptNo, String newTitle) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();

            // Gọi stored procedure để thực hiện thuyên chuyển và lấy kết quả
            String storedProcedureCall = "{CALL TransferEmployeeToDepartment(?, ?, ?)}";
            statement = connection.prepareStatement(storedProcedureCall);
            statement.setInt(1, empId);
            statement.setString(2, newDeptNo);
            statement.setString(3, newTitle);
            statement.execute();

            // Lấy kết quả từ stored procedure
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                // Xử lý và in ra thông tin kết quả cũ trước khi chuyển đổi chức vụ và phòng ban
                System.out.println("Employee ID: " + resultSet.getInt("emp_no"));
                System.out.println("Full Name: " + resultSet.getString("full_name"));
                System.out.println("Gender: " + resultSet.getString("gender"));
                System.out.println("Title: " + resultSet.getString("title"));
                System.out.println("Department Name: " + resultSet.getString("dept_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi nếu cần

        } finally {
            // Đảm bảo đóng các resource (connection, statement, resultSet) trong finally
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Tạo một đối tượng của EmployeeTransferService
        EmployeeTransferService transferService = new EmployeeTransferService();

        // Call peocerdure
        //Chuyen 10005 từ d003 sang d004
        transferService.transferEmployee(10005, "d004", "StaffTester");
    }
}
