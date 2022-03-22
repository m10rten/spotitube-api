package nl.han.oose.vdlei.spotitube.domain.impl.service;

import nl.han.oose.vdlei.spotitube.domain.impl.data.UserDaoImpl;
import nl.han.oose.vdlei.spotitube.domain.user.data.LoginEntity;
import nl.han.oose.vdlei.spotitube.domain.user.presentation.LoginResponse;
import nl.han.oose.vdlei.spotitube.domain.user.service.LoginService;
import nl.han.oose.vdlei.spotitube.utils.token.TokenMethodes;

import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;

public class LoginServiceImpl implements LoginService {
  private UserDaoImpl userDao;
  private TokenMethodes tokens;

  @Inject
  public void setUserDao(UserDaoImpl userDao) {
    this.userDao = userDao;
  }

  @Inject
  public void setTokens(TokenMethodes tokens) {
    this.tokens = tokens;
  }

  public LoginResponse loginUser(String userName, String password) throws NotAuthorizedException {
    System.out.println(userName);
    System.out.println(password);
    LoginEntity user = userDao.getUserDetails(userName, password);
    LoginResponse response = new LoginResponse();
    if (user != null) {
      String token = tokens.generateRandomToken();
      userDao.updateTokenOnLogin(token, userName);
      response.setToken(token);
      response.setUser(user.getUser());
    } else {
      throw new NotAuthorizedException("Auth fail");
    }
    return response;
  };
}
