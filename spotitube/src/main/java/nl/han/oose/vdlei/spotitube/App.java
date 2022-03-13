package nl.han.oose.vdlei.spotitube;

import nl.han.oose.vdlei.spotitube.utils.LoginJSONBuilder;
import org.json.JSONObject;

import java.util.HashMap;

public class App {
  public static void main(String[] args) {
    Person person = new Person();
    person.setName( "Person Name" );
    person.setAge( 333 );

    JSONObject jsonObj = new JSONObject( person );
    System.out.println( jsonObj );
  }
}
