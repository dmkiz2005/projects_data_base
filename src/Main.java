import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String  userName = "root";
        String password = "a1c90521";
        String connectionUrl = "jdbc:mysql://localhost:3306/univers";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(connectionUrl,userName, password);
                Statement statement =  connection.createStatement()) {
            statement.executeUpdate("drop table Users");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users( id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, password CHAR(30) NOT NULL, PRIMARY KEY (id));");
            statement.executeUpdate("INSERT INTO Users (name, password) VALUES ('max','123')");
            statement.executeUpdate("INSERT INTO Users SET name = 'otherGuy', password = '321'");

            String userId = "1";
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Users where id = ?");
            preparedStatement.setString(1, userId);
            //preparedStatement.setString(2,"max");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                   System.out.println("userName: " + resultSet.getString("name"));
                   System.out.println("userPassword: " + resultSet.getString("password"));
            }
            
        }
    }
}