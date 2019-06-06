package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import com.stackroute.muzixservice.repository.TrackRepository;
import org.h2.engine.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class TrackServiceImplTest {

    private Track track;
    private Optional optional;

    //Create a mock for UserRepository
    @Mock
    private TrackRepository trackRepository;

    //Inject the mocks as dependencies into TrackServiceImpl
    @InjectMocks
    private TrackServiceImpl trackService;
    List<Track> list= null;

    @Before
    public void setUp() throws Exception {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setTrackId(1);
        track.setTrackName("undiporadhe");
        track.setTrackComments("nice song");
        list = new ArrayList<>();
        list.add(track);
        optional=Optional.of(track);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistsException {

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track,savedTrack);

        //verify here verifies that trackRepository save method is only called once
        verify(trackRepository,times(1)).save(track);

    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveUserTestFailure() throws TrackAlreadyExistsException{
        when(trackRepository.save((Track)any())).thenReturn(null);
        Track savedTrack = trackService.saveTrack(track);

    }

    @Test
    public void getAllTrack(){

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> trackList = trackService.getAllTrack();
        Assert.assertEquals(list,trackList);
    }

    @Test
    public void testDeleteTrack() throws TrackNotFoundException {

        when(trackRepository.existsById(track.getTrackId())).thenReturn(true);
        boolean status=trackService.delete(track);
        Assert.assertEquals(true,status);
    }
    @Test(expected = TrackNotFoundException.class)
    public void testDeleteTrackFailure() throws TrackNotFoundException {

        when(trackRepository.existsById(track.getTrackId())).thenReturn(false);
        boolean status=trackService.delete(track);

    }

    @Test
    public void testUpdateTrackComments() throws TrackNotFoundException{

        when(trackRepository.findById(track.getTrackId())).thenReturn(optional);
        track.setTrackComments("super");
        Track track1=trackService.updateComments(track);
        Assert.assertEquals("super",track1.getTrackComments());
    }
    @Test(expected = TrackNotFoundException.class)
    public void testUpdateTrackCommentsFailure() throws TrackNotFoundException{

        when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.empty());
        track.setTrackComments("nice");
        Track track1=trackService.updateComments(track);
    }
    @Test
    public void testTrackByName() throws TrackNotFoundException{
        when(trackRepository.getTrackByName(track.getTrackName())).thenReturn(list);
        List<Track> list1=trackService.getTrackByName(track.getTrackName());
        Assert.assertEquals("undiporadhe",list1.get(0).getTrackName());
    }
    @Test(expected = TrackNotFoundException.class)
    public void testTrackByNameFailure() throws TrackNotFoundException{
        when(trackRepository.getTrackByName(track.getTrackName())).thenReturn(Collections.<Track>emptyList());
        List<Track> list1=trackService.getTrackByName(track.getTrackName());

    }



}