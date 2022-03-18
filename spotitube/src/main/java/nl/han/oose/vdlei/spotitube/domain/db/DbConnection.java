package nl.han.oose.vdlei.spotitube.domain.db;

import nl.han.oose.vdlei.spotitube.utils.hash.HashMethodes;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DbConnection {
  private String database;
  private Properties properties = new Properties();
  private String userName;
  private String password;
  private Connection connection;

  public Connection getConnection() {
    return connection;
  }

  public final static Connection connection(){
    return new DbConnection().connect().getConnection();
  }

  public DbConnection(){
    try {
      DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
      properties.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
      this.database = properties.getProperty("db_name");
      this.userName = properties.getProperty("db_user");
      this.password = properties.getProperty("db_password");
    } catch (IOException | SQLException e){
      e.printStackTrace();
    }
  }
  public DbConnection connect(){
    String dbURL = "jdbc:sqlserver://localhost:1433;databaseName="+database+";trustServerCertificate=true;encrypt=true;";
    try {
      Connection conn = DriverManager.getConnection(dbURL, userName, password);
      this.connection = conn;
      return this;
    } catch (SQLException e) {
      e.printStackTrace();
      return this;
    }
  }

  public static void main(String[] args) {
    var conn = new DbConnection().connect().getConnection();

    HashMethodes hasher = new HashMethodes();
    try {
      PreparedStatement statement = conn.prepareStatement("SELECT * FROM Users WHERE UserName = ? AND UserPassword = ?");
      statement.setString(1, "meron");
      statement.setString(2, hasher.hash("MySuperSecretPassword12341"));
      ResultSet result = statement.executeQuery();
      if(!result.next()){
        System.out.println("null");
      }
      System.out.println(result.getString("UserFull"));
//      user.setUser(result.getString("UserFull"));
//      user.setToken(result.getString("UserToken"));

    } catch (SQLException e){
      e.printStackTrace();
    }
  }
}
