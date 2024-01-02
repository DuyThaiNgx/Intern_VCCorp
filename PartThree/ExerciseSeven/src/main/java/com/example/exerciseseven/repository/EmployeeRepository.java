package com.example.exerciseseven.repository;

import com.example.exerciseseven.model.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class EmployeeRepository {

    private final DataSource dataSource;

    @Autowired
    public EmployeeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Employees> findEmployeesByCustomQuery(Date hireDate, Float salary, String deptNo, String title) {
        List<Employees> employees = new ArrayList<>();
        String query = buildQuery(hireDate, salary, deptNo, title);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setParameters(preparedStatement, hireDate, salary, deptNo, title);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Employees employee = mapRowToEmployee(resultSet);
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    private String buildQuery(Date hireDate, Float salary, String deptNo, String title) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT e.*,t.title, sa.salary, de.dept_no FROM employees e " +
                "JOIN titles t ON e.emp_no = t.emp_no " +
                "JOIN salaries sa ON sa.emp_no = e.emp_no " +
                "JOIN dept_emp de ON e.emp_no = de.emp_no");

        if (hireDate != null) {
            queryBuilder.append(" WHERE hire_date >= ?");
        }
        if (salary != null) {
            queryBuilder.append(" AND salary >= ?");
        }
        if (deptNo != null) {
            queryBuilder.append(" AND dept_no = ?");
        }
        if (title != null) {
            queryBuilder.append(" AND title = ?");
        }

        return queryBuilder.toString();
    }

    private void setParameters(PreparedStatement preparedStatement, Date hireDate, Float salary, String deptNo, String title) throws SQLException {
        int parameterIndex = 1;

        if (hireDate != null) {
            preparedStatement.setDate(parameterIndex++, new java.sql.Date(hireDate.getTime()));
        }
        if (salary != null) {
            preparedStatement.setFloat(parameterIndex++, salary);
        }
        if (deptNo != null) {
            preparedStatement.setString(parameterIndex++, deptNo);
        }
        if (title != null) {
            preparedStatement.setString(parameterIndex, title);
        }
    }

    private Employees mapRowToEmployee(ResultSet rs) throws SQLException {
        Employees employee = new Employees();
        employee.setEmp_no(rs.getInt("emp_no"));
        employee.setHire_date(rs.getDate("hire_date"));
        employee.setFirst_name(rs.getString("first_name"));
        employee.setLast_name(rs.getString("last_name"));
        employee.setSalary(rs.getFloat("salary"));
        employee.setDept_no(rs.getString("dept_no"));
        employee.setTitle(rs.getString("title"));
        // Set các trường khác nếu cần

        return employee;
    }
}
