package nl.han.oose.vdlei.spotitube.utils.token;

import java.util.UUID;

public class TokenMethodes {
  public String generateTokenThatIsNotInDB() {
    UUID newTokenUUID = UUID.randomUUID();
    String newToken = newTokenUUID.toString();
    return newToken;
  }
}
