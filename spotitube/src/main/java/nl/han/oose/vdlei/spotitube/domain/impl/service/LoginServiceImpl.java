package nl.han.oose.vdlei.spotitube.domain.impl.service;

import nl.han.oose.vdlei.spotitube.domain.impl.data.UserDaoImpl;
import nl.han.oose.vdlei.spotitube.domain.user.data.LoginEntity;
import nl.han.oose.vdlei.spotitube.domain.user.presentation.LoginResponse;
import nl.han.oose.vdlei.spotitube.domain.user.service.LoginService;

import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;

public class LoginServiceImpl implements LoginService {
  @Inject
  private UserDaoImpl loginDao;
  //  TODO: when a user logs in: create new token.
  public LoginResponse loginUser(String userName, String password) throws NotAuthorizedException {
    System.out.println(userName);
    System.out.println(password);
    LoginEntity user = loginDao.getUserDetails(userName, password);
    LoginResponse response = new LoginResponse();
    if(user != null) {
      response.setToken(user.getToken());
      response.setUser(user.getUser());
    } else {
      throw new NotAuthorizedException("Auth fail");
    }
    return response;
  };
}
