import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String userName = "root";
        String password = "a1c90521";
        String connectionUrl = "jdbc:mysql://localhost:3306/univers";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
             Statement statement = connection.createStatement()) {
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
            //statement.executeUpdate("CREATE TABLE IF NOT EXISTS Books (id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, img BLOB, PRIMARY KEY (id))");
            /*
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

             */
            /*
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Books (id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, dt DATE, PRIMARY KEY (id))");

            PreparedStatement preparedStatement = connection.prepareStatement("insert into Books (name, dt) VALUES ('someName', ?)");
            preparedStatement.setDate(1, new Date(1696706114968L));
            preparedStatement.execute();
            //System.out.println(preparedStatement);

            statement.executeUpdate("insert into Books (name, dt) VALUES ('someName', '2023-10-07')");
            ResultSet resultSet = statement.executeQuery("select * from Books");
            while (resultSet.next()) {
                System.out.println(resultSet.getDate("dt"));
            }
            */
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Books (id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, dt DATE, PRIMARY KEY (id))");
            statement.executeUpdate("insert into books (name) values ('Inferno')");
            statement.executeUpdate("insert into books (name) values ('DaVinchi Code')");
            statement.executeUpdate("insert into books (name) values ('Solomon key')");

            /*CallableStatement callableStatement = connection.prepareCall("{call BooksCount(?)}");
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.execute();
            System.out.println(callableStatement.getInt(1));
            System.out.println("------------------------");

            CallableStatement callableStatement1 = connection.prepareCall("{call getBooks(?)}");
            callableStatement1.setInt(1,2);
            if(callableStatement1.execute()){
                ResultSet resultSet = callableStatement1.getResultSet();
                while (resultSet.next()){
                    System.out.println(resultSet.getInt("id"));
                    System.out.println(resultSet.getString("name"));

                }
            }
            //ResultSet resultSet = callableStatement.getResultSet();
            //while (resultSet.next()){
            //    System.out.println();
            //}
            */
            /*
            CallableStatement callableStatement = connection.prepareCall("CALL getCount()");
            boolean hasResults = callableStatement.execute();
            while (hasResults){
                ResultSet resultSet = callableStatement.getResultSet();
                while(resultSet.next()){
                    System.out.println(resultSet.getInt(1));
                }
                hasResults = callableStatement.getMoreResults();
            }

             */
            /*
            Statement stat = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //PreparedStatement preparedStatement = connection.prepareStatement("", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = stat.executeQuery("select * from books");
            if(resultSet.next()){
                System.out.println(resultSet.getString("name"));
            }
            if(resultSet.next()){
                System.out.println(resultSet.getString("name"));
            }
            if(resultSet.previous()){
                System.out.println(resultSet.getString("name"));
            }
            if(resultSet.relative(2)){
                System.out.println(resultSet.getString("name"));
            }
            if(resultSet.relative(-2)){
                System.out.println(resultSet.getString("name"));
            }
            if(resultSet.absolute(2)){
                System.out.println(resultSet.getString("name"));
            }
            if(resultSet.first()){
                System.out.println(resultSet.getString("name"));
            }
            if(resultSet.last()){
                System.out.println(resultSet.getString("name"));
            }
            */
            //resultSet.beforeFirst();
            //resultSet.next();

            Statement stat = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //PreparedStatement preparedStatement = connection.prepareStatement("sql", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

            ResultSet resultSet = stat.executeQuery("select * from books");
            while(resultSet.next()){
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));
            }

            resultSet.last();
            resultSet.updateString("name", "new Value");
            resultSet.updateRow();

            resultSet.moveToInsertRow();
            resultSet.updateString("name","inserted new");
            resultSet.insertRow();

            resultSet.absolute(2);
            resultSet.deleteRow();

            resultSet.beforeFirst();
            while(resultSet.next()){
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));
            }
        }
    }
}