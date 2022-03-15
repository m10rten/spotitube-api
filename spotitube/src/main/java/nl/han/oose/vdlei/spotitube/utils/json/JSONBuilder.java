package nl.han.oose.vdlei.spotitube.utils.json;

import org.json.JSONObject;

public class JSONBuilder {
  private final Object pojo;

  public JSONBuilder(JSONEntity obj){
    this.pojo = obj;
  };

  public String build () {
    JSONObject jsonObj = new JSONObject(pojo);
    String json = jsonObj.toString();
    return json;
  };
}
