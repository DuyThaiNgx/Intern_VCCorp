package com.example.demo.repository;

import com.example.demo.dto.EmployeeDto;
//import com.example.demo.repository.EmployeeDto;
import com.example.demo.dto.SalaryDto;
import com.example.demo.dto.TaxDto;
import com.example.demo.model.DepartmentSalary;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSalary;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    private DataSource dataSource;

    //    public EmployeeRepository() {
////        BasicDataSource dataSource = new BasicDataSource();
////        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
////        dataSource.setUrl("jdbc:mysql://localhost:3306/employees");
////        dataSource.setUsername("root");
////        dataSource.setPassword("23092002");
////        this.dataSource = new BasicDataSource();
//
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/employees");
//        config.setUsername("root");
//        config.setPassword("23092002");
//
//        // Cấu hình HikariCP
//        config.setMaximumPoolSize(10);
//        config.setAutoCommit(false); // Tắt auto-commit để sử dụng transaction
//
//        this.dataSource = new HikariDataSource(config);
//    }
    public EmployeeRepository() {
        initializeDataSource();
    }

    private void initializeDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/employees");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("23092002");
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(10);
        basicDataSource.setMaxOpenPreparedStatements(100);
        basicDataSource.setDefaultAutoCommit(false);
        this.dataSource = basicDataSource;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            rs = preparedStatement.getResultSet();
            while (rs.next()) {
                employees.add(new Employee(rs.getInt("emp_no"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("gender"), rs.getDate("birth_date"), rs.getDate("hire_date")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
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

    public Employee addEmployee(int emp_no, String full_name, String gender, String birth_date, String hire_date,
                                String title, int salary) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet rs = null;
        Employee employee = new Employee();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//        jdbcTemplate.update(connection -> {
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, empNo);
//            preparedStatement.setString(2, str[0]);
//            preparedStatement.setString(3, str[1]);
//            preparedStatement.setString(4, gender);
//            preparedStatement.setString(5, birthDate);
//            preparedStatement.setString(6, hireDate);
//            return preparedStatement;
//        });
        try {
            String sql = "INSERT INTO employees (emp_no, first_name, last_name, gender, birth_date, hire_date) VALUES (?,?,?,?,?,?)";
            String[] str = full_name.split(" ");
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, emp_no);
            employee.setEmpNo(emp_no);
            preparedStatement.setString(2, str[0]);
            preparedStatement.setString(3, str[1]);
            employee.setFirstName(str[0]);
            employee.setLastName(str[1]);
            preparedStatement.setString(4, gender);
            employee.setGender(gender);
            preparedStatement.setString(5, birth_date);
            employee.setBirthDate(Date.valueOf(LocalDate.parse(birth_date, formatter)));
            preparedStatement.setString(6, hire_date);
            employee.setHireDate(Date.valueOf(LocalDate.parse(hire_date, formatter)));
            preparedStatement.executeUpdate();

            sql = "INSERT INTO titles (emp_no, title, from_date, to_date) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, emp_no);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, hire_date);
            preparedStatement.setString(4, "9999-01-01");
            preparedStatement.executeUpdate();

            sql = "INSERT INTO salaries (emp_no, salary, from_date, to_date) VALUES  (?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, emp_no);
            preparedStatement.setInt(2, salary);
            preparedStatement.setString(3, hire_date);
            preparedStatement.setString(4, "9999-01-01");
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                    System.out.println("Transaction rolled back due to an exception: " + e.getMessage());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return employee;
        }
    }
//        sql = "INSERT INTO titles (emp_no, title, from_date, to_date) VALUES (?, ?, ?, ?)";
//        jdbcTemplate.update(connection -> {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, empNo);
//            preparedStatement.setString(2, title);
//            preparedStatement.setString(3,);
//            preparedStatement.setString(4, );
//        });


    //    public void addTitle(Employee employee, String hire_date, String title){
//        String sql = "INSERT INTO titles (emp_no, title, from_date, to_date) VALUES (?, ?, ?, ?)";
//        jdbcTemplate.update(connection -> {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, employee.getEmpNo());
//            preparedStatement.setString(2, title);
//            preparedStatement.setString();
//            preparedStatement.setDate(4, Date.);
//        });
//    }
//    public void setupNewEmployee(EmployeeDto employee) {
//        Connection connection = null;
//        try {
//            connection = dataSource.getConnection();
//            connection.setAutoCommit(false);
////        executeAddEmployee(connection, employee);
////        executeAddTitle(connection, employee.getEmpNo(), employee.getHireDate(), title);
////        executeAddSalary(connection, employee.getEmpNo(), employee.getHireDate(), salary);
//            addEmployee(employee.getEmp_no(), employee.getBirth_date(), employee.getFull_name(), employee.getGender(),
//                    employee.getHire_date(), employee.getTitle(), employee.getSalary());
//            connection.commit();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            try {
//                if (connection != null) {
//                    connection.rollback();
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void transferEmployee(int empNo, String deptNo, String title) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            String sql = "Call TransferEmployeeToDepartment(?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, empNo);
            preparedStatement.setString(2, deptNo);
            preparedStatement.setString(3, title);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public SalaryDto getSalary(String date) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Connection connection = null;
        List<EmployeeSalary> employeeSalaries = new ArrayList<>();
        List<DepartmentSalary> departmentSalaries = new ArrayList<>();
        SalaryDto salaryDTO = new SalaryDto();

        try {
            connection = dataSource.getConnection();

            StringBuilder sql = new StringBuilder("select emp_no, sum(salary) \n" +
                    "from salaries \n" +
                    "where from_date < ? \n" +
                    "and to_date > ? \n" +
                    "group by emp_no");
            pstm = connection.prepareStatement(sql.toString());
            pstm.setString(1, date);
            pstm.setString(2, date);
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                int emp_no = rs.getInt(1);
                long salary = rs.getLong(2);
                employeeSalaries.add(new EmployeeSalary(emp_no, salary));
            }

            sql = new StringBuilder("SELECT d.dept_name, SUM(s.salary) as total_salary\n" +
                    "FROM employees e \n" +
                    "JOIN dept_emp de ON e.emp_no=de.emp_no\n" +
                    "JOIN departments d ON d.dept_no=de.dept_no\n" +
                    "JOIN salaries s ON e.emp_no=s.emp_no \n" +
                    "WHERE s.from_date < ? \n" +
                    "AND s.to_date > ? \n" +
                    "GROUP BY d.dept_name");

            pstm = connection.prepareStatement(sql.toString());
            pstm.setString(1, date);
            pstm.setString(2, date);
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                String dept_no = rs.getString(1);
                long salary = rs.getLong(2);
                departmentSalaries.add(new DepartmentSalary(dept_no, salary));
            }

            sql = new StringBuilder("select sum(salary) \n" +
                    "from salaries \n" +
                    "where from_date < ? \n" +
                    "and to_date > ?");

            pstm = connection.prepareStatement(sql.toString());
            pstm.setString(1, date);
            pstm.setString(2, date);
            pstm.execute();
            rs = pstm.getResultSet();

            long total = 0;

            while (rs.next()) {
                total = rs.getLong(1);
            }
            salaryDTO.setEmployeeSalaryList(employeeSalaries);
            salaryDTO.setDepartmentSalaryList(departmentSalaries);
            salaryDTO.setCompanySalary(total);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return salaryDTO;
    }

    // -----------------------------------------TAX
    private void executeInsertTax(Connection connection, List<TaxDto> taxDtos) throws SQLException {
        String createTable = "CREATE TABLE IF NOT EXISTS income_tax (\n" +
                "emp_no INT PRIMARY KEY NOT NULL,\n" +
                "tax INT NOT NULL,\n" +
                "year INT NOT NULL,\n" +
                "month INT NOT NULL,\n" +
                "FOREIGN KEY (emp_no) REFERENCES employees(emp_no)\n" +
                ")";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createTable)) {
            preparedStatement.executeUpdate();
        }
        String sql = "INSERT INTO income_tax (emp_no, tax, year, month) VALUES (?, ?, ?, ?)";
        for (TaxDto taxDto : taxDtos) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, taxDto.getEmpNo());
                preparedStatement.setInt(2, taxDto.getTax());
                preparedStatement.setInt(3, taxDto.getYear());
                preparedStatement.setInt(4, taxDto.getMonth());
                preparedStatement.executeUpdate();
            }
        }

    }
}