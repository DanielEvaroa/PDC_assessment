package MB;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
 
public class TicketDB_mananger {

    private static final String USER_NAME = "pdc"; //your DB username
    private static final String PASSWORD = "pdc"; //your DB password
    private static final String URL = "jdbc:derby://localhost:1527/Ticket";  //url of the DB host

    Connection conn;

    public TicketDB_mananger()
    {
        establishConnection();
    }

    public Connection getConnection() {
        return this.conn;
    }

     public void establishConnection() {
        //Establish a connection to Database
        try
        {
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            if (conn != null) {
                System.out.println("Successfully connected to the database.");
            }
        }
        catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        } 
    }

     public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
 
    public ResultSet queryDB(String sql) {
        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }
 
    

}
 