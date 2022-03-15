package nl.han.oose.vdlei.spotitube.utils.json;

import nl.han.oose.vdlei.spotitube.Person;
import nl.han.oose.vdlei.spotitube.domain.login.data.LoginEntity;
import org.json.JSONObject;

public class LoginJSONBuilder {
  private Object pojo;
  private String json;

  public LoginJSONBuilder(JSONEntity obj){
    this.pojo = obj;
  };

  public String build () {
    JSONObject jsonObj = new JSONObject(pojo);
    this.json = jsonObj.toString();
    return this.json;
  };
}
