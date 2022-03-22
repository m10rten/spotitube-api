package nl.han.oose.vdlei.spotitube.utils.token;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenMethodesTest {

    private TokenMethodes sut;

    @BeforeEach
    void setUp() {
        this.sut = new TokenMethodes();
    }

    @Test
    void generateRandomTokenTest() {
        String token = sut.generateRandomToken();
        assertNotNull(token);
    }
}