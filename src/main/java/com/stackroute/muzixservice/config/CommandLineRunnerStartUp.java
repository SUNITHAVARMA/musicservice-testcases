package com.stackroute.muzixservice.config;


import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerStartUp implements CommandLineRunner {

    @Value("${track2.id}")
    private int id;
    @Value("${track2.name}")
    private String name;
    @Value("${track2.comment}")
    private String comments;

    private TrackRepository trackRepository;
    Track track=new Track();

    @Autowired
    public CommandLineRunnerStartUp(TrackRepository trackRepository){
        this.trackRepository=trackRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        track.setTrackId(id);
        track.setTrackName(name);
        track.setTrackComments(comments);
        trackRepository.save(track);
    }

}
