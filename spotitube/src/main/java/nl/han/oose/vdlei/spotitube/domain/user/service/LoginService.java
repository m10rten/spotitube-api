package nl.han.oose.vdlei.spotitube.domain.login.service;

import nl.han.oose.vdlei.spotitube.domain.login.presentation.LoginResponse;

public interface LoginService {
  public LoginResponse loginUser(String userName, String password);
}
