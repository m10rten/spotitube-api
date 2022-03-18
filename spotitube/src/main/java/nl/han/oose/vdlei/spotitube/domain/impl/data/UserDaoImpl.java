package nl.han.oose.vdlei.spotitube.domain.impl.data;

import nl.han.oose.vdlei.spotitube.domain.user.data.UserDao;
import nl.han.oose.vdlei.spotitube.domain.user.data.LoginEntity;
import nl.han.oose.vdlei.spotitube.domain.db.DbConnection;
import nl.han.oose.vdlei.spotitube.utils.hash.HashMethodes;

import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
  @Inject
  private HashMethodes hasher;
  @Override
  public LoginEntity getUserDetails(String userName, String password) {
    LoginEntity user = new LoginEntity();
    try (Connection conn = new DbConnection().connect().getConnection()) {
      PreparedStatement statement = conn.prepareStatement("SELECT * FROM Users WHERE UserName = ? AND UserPassword = ?");
      statement.setString(1, userName);
      statement.setString(2, hasher.hash(password));
      ResultSet result = statement.executeQuery();
      if(!result.next()){
        return null;
      }
      user.setUser(result.getString("UserFull"));
      user.setToken(result.getString("UserToken"));
    } catch (SQLException e){
      e.printStackTrace();
    }
    return user;
  }

  @Override
  public boolean verifyUserWithToken (String token) throws NotAuthorizedException {
    try (Connection conn = new DbConnection().connect().getConnection()){
      if(token == null) throw new NotAuthorizedException("Invalid token");
      PreparedStatement statement = conn.prepareStatement("SELECT UserToken FROM Users WHERE UserToken = ?");
      statement.setString(1, token);
      ResultSet results = statement.executeQuery();
      if(!results.next()) throw new NotAuthorizedException("Invalid token");
      return true;
    } catch (SQLException | NotAuthorizedException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public void updateTokenOnLogin (String newToken, String userName) {
    try (Connection conn = new DbConnection().connect().getConnection()){
      PreparedStatement statement = conn.prepareStatement("UPDATE Users SET UserToken = ? WHERE UserName = ? ");
      statement.setString(1, newToken);
      statement.setString(2, userName);
      statement.execute();
    } catch (SQLException | NotAuthorizedException e) {
      e.printStackTrace();
    }
  }

  public int getId (String userToken) {
    try (Connection conn = new DbConnection().connect().getConnection()) {
      PreparedStatement statement = conn.prepareStatement("SELECT UserId FROM Users WHERE UserToken = ?");
      statement.setString(1, userToken);
      ResultSet result = statement.executeQuery();
      if(result.next()) {
        int userId = Integer.parseInt(result.getString("UserId"));
        return userId;
      } else {
        return -1;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }
}
