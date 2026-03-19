package memoir;

import memoir.db.DatabaseManager;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        DatabaseManager.initializeDatabase();
    }
}
