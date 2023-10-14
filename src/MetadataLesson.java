import java.sql.*;


public class MetadataLesson {
    public static void main(String[] args) throws Exception{
        String userName = "root";
        String password = "a1c90521";
        String connectionUrl = "jdbc:mysql://localhost:3306/univers";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
             Statement stat = connection.createStatement()) {
            stat.execute("drop table if exists books");
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS Books (id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, dt DATE, PRIMARY KEY (id))");

            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null,null,null, new String[]{"Table"});
            while (resultSet.next()){
                //System.out.println(resultSet.getString(1));
                //System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                //System.out.println(resultSet.getString(4));
            }
            System.out.println("-------------------------------");
            ResultSet resultSet2 = stat.executeQuery("select * from users");
            ResultSetMetaData resultSetMetaData =  resultSet2.getMetaData();;
            for(int i = 1;i<=resultSetMetaData.getColumnCount();i++){
                System.out.println(resultSetMetaData.getColumnLabel(i));
                System.out.println(resultSetMetaData.getColumnType(i));
            }

        }
    }
}
