package nl.han.oose.vdlei.spotitube.domain.login.data;

import nl.han.oose.vdlei.spotitube.utils.json.JSONBuilder;
import nl.han.oose.vdlei.spotitube.utils.json.JSONEntity;

public class LoginEntity implements JSONEntity {
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

  @Override
  public String toJson() {
    JSONBuilder jb = new JSONBuilder(this);
    String json = jb.build();
    return json;
  }
}
