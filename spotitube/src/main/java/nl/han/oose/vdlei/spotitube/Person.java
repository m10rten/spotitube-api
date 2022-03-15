package nl.han.oose.vdlei.spotitube;

import nl.han.oose.vdlei.spotitube.utils.json.JSONBuilder;
import nl.han.oose.vdlei.spotitube.utils.json.JSONEntity;

import java.util.ArrayList;

public class Person implements JSONEntity {
  private String name;
  private Integer age;
  private ArrayList<Person> children = new ArrayList<Person>();
  public ArrayList<Person> getChildren() {
    return children;
  }
  public String getName() { return this.name; }
  public void setName( String name ) { this.name = name; }

  public Integer getAge() { return this.age; }

  public void setAge( Integer age ) { this.age = age; }
  public void addChild (Person child) {
    children.add(child);
  }

  @Override
  public String toJson() {
    JSONBuilder jb = new JSONBuilder(this);
    return jb.build();
  }
}
