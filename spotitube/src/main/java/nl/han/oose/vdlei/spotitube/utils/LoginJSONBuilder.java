package nl.han.oose.vdlei.spotitube.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import nl.han.oose.vdlei.spotitube.Person;
import nl.han.oose.vdlei.spotitube.utils.JSONBuilder;
import org.json.JSONObject;

import javax.json.*;
import javax.json.JsonObjectBuilder;
import java.util.HashMap;

public class LoginJSONBuilder implements JSONBuilder {
  private Gson gson;
  private String json;
  private JsonObjectBuilder builder;
  private HashMap<String, String> map;
  private Object pojo;
  private JSONObject JsonElem;
  public LoginJSONBuilder(HashMap<String, String> map, Person obj){
    this.map = map;
    this.gson = new Gson();
    this.pojo = obj;

    this.JsonElem = new JSONObject(pojo);
//    System.out.println(JsonElem);
//    this.list = new HashMap<String, String>();
//    list.forEach();
//    this.list.put();
//    this.builder = Json.createObjectBuilder();
//    this.gson = new GsonBuilder().create();
  };

  @Override
  public String build () {
//    System.out.println(json);
//    this.json = gson.toJson(JsonElem);
//    System.out.println(JsonElem.toString());

//    var newJson = gson.toJson(JsonElem);
//    System.out.println(newJson);
//    map.forEach((key, value) -> builder.add(key, value));
//    System.out.println(json);
//    this.json = builder.build().toString();
    JSONObject jsonobj = new JSONObject(pojo);
    this.json = jsonobj.toString();
    return this.json;
  };

  public static void main(String[] args) {
    HashMap<String, String> list = new HashMap<String, String>();
    list.put("token", "121212");
    list.put("username", "nameingss");
    class Cow {
      public String name = "hi";
      public int age = 12;
//      Cow(int age, String name) {
//        this.age = age;
//        this.name = name;
//      }
        public String getName() { return this.name; }
        public void setName( String name ) { this.name = name; }

        public Integer getAge() { return this.age; }
        public void setAge( Integer age ) { this.age = age; }
      }
    var cow = new Person();
    cow.setName( "Person Name" );
    cow.setAge( 333 );
    LoginJSONBuilder lgBuilder = new LoginJSONBuilder(list, cow);
    System.out.println(lgBuilder.build());
  }
}
