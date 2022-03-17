package nl.han.oose.vdlei.spotitube.domain.user.presentation;

public class LoginRequest {
  private String user;
  private String password;

  public void setUser(String user) {
    this.user = user;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  public String getUser() {
    return user;
  }

  public String getPassword() {
    return password;
  }
}
