package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;

import java.util.List;

public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;
    public List<Track> getAllTrack();
    public  boolean delete(Track track) throws TrackNotFoundException;
    public Track updateComments(Track track) throws  TrackNotFoundException;
    public List<Track> getTrackByName(String trackName) throws Exception;
}
