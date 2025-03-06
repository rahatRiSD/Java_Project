package com.employee.leave_request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgreSQLConnection {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/leave_request_db";
        String user = "leave_user";
        String password = "leave_password";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            if (conn.isValid(2)) {
                System.out.println("Connected to PostgreSQL successfully!");
            } else {
                System.out.println("Failed to connect to PostgreSQL.");
                return;
            }

            // Show all tables in the database
            ResultSet rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'public';");

            System.out.println("Tables in the database:");
            while (rs.next()) {
                System.out.println(rs.getString("table_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
