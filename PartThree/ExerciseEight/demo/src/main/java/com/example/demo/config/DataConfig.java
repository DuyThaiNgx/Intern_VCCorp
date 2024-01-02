//package com.example.demo.config;
//
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.stereotype.Repository;
//
//import javax.sql.DataSource;
//@Repository
//public class DataConfig {
//
//    public DataSource dataSource;
//
//    public DataSource dataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/employees");
//        dataSource.setUsername("root");
//        dataSource.setPassword("23092002");
//        dataSource.setMinIdle(5);
//        dataSource.setMaxIdle(10);
//        dataSource.setMaxOpenPreparedStatements(100);
//        this.dataSource = dataSource;
//        return dataSource;
//    }
//}
