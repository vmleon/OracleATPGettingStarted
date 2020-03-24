package page.cateam.atp;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        Database database = new Database();
        try {
            database.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
