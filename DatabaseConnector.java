package MB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.drda.NetworkServerControl;
import java.net.InetAddress;
import java.io.PrintWriter;

public class DatabaseConnector {

    public static Connection getConnection() {
     
        String url = "jdbc:derby://localhost:1527/Ticket;create=true";  
        String user = "pdc";  
        String password = "pdc";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established to 'Ticket'.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void startDerbyNetworkServer() {
        try {
            // Step 1: Create a NetworkServerControl instance bound to localhost and port 1527 (default Derby port)
            NetworkServerControl serverControl = new NetworkServerControl(InetAddress.getByName("localhost"), 1527);
            
            // Step 2: Start the Derby Network Server
            serverControl.start(new PrintWriter(System.out, true)); // Logs server output to the console
            System.out.println("Derby Network Server started on port 1527.");

            // Wait for the server to fully start by pinging it
            boolean serverStarted = false;
            while (!serverStarted) {
                try {
                    serverControl.ping();
                    System.out.println("Derby Network Server is running.");
                    serverStarted = true;
                } catch (Exception e) {
                    System.out.println("Waiting for Derby Network Server to start...");
                    Thread.sleep(1000);  // Wait 1 second before retrying
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createTableAndInsertData() {
        Connection conn = getConnection();
    }

  
}
