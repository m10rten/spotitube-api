package nl.han.oose.vdlei.spotitube.utils.json;

import org.json.JSONObject;

public abstract class JSONBuilderAbstract {
  private String json = null;
  private Object pojo = null;

  public String build(){
    JSONObject jsonObj = new JSONObject(pojo);
    this.json = jsonObj.toString();
    return this.json;
  };
}
