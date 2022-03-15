package nl.han.oose.vdlei.spotitube.utils.hash;

import org.apache.commons.codec.digest.DigestUtils;

public class HashMethodes {
  public boolean match(String hash, String toMatch){
    return hash.equals(hash(toMatch));
  }
  public String hash(String str){
    return DigestUtils.sha256Hex(str);
  }
}
