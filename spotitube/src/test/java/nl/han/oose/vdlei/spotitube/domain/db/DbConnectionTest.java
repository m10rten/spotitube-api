package nl.han.oose.vdlei.spotitube.domain.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import javax.jms.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class DbConnectionTest {

  @InjectMocks
  private DbConnection sut;

  @BeforeEach
  void setUp() {
    this.sut = new DbConnection();
  }

  @Test
  void getConnection() {
    var conn = sut.connect().getConnection();

    assertNotNull(conn);
  }

  @Test
  void connect() {
    var conn = sut.connect();

    assertEquals(conn.getClass(), DbConnection.class);
  }
}