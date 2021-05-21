package de.telekom.jdbcchallenge;

import java.sql.*;

public class Injection {
    public static void main(String[] args) {
        final String DRIVER = "com.mysql.cj.jdbc.Driver";
        final String URL = "jdbc:mysql://localhost:3306/seadb?user=seauser&password=seapass";

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();
            String surname = "'adfas', NAME = 'adf'";
            long id = 1;
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE persons SET SURNAME = " + surname + " WHERE ID = " + id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
