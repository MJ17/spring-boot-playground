package com.playground.springboot;


import com.playground.springboot.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    TeamRepository teamRepository;

    @GetMapping("")
    public ResponseEntity<List<Team>> getTeamList() {
        log.error("get team list");

        List<Team> teams = teamRepository.findAll();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable("id") Long id) {
        log.error("get a team");
        Team team = teamRepository.findById(id);

        if (team == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(team);
    }

    @PostMapping("")
    public ResponseEntity<Team> createTeam(@RequestHeader HttpHeaders headers,
                               @RequestBody CreateTeamRequest request) {

        log.error("createTeam() : name={}", request.getName());

        Team team = Team.builder()
                .name(request.getName())
                .build();

        Team result = teamRepository.save(team);

        return ResponseEntity.ok(result);
    }

}
