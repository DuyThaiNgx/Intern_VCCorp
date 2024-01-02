// src/main/java/com/example/demo/dao/EmployeeDAOImpl.java



import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToEmployee(rs));
    }

    private Employee mapRowToEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setEmpNo(rs.getInt("emp_no"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setGender(rs.getString("gender"));
        employee.setBirthDate(rs.getDate("birth_date"));
        employee.setHireDate(rs.getDate("hire_date"));
        return employee;
    }

    @Override
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (emp_no, first_name, last_name, gender, birth_date, hire_date) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employee.getEmpNo());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setString(4, employee.getGender());
            preparedStatement.setDate(5, new java.sql.Date(employee.getBirthDate().getTime()));
            preparedStatement.setDate(6, new java.sql.Date(employee.getHireDate().getTime()));
            return preparedStatement;
        });
    }
}
