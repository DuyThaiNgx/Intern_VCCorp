package com.example.employee.repository;

import com.example.employee.model.DepartmentSalary;
import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeSalary;
import com.example.employee.model.SalaryDTO;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    DataSource dataSource;

    public EmployeeRepository() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/employees");
        config.setUsername("root");
        config.setPassword("23092002");

        // Cấu hình HikariCP
        config.setMaximumPoolSize(10);
        config.setAutoCommit(false); // Tắt auto-commit để sử dụng transaction

        this.dataSource = new HikariDataSource(config);
    }

    public List<Employee> getEmployees(Integer salary, String deptNo, String title, String hireDateFrom) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Connection connection = null;
        List<Employee> employees = new ArrayList<>();

        try {
            connection = dataSource.getConnection();

            StringBuilder sqlString = new StringBuilder("select distinct e.* from employees e join salaries s on s.emp_no = e.emp_no and year(s.from_date) <= year(current_date) and year(s.to_date) > year(current_date) ");

            if (salary != null) {
                sqlString.append("and s.salary > ? ");
            }

            sqlString.append("join dept_emp de on de.emp_no = e.emp_no ");

            if (deptNo != null) {
                sqlString.append("and de.dept_no = ? ");
            }

            sqlString.append("join titles t on t.emp_no = e.emp_no ");

            if (title != null) {
                sqlString.append("and t.title = ? ");
            }

            sqlString.append("where 1 = 1 ");

            if (hireDateFrom != null) {
                sqlString.append("and e.hire_date > ?");
            }

            pstm = connection.prepareStatement(sqlString.toString());

            int index = 1;



            if (salary != null) {
                pstm.setDouble(index++, salary);
            }

            if (deptNo != null) {
                pstm.setString(index++, deptNo);
            }

            if (title != null) {
                pstm.setString(index++, title);
            }

            if (hireDateFrom != null) {
                pstm.setString(index, hireDateFrom);
            }

            System.out.println(sqlString.toString());
            System.out.println(pstm);

            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("emp_no"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("birth_date"),
                        rs.getDate("hire_date"),
                        rs.getString("gender")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employees;
    }

    public Employee setupEmployee(int emp_no, String birth_date, String full_name, String gender, String hire_date, String title, int salary) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Connection connection = null;
        Employee employee = new Employee();

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Insert employee
            StringBuilder insertEmployeeSQL = new StringBuilder("insert into employees(emp_no, birth_date, first_name, last_name, gender, hire_date) values(?,?,?,?,?,?)");
            pstm = connection.prepareStatement(insertEmployeeSQL.toString());

            pstm.setInt(1, emp_no);
            employee.setEmp_no(emp_no);

            pstm.setString(2, birth_date);
            employee.setBirth_date(LocalDate.parse(birth_date, formatter));

            String[] names = full_name.split(" ");
            pstm.setString(3, names[0]);
            pstm.setString(4, names[1]);
            employee.setFirst_name(names[0]);
            employee.setLast_name(names[1]);

            pstm.setString(5, gender);
            employee.setGender(gender);

            pstm.setString(6, hire_date);
            employee.setHire_date(LocalDate.parse(hire_date, formatter));

            pstm.executeUpdate();

            // Insert title
            StringBuilder insertTitleSQL = new StringBuilder("insert into titles(emp_no, title, from_date, to_date) values (?,?,?,?)");
            pstm = connection.prepareStatement(insertTitleSQL.toString());

            pstm.setInt(1, emp_no);
            pstm.setString(2, title);
            pstm.setString(3, hire_date);
            pstm.setString(4, "9999-01-01");

            pstm.executeUpdate();


            // Insert salary
            StringBuilder insertSalarySQL = new StringBuilder("insert into salaries(emp_no, salary, from_date, to_date) values (?,?,?,?)");
            pstm = connection.prepareStatement(insertSalarySQL.toString());
            pstm.setInt(1, emp_no);
            pstm.setInt(2, salary);
            pstm.setString(3, hire_date);
            pstm.setString(4, "9999-01-01");

            pstm.executeUpdate();

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

            return employee;
        }
    }

    public void transferEmployee(int employeeId, String newDepartment, String newTitle) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Connection connection = null;
        Employee employee = new Employee();

        try {
            connection = dataSource.getConnection();

            // Tạo PreparedStatement để gọi stored procedure
            String query = "{ CALL TransferEmployeeToDepartment(?, ?, ?) }";
            pstm = connection.prepareStatement(query);

            // Thiết lập tham số cho stored procedure
            pstm.setInt(1, employeeId);
            pstm.setString(2, newDepartment);
            pstm.setString(3, newTitle);

            // Thực thi stored procedure
            pstm.execute();
            // Kết thúc và commit transaction
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
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
    }

    public SalaryDTO getSalary(String date) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Connection connection = null;
        List<EmployeeSalary> employeeSalaries = new ArrayList<>();
        List<DepartmentSalary> departmentSalaries = new ArrayList<>();
        SalaryDTO salaryDTO = new SalaryDTO();

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

            while (rs.next()){
                total = rs.getLong(1);
            }

            salaryDTO.setEmployeeSalaries(employeeSalaries);
            salaryDTO.setDepartmentSalaries(departmentSalaries);
            salaryDTO.setTotalSalary(total);

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


}
