package JdbcTest;

import org.testng.annotations.Test;

import java.sql.*;

public class PracticingJdbc {

    String dbUrl = "jdbc:oracle:thin:@54.174.236.45:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

@Test
        public void test1() throws SQLException {



    Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("select first_name, last_name, salary from employees");

    while(resultSet.next()){

        System.out.println(resultSet.getString("first_name")+"-"+resultSet.getString("last_name"));
    }

    /*To get the row in our Query we gonna use several type of methods form java.

        ResultSet Methods are below:
        next.() --> moves to next row
        previous()--> moves to previous row
        beforeFirst()--> goes before the first row
        afterLast()--> goes after the last row
        absolute()--> goes specific row
        getRow()--> gets the row number
        last()--> moves to last row
     */


    resultSet.close();
    statement.close();
    connection.close();


   }
    @Test
    public void test2() throws SQLException {

        String dbUrl = "jdbc:oracle:thin:@54.174.236.45:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        //With default setting of statement class, driver only goes up to down.

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select first_name, last_name from employees");

        //here we learn how to find the records ( row) in the Query
        //go to last row
        resultSet.last();
        int rowCount = resultSet.getRow();
        System.out.println("rowCount = " + rowCount);

        System.out.println(resultSet.getString("First_name"));

        System.out.println("=====================================================");
        //we need to move before the firs row to get the full row
        resultSet.beforeFirst();
        while(resultSet.next()){

            System.out.println(resultSet.getString("first_name"));

        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void test3() throws SQLException {

        String dbUrl = "jdbc:oracle:thin:@54.174.236.45:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";


        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from locations");


        //DatabaseMetaData is general information about my data!!!!!

        DatabaseMetaData databaseMetaData = connection.getMetaData();
        System.out.println("databaseMetaData.getDriverVersion() = " + databaseMetaData.getDriverVersion());
        System.out.println("databaseMetaData.getUserName() = " + databaseMetaData.getUserName());
        System.out.println("databaseMetaData.getDatabaseProductName() = " + databaseMetaData.getDatabaseProductName());

        //Get the resultSet object metaData
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

     /*   //how many column we have??
        System.out.println(resultSetMetaData.getColumnCount());

        //Column name
        System.out.println(resultSetMetaData.getColumnName(2));
        System.out.println(resultSetMetaData.getColumnName(8));


        System.out.println("==========================================");
        //Print all column names dynamicly
*/
        int columnCount = resultSetMetaData.getColumnCount();

        for (int i = 1; i <columnCount ; i++) {
            System.out.println(resultSetMetaData.getColumnName(i));

        }







        resultSet.close();
        statement.close();
        connection.close();

    }


}
