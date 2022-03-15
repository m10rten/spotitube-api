package nl.han.oose.vdlei.spotitube.domain.login.service;

import nl.han.oose.vdlei.spotitube.domain.impl.data.LoginDoaImpl;
//import nl.han.oose.vdlei.spotitube.domain.login.data.LoginDao;
import nl.han.oose.vdlei.spotitube.domain.login.data.LoginEntity;
import nl.han.oose.vdlei.spotitube.domain.login.presentation.LoginResponse;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

public class LoginService {
  @Inject
  private LoginDoaImpl loginDao;
//  TODO: when a user logs in: create new token.
  public LoginResponse loginUser(String userName, String password) {
    System.out.println(userName);
    System.out.println(password);
    LoginEntity user = loginDao.getUserDetails(userName, password);
    LoginResponse response = new LoginResponse();
    if(user != null) {
      response.setStatus(Response.Status.OK);
      response.setJson(user.toJson());
    }
    return response;
  };
}
