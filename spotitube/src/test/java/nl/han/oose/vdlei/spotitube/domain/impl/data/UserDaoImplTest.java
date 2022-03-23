package nl.han.oose.vdlei.spotitube.domain.impl.data;

import nl.han.oose.vdlei.spotitube.utils.hash.HashMethodes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDaoImplTest {

    @InjectMocks
    private UserDaoImpl sut;

    @Mock
    private HashMethodes hasher;

    @BeforeEach
    void setUp() {
        this.sut = new UserDaoImpl();
        this.hasher = mock(HashMethodes.class);
        sut.setHasher(hasher);
    }

    @Test
    void getUserDetails() {
    }

    @Test
    void verifyUserWithToken() {
    }

    @Test
    void getId() {
    }
}