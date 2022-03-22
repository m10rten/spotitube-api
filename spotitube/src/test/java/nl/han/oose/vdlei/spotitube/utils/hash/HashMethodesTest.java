package nl.han.oose.vdlei.spotitube.utils.hash;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class HashMethodesTest {
    @InjectMocks
    private HashMethodes sut;

    @BeforeEach
    void setUp() {
        this.sut = new HashMethodes();
    }

    @Test
    void hashTest() {
        String toMatchThis = "51bccea53b8cda0c9c266655f8632ade63643dbfdc848713848abb9df96502e8";

        String hash = sut.hash("testhashstring");

        assertEquals(toMatchThis, hash);
    }
}