package nl.han.oose.vdlei.spotitube.domain.user.data;

import nl.han.oose.vdlei.spotitube.utils.hash.HashMethodes;

public interface UserDao {
  LoginEntity getUserDetails(String userName, String password);

  boolean verifyUserWithToken(String token);

  void updateTokenOnLogin(String newToken, String userName);

  int getId(String token);

  void setHasher(HashMethodes hasher);
}
