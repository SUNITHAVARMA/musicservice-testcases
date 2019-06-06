package com.stackroute.muzixservice.repository;

import com.stackroute.muzixservice.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;
    private Track track;


    @Before
    public void setUp() throws Exception {

        track= new Track();
        track.setTrackId(1);
        track.setTrackName("undiporadhe");
        track.setTrackComments("nice");

    }

    @After
    public void tearDown() throws Exception {
        trackRepository.deleteAll();
    }


    @Test
    public void testSaveTrack(){
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(10,fetchTrack.getTrackId());

    }

    @Test
    public void testSaveTrackFailure(){
        Track testTrack = new Track(2,"mellaga","nice song");
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
        Assert.assertNotSame(testTrack,track);
    }

    @Test
    public void testGetAllTrack(){
        Track track1 = new Track(2,"pade pade","nice");
        Track track2 = new Track(3,"darlingey","super");
        trackRepository.save(track1);
        trackRepository.save(track2);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("pade pade",list.get(0).getTrackName());
    }

    @Test
    public void testDeleteTrack(){
        Track track=new Track(2,"mellaga karagani","super");
        trackRepository.delete(track);
        boolean deletedTrack=trackRepository.existsById(2);
        Assert.assertEquals(false,deletedTrack);
    }

    @Test
    public void testDeleteTrackFailure(){
        Track track=new Track(2,"mellaga kargani","super");
        trackRepository.delete(track);
        boolean deletedTrack=trackRepository.existsById(2);
        Assert.assertNotSame(true,deletedTrack);
    }


    @Test
    public void testUpdateTrack(){
        Track track=new Track(3,"undiporadhe","nice song");
        Track track1=new Track(4,"pade pade","nice song");
        trackRepository.save(track);
        trackRepository.save(track1);
        trackRepository.findById(track.getTrackId()).get().setTrackComments("super");
        List<Track> list=trackRepository.findAll();
        Assert.assertEquals("super",list.get(0).getTrackComments());
    }

    @Test
    public void testUpdateTrackFailure(){
        Track track=new Track(3,"undiporadhe","super song");
        Track track1=new Track(4,"lovely","nice song");
        trackRepository.save(track);
        trackRepository.save(track1);
        trackRepository.findById(track.getTrackId()).get().setTrackComments("super");
        List<Track> list=trackRepository.findAll();
        Assert.assertNotSame("nice song",list.get(0).getTrackComments());
    }

}


