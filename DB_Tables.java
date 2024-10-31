package MB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;



public class DB_Tables {

    private final TicketDB_mananger TicketDB_mananger;
    private final Connection conn;
    private Statement statement;
    //Connection object to the table.

    public DB_Tables() {
        TicketDB_mananger = new TicketDB_mananger();
        conn = TicketDB_mananger.getConnection();
    }
    
    //User table 
    public void create_user_table() {
        try {
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "USERS", null);
            if (!tables.next()) {
                statement = conn.createStatement();
                String createTableSQL = "CREATE TABLE USERS ("
                        + "EMAIL VARCHAR(50),"
                        + "PASSWORD VARCHAR(50))";
                statement.executeUpdate(createTableSQL);
            }
        } catch (SQLException e) {
            System.out.println("Error during database initialization: " + e.getMessage());
            e.printStackTrace();

        }

    }

    //#########################################################################################
    //Creates the blanck tables for the ticket types.
    
    public void create_Eco_ticket() {

        try {
            DatabaseMetaData dbm = conn.getMetaData();
            // Check if "STANDARD" table exists
            ResultSet tables = dbm.getTables(null, null, "ECO", null);
            if (!tables.next()) {
                statement = conn.createStatement();

                String createTableSQL = "CREATE TABLE ECO ("
                        + "EMAIL VARCHAR(50), "
                        + "NAME VARCHAR(50), "
                        + "Ticket_num INT, "
                        + "SEATED VARCHAR(50))";
                statement.executeUpdate(createTableSQL);
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println("Error during database initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }
   

    public void create_Standard_ticket() {

        try {
            DatabaseMetaData dbm = conn.getMetaData();
            // Check if "STANDARD" table exists
            ResultSet tables = dbm.getTables(null, null, "STANDARD", null);
            if (!tables.next()) {
                statement = conn.createStatement();

                String createTableSQL = "CREATE TABLE STANDARD ("
                        + "EMAIL VARCHAR(50),"
                        + "NAME VARCHAR(50),"
                        + "Ticket_num INT)";
                statement.executeUpdate(createTableSQL);
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println("Error during database initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void create_GOLD_ticket() {
        try {
            DatabaseMetaData dbm = conn.getMetaData();
            // Check if "PREMIUM" table exists
            ResultSet tables = dbm.getTables(null, null, "GOLD", null);
            if (!tables.next()) {
                String createTableSQL = "CREATE TABLE GOLD ("
                        + "EMAIL VARCHAR(50), "
                        + "NAME VARCHAR(50), "
                        + "Ticket_num INT, " 
                        + "DRINKS VARCHAR(50)"
                        + ")";
                statement.executeUpdate(createTableSQL);
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println("Error during database initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }
    


    public void create_Premium_ticket() {
    try {
        // Check if the connection object is initialized
        if (conn == null) {
            throw new SQLException("Database connection is not initialized.");
        }

        // Get database metadata
        DatabaseMetaData dbm = conn.getMetaData();

        // Check if "PREMIUM" table exists
        ResultSet tables = dbm.getTables(null, null, "PREMIUM", null);
        
        // If the table doesn't exist, create it
        if (!tables.next()) {
            String createTableSQL = "CREATE TABLE PREMIUM ("
                                    + "EMAIL VARCHAR(50), "
                                    + "NAME VARCHAR(50), "
                                    + "Ticket_num INT, "
                                    + "DRINKS VARCHAR(50), "
                                    + "FOOD VARCHAR(50)"
                                    + ")";
            
            // Create a statement from the connection
            Statement statement = conn.createStatement();

            // Execute the table creation SQL
            statement.executeUpdate(createTableSQL);
            
            // Close the statement after use
            statement.close();

            System.out.println("Table 'PREMIUM' created successfully.");
        } else {
            System.out.println("Table 'PREMIUM' already exists.");
        }

        // Close the resultSet
        tables.close();

    } catch (SQLException e) {
        // Handle SQL exceptions
        System.out.println("Error during database initialization: " + e.getMessage());
        e.printStackTrace();
    }
}
    //#########################################################################################
    // The populate functions search the ticket tables for inputs. if there is no inputs in the in the table then it is populated with 10 tickets
    // If the table does have data in the table then the function will not update or add tickets.
   
    
    public void populate_eco_amount(String EMAIL,String NAME,int num,String Seated) {
        String selectQuery = "SELECT * FROM ECO FETCH FIRST ROW ONLY"; // Assuming this query is correct for your DB
        String insertQuery = "INSERT INTO ECO (EMAIL,NAME,TICKET_NUM,SEATED) VALUES (?,?,?,?)";

    try {
        PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
        ResultSet resultSet = selectStatement.executeQuery();
        
        if (!resultSet.next()) {
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setString(1, EMAIL); 
            insertStatement.setString(2, NAME); 
            insertStatement.setInt(3, num); 
            insertStatement.setString(4,Seated ); 
            insertStatement.executeUpdate();
            insertStatement.close();
        }
        resultSet.close();
        selectStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
    
    public void populate_standard_amount(String EMAIL,String NAME,int num) {
        String selectQuery = "SELECT * FROM STANDARD FETCH FIRST ROW ONLY"; // Assuming this query is correct for your DB
        String insertQuery = "INSERT INTO STANDARD (EMAIL,NAME,TICKET_NUM) VALUES (?,?,?)";

    try {
        PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
        ResultSet resultSet = selectStatement.executeQuery();
        
        if (!resultSet.next()) {
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setString(1, EMAIL); 
            insertStatement.setString(2, NAME); 
            insertStatement.setInt(3, num); 
            
            insertStatement.executeUpdate();
            insertStatement.close();
        }
        resultSet.close();
        selectStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public void populate_gold_amount(String EMAIL, String NAME, int num,String drinks) {
    
        String selectQuery = "SELECT * FROM GOLD FETCH FIRST ROW ONLY"; // Assuming this query is correct for your DB
        String insertQuery = "INSERT INTO GOLD (EMAIL,NAME,TICKET_NUM,DRINKS) VALUES (?,?,?,?)";

    try {
        PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
        ResultSet resultSet = selectStatement.executeQuery();
        
        if (!resultSet.next()) {
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setString(1, EMAIL); 
            insertStatement.setString(2, NAME); 
            insertStatement.setInt(3, num); 
            insertStatement.setString(4, "true"); 
            insertStatement.executeUpdate();
            insertStatement.close();
        }

        
        resultSet.close();
        selectStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
    public void populate_premium_amount(String EMAIL, String NAME, int num,String drinks,String Food) {
        String selectQuery = "SELECT * FROM PREMIUM FETCH FIRST ROW ONLY"; // Assuming this query is correct for your DB
        String insertQuery = "INSERT INTO PREMIUM (EMAIL,NAME,TICKET_NUM,DRINKS,FOOD) VALUES (?,?,?,?,?)";

    try {
        PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
        ResultSet resultSet = selectStatement.executeQuery();
        
        if (!resultSet.next()) {
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setString(1, EMAIL); 
            insertStatement.setString(2, NAME); 
            insertStatement.setInt(3, num); 
            insertStatement.setString(4, "true"); 
            insertStatement.setString(5, "true"); 
            insertStatement.executeUpdate();
            insertStatement.close();
        }
        resultSet.close();
        selectStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
   
    
    
    

}
