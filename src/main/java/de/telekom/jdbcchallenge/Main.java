package de.telekom.jdbcchallenge;

import java.sql.*;

public class Main {
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
            ResultSet resultSet = statement.executeQuery( "select * from persons");
            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getInt(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
            }

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into persons (ID, SALUTATION, NAME, SURNAME) values (?,?,?,?)");
            preparedStatement.setInt(1, 8);
            preparedStatement.setInt(2, 0);
            preparedStatement.setString(3, "Name");
            preparedStatement.setString(4, "Surname");
            preparedStatement.execute();

            resultSet = statement.executeQuery( "select * from persons");
            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getInt(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
            }

            preparedStatement.close();
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
