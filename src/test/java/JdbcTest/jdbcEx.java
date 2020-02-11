package JdbcTest;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class jdbcEx {

    String dbUrl = "jdbc:oracle:thin:@54.174.236.45:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select region_name from regions");

        while (resultSet.next()){
            System.out.println(resultSet.getString("region_name"));
        }

        //===================================================================

        resultSet = statement.executeQuery("select * from countries");

        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void CountAndNavigate() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //how to find how many record(rows) i have for the query
        resultSet.last();
        int rowCount = resultSet.getRow();
        System.out.println("rowCount = " + rowCount);

        System.out.println(resultSet.getString("department_name"));

        //we need to move before


        resultSet.beforeFirst();

        System.out.println("----------------whileLoopStartAgain---------------------");
        while ((resultSet.next())) {

            System.out.println(resultSet.getString("department_name"));
        }

        resultSet.close();
        statement.close();
        connection.close();


        /*Result Methods
        next () --> move to next row
        previous ()--> move to previous row
        before after ()-->
         */

    }

    @Test
    public void metaData() throws SQLException {


        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");

        //get the dataBase related data inside the dbMetadata object
        DatabaseMetaData bdData = connection.getMetaData();

        System.out.println("user: "+bdData.getUserName());
        System.out.println("database product name: "+bdData.getDatabaseProductName());
        System.out.println("Driver: "+bdData.getDriverName());
        System.out.println("Driver version: "+bdData.getDriverVersion());

        //get the result object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //how many column we have?
        System.out.println("column count: "+ rsMetadata.getColumnCount());

        //Column name
      //  System.out.println(rsMetadata.getColumnName(8));


        //print all column name dynamically

        int columnCount = rsMetadata.getColumnCount();

        for (int i = 1; i <= columnCount ; i++) {
            System.out.println(rsMetadata.getColumnName(i));
        }

        resultSet.close();
        statement.close();
        connection.close();

    }
}
