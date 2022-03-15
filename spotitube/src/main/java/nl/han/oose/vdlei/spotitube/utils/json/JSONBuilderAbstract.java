package nl.han.oose.vdlei.spotitube.utils.json;

import com.google.gson.JsonElement;
import org.json.JSONObject;

public abstract class JSONBuilder {
  private String json = null;
  private Object pojo = null;

  public String build(){
    JSONObject jsonObj = new JSONObject(pojo);
    this.json = jsonObj.toString();
    return this.json;
  };
}
