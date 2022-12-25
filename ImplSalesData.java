import java.sql.*;

public class ImplSalesData
{

    static Connection con;
    static Statement stmt;

    public static void establishConnection () throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded successfully");

        String user="root";
        String pass="Root";
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC2",user,pass);
        System.out.println("Connection established");

    }

    public static void addNewSalesPerson () throws SQLException
    {
        Statement stmt = con.createStatement();

        // execute a query
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("1. John Kenov has joined the sales team from the Nordic region. Include his details in the database.");

        String query="insert into salesPerson values (1012,'John','Nordic',24);";
        try
        {
            int count = stmt.executeUpdate(query);
            System.out.println(count +" rows affected" );
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            System.out.println("Duplicate Entry");
        }
    }

    public static void resignedEmployee () throws SQLException
    {
        stmt = con.createStatement();

        // execute a query
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("2. AxelRod has resigned from his position of a sales representative.Remove his details from the database.");

        String query="delete from salesPerson where firstName = 'AxelRod'";
        int count = stmt.executeUpdate(query);
        if(count==0){System.out.println("Data has been removed already");}
        else{System.out.println(count +" rows affected");};
    }

    public static void addCustomer () throws SQLException
    {
        stmt = con.createStatement();

        // execute a query
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("A new order has been placed by a Customer from the Nordic region");
        try
        {
            String query1="insert into customer values (2008,'Thomas','Samuel','Nordic',150,1012);";
            String query2="insert into orders values (3012,3000,20210512,2008);";

            int count1 = stmt.executeUpdate(query1);
            int count2 = stmt.executeUpdate(query2);
            System.out.println(count1+count2 +" rows affected" );
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            System.out.println("Duplicate Entry");
        }
    }

    public static void updateAmount () throws SQLException
    {
        stmt = con.createStatement();

        // execute a query
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("4. Samuel Thomas wants to upgrade the order amount to 3500 on the current order, update the table.");
        String query="update orders set amount = 3500 where custid = 2008;";
        int count = stmt.executeUpdate(query);
        System.out.println(count +" rows affected" );
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        establishConnection();
        addNewSalesPerson();
        resignedEmployee();
        addCustomer();
        updateAmount();
    }
}
