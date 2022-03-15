package nl.han.oose.vdlei.spotitube;

import nl.han.oose.vdlei.spotitube.utils.json.JSONBuilder;

public class App {
  public static void main(String[] args) {
    Person parent = new Person();
    parent.setAge(28);
    parent.setName("parent");

    Person child = new Person();
    child.setAge(2);
    child.setName("child");
    Person child2 = new Person();
    child2.setAge(3);
    child2.setName("child2");
    parent.addChild(child);
    parent.addChild(child2);

    System.out.println(parent.toJson());
  }
}
