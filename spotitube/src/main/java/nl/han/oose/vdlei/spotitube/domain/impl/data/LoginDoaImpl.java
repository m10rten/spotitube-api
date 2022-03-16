package nl.han.oose.vdlei.spotitube.domain.impl.data;

import nl.han.oose.vdlei.spotitube.domain.login.data.LoginDao;
import nl.han.oose.vdlei.spotitube.domain.login.data.LoginEntity;
import nl.han.oose.vdlei.spotitube.domain.db.DbConnection;
import nl.han.oose.vdlei.spotitube.utils.hash.HashMethodes;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDoaImpl implements LoginDao {
  @Inject
  private HashMethodes hasher;
  @Override
  public LoginEntity getUserDetails(String userName, String password) {
    LoginEntity user = new LoginEntity();
    Connection conn = new DbConnection().connect().getConnection();
    try {
      PreparedStatement statement = conn.prepareStatement("SELECT * FROM Users WHERE UserName = ? AND UserPassword = ?");
      statement.setString(1, userName);
      statement.setString(2, hasher.hash(password));
      ResultSet result = statement.executeQuery();
      if(!result.next()){
        return null;
      }
      user.setUser(result.getString("UserFull"));
      user.setToken(result.getString("UserToken"));
      conn.close();
    } catch (SQLException e){
      e.printStackTrace();
    }

    return user;
  }
}
