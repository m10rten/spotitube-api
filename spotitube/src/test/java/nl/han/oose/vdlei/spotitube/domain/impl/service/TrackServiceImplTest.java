package nl.han.oose.vdlei.spotitube.domain.impl.service;

import nl.han.oose.vdlei.spotitube.domain.DummyData;
import nl.han.oose.vdlei.spotitube.domain.impl.data.TrackDaoImpl;
import nl.han.oose.vdlei.spotitube.domain.tracks.data.TrackDao;
import nl.han.oose.vdlei.spotitube.domain.tracks.presentation.TracksResponse;
import nl.han.oose.vdlei.spotitube.domain.tracks.service.TrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrackServiceImplTest {

    @InjectMocks
    private TrackService sut;
    @Mock
    private TrackDao mockedTrackDao;

    @BeforeEach
    void setUp() {
        this.sut = new TrackServiceImpl();
        this.mockedTrackDao = mock(TrackDaoImpl.class);
        sut.setTrackDao(mockedTrackDao);
    }

    @Test
    void findAllTracksNotInThePlaylist() {
        when(mockedTrackDao.getTracksNotInPlaylistFromDB(DummyData.DUMMY_PLAYLIST.getId()))
                .thenReturn(DummyData.DUMMY_PLAYLIST.getTracks());
        TracksResponse response = sut.findAllTracksNotInThePlaylist(DummyData.DUMMY_PLAYLIST.getId());
        verify(mockedTrackDao).getTracksNotInPlaylistFromDB(DummyData.DUMMY_PLAYLIST.getId());
        assertTrue(response instanceof TracksResponse);
        assertNotNull(response);
    }
}