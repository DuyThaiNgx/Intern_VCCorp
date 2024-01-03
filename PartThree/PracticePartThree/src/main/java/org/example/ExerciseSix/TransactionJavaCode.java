package org.example.ExerciseSix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionJavaCode {

    // Các thông tin kết nối cơ sở dữ liệu
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/employees";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "23092002";

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // connectdb
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            connection.setAutoCommit(false);

            //Hủy chức vụ hiện tại
            String updateCurrentTitleQuery = "UPDATE titles SET to_date = NOW() WHERE emp_no = ? AND title = 'Staff' AND to_date = '9999-01-01'";
            try (PreparedStatement statement1 = connection.prepareStatement(updateCurrentTitleQuery)) {
                statement1.setInt(1, 10002);
                statement1.executeUpdate();
            }

            //Thăng chức lên "Senior Staff"
            String promoteToSeniorStaffQuery = "INSERT INTO titles (emp_no, title, from_date, to_date) VALUES (?, 'Senior Staff', NOW(), '9999-01-01')";
            try (PreparedStatement statement2 = connection.prepareStatement(promoteToSeniorStaffQuery)) {
                statement2.setInt(1, 10002);
                statement2.executeUpdate();
            }

            connection.commit();

        } catch (SQLException e) {
            // rollback lại transaction neu loi
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }

            e.printStackTrace();

        } finally {
            // Đảm bảo đóng connection trong mọi trường hợp
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }
    }
}
