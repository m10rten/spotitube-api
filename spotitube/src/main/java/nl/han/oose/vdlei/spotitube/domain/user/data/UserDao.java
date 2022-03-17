package nl.han.oose.vdlei.spotitube.domain.login.data;

public interface LoginDao {
  LoginEntity getUserDetails(String userName, String password);
}
