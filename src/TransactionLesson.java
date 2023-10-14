import java.sql.*;

public class TransactionLesson {
    public static void main(String[] args) throws Exception {
        String userName = "root";
        String password = "a1c90521";
        String connectionUrl = "jdbc:mysql://localhost:3306/univers";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
             Statement stat = connection.createStatement()) {
            connection.setAutoCommit(false);
            stat.execute("drop table if exists books");
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS Books (id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, dt DATE, PRIMARY KEY (id))");

            stat.executeUpdate("insert into books (name) values ('Inferno')");
            Savepoint savepoint = connection.setSavepoint();
            stat.executeUpdate("insert into books (name) values ('DaVinchi Code')");
            stat.executeUpdate("insert into books (name) values ('Solomon key')");

            connection.rollback(savepoint);
            connection.commit();
            connection.releaseSavepoint(savepoint);
        }
    }
}
