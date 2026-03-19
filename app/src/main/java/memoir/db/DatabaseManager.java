package memoir.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:memoir.db";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {
        String schemaPath = "db/schema.sql";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            java.nio.file.Path path = java.nio.file.Paths.get(schemaPath);

            if (!java.nio.file.Files.exists(path)) {
                System.err.println("Error: schema.sql couldn't be found under: " + path.toAbsolutePath());
                return;
            }

            String sql = java.nio.file.Files.readString(path);

            if (!sql.trim().isEmpty()) {
                statement.executeUpdate(sql);
                System.out.println("SQL Database is ready!");
            }

        } catch (Exception e) {
            System.err.println("Database initialization failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

