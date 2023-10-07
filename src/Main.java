
import java.sql.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        String  userName = "root";
        String password = "a1c90521";
        String connectionUrl = "jdbc:mysql://localhost:3306/univers";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(connectionUrl,userName, password);
                Statement statement =  connection.createStatement()) {
            /* statement.executeUpdate("drop table Users");
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
            */
            statement.execute("drop table IF EXISTS Books");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Books (id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, img BLOB, PRIMARY KEY (id))");

            BufferedImage image = ImageIO.read(new File("smile.jpg"));
            Blob blob = connection.createBlob();
            try(OutputStream outputStream = blob.setBinaryStream(1)) {
                ImageIO.write(image, "jpg", outputStream);
            }
            PreparedStatement statement1 = connection.prepareStatement("insert into Books (name, img) values (?,?)");
            statement1.setString(1,"inferno");
            statement1.setBlob(2,blob);
            statement1.execute();

            ResultSet resultSet = statement.executeQuery("select * from Books");
            while(resultSet.next()){
                Blob blob1 = resultSet.getBlob("img");
                BufferedImage image2 = ImageIO.read(blob.getBinaryStream());
                File outputFile = new File("saved.png");
                ImageIO.write(image2, "png", outputFile);
            }
        }
    }
}