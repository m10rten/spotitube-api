package nl.han.oose.vdlei.spotitube;

import java.util.ArrayList;

public class Person {
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
}
