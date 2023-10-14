import java.sql.*;

public class BatchLessons {
    public static void main(String[] args) throws Exception {
        String userName = "root";
        String password = "a1c90521";
        String connectionUrl = "jdbc:mysql://localhost:3306/univers";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
             Statement stat = connection.createStatement()) {
            connection.setAutoCommit(false);
            stat.addBatch("drop table if exists books");
            stat.addBatch("CREATE TABLE IF NOT EXISTS Books (id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, dt DATE, PRIMARY KEY (id))");
            stat.addBatch("insert into books (name) values ('Inferno')");
            stat.addBatch("insert into books (name) values ('DaVinchi Code')");
            stat.addBatch("insert into books (name) values ('Solomon key')");
            if(stat.executeBatch().length == 5){
                connection.commit();
            }else {
                connection.rollback();
            }
        }
    }
}
