package nl.han.oose.vdlei.spotitube.domain.user.data;

public class LoginEntity {
  private String token;
  private String user;

  public void setToken(String token) {
    this.token = token;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getToken() {
    return token;
  }

  public String getUser() {
    return user;
  }
}
