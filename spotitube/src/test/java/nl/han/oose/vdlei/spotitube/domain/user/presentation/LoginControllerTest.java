package nl.han.oose.vdlei.spotitube.domain.user.presentation;

import nl.han.oose.vdlei.spotitube.domain.impl.service.LoginServiceImpl;
import nl.han.oose.vdlei.spotitube.domain.user.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Not;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {

  @InjectMocks
  private LoginController sut;
  @Mock
  private LoginService mockedLoginService;

  private static final String USER_NAME = "user1234";
  private static final String USER_PASSWORD = "password1234";

  @BeforeEach
  void setUp() {
    this.sut = new LoginController();
    this.mockedLoginService = mock(LoginService.class);
    this.sut.setLoginService(mockedLoginService);
  }

  @Test
  void loginUserEndpointWrongCredentials() {
    var login = new LoginResponse();
    login.setToken("1234-1234-1235");

    when(mockedLoginService.loginUser(anyString(), anyString())).thenThrow(NotAuthorizedException.class);

    LoginRequest loginParams = new LoginRequest();
    loginParams.setUser("USER_NAME");
    loginParams.setPassword("USER_PASSWORD");
    Response response = sut.loginUserEndpoint(loginParams);

    assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
  }

  @Test
  void loginUserEndpointCorrectCredentials() {
    var login = new LoginResponse();
    login.setToken("1234-1234-1235");

    when(mockedLoginService.loginUser(USER_NAME, USER_PASSWORD)).thenReturn(login);

    LoginRequest loginParams = new LoginRequest();
    loginParams.setUser(USER_NAME);
    loginParams.setPassword(USER_PASSWORD);
    Response response = sut.loginUserEndpoint(loginParams);
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }
}