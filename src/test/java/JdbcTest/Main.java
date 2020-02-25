package JdbcTest;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        //Those are the connection Strings to get connect with the database
        //at the real work we have to ask them what is the connection String

        String dbUrl = "jdbc:oracle:thin:@54.174.236.45:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        //Create connection to dataBase
        //This class comes from java

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //Create statement object
        Statement statement = connection.createStatement();
        //move pointer to next row,, the purpose move to pointer
        ResultSet resultSet = statement.executeQuery("select * from employees");

        //move pointer to next row
        //resultSet.next();
        // System.out.println(resultSet.getString("region_name"));
        while(resultSet.next()){
            System.out.println(resultSet.getString(2)+"."+ resultSet.getString(3)+"-->"+ resultSet.getInt("salary")) ;
        }


        //We have to close all the connection before we run
        //we have to close the steps opposite order! !!!!
        resultSet.close();
        statement.close();
        connection.close();


    }


}
