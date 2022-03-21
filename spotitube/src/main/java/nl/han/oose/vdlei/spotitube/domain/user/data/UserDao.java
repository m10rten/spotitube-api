package nl.han.oose.vdlei.spotitube.domain.user.data;

public interface UserDao {
  LoginEntity getUserDetails(String userName, String password);

  boolean verifyUserWithToken(String token);

  void updateTokenOnLogin(String newToken, String userName);
}
