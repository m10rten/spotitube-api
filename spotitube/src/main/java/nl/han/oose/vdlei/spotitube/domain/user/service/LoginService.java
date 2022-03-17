package nl.han.oose.vdlei.spotitube.domain.user.service;

import nl.han.oose.vdlei.spotitube.domain.user.presentation.LoginResponse;

public interface LoginService {
  public LoginResponse loginUser(String userName, String password);
}
