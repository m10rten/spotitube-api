package nl.han.oose.vdlei.spotitube.domain.impl.service;

import nl.han.oose.vdlei.spotitube.domain.DummyData;
import nl.han.oose.vdlei.spotitube.domain.user.data.UserDao;
import nl.han.oose.vdlei.spotitube.domain.user.service.LoginService;
import nl.han.oose.vdlei.spotitube.utils.token.TokenMethodes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginServiceImplTest {

  @InjectMocks
  @Mock
  private LoginServiceImpl sut;

  @Mock
  private UserDao userDao;

  @Mock
  private TokenMethodes tokens;


  @BeforeEach
  void setUp() {
    this.sut = new LoginServiceImpl();
    this.userDao = mock(UserDao.class);
    this.tokens = mock(TokenMethodes.class);
    sut.setUserDao(userDao);
    sut.setTokens(tokens);
  }

  @Test
  void loginUser() {
    when(userDao.getId(DummyData.DUMMY_LOGIN.getToken())).thenReturn(1);
    when(userDao.getUserDetails("test", "pwd")).thenReturn(DummyData.DUMMY_LOGIN);
    sut.loginUser("test", "pwd");
    verify(userDao).getUserDetails("test","pwd");
  }
}