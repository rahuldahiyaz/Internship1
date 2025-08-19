import java.sql.*;

public class JavaJDBC {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/TEST";
    private static final String USER = "root";    
    private static final String PASSWORD = "test"; 

    public static void main(String[] args) {
        // Load MySQL JDBC Driver 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
            return;
        }

        
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            if (conn != null) {
                System.out.println("Connected to the database.");

                // Step 1: Create table if not exists
                String createTableSQL = """
                        CREATE TABLE IF NOT EXISTS users (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            email VARCHAR(100) UNIQUE NOT NULL
                        )
                        """;
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(createTableSQL);
                }

                // Step 2: Insert user data using PreparedStatement
                String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setString(1, "Rahul Dahiya");
                    pstmt.setString(2, "rahul@example.com");
                    pstmt.executeUpdate();

                    pstmt.setString(1, "John Doe");
                    pstmt.setString(2, "john@example.com");
                    pstmt.executeUpdate();
                }

                // Step 3: Fetch and display data
                String selectSQL = "SELECT id, name, email FROM users";
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(selectSQL)) {

                    System.out.println("\nUser Data:");
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String email = rs.getString("email");
                        System.out.printf("ID: %d | Name: %s | Email: %s%n", id, name, email);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
