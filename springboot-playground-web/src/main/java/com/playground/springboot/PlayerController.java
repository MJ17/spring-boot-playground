package com.playground.springboot;


import com.playground.springboot.repository.PlayerRepository;
import com.playground.springboot.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable("id") Long id) {
        log.error("get player");
        Player p = playerRepository.findById(id);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }

    @GetMapping("")
    public ResponseEntity<List<Player>> getPlayersByTeam(@RequestParam("teamId") Long teamId) {
        log.error("get player");

        if (teamId == null) {
            return ResponseEntity.badRequest().build();
        }

        Team team = teamRepository.findById(teamId);
        if (team == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Player> players = playerRepository.findAllByTeamId(teamId);
        return ResponseEntity.ok(players);
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestHeader HttpHeaders headers,
                               @RequestBody CreatePlayerRequest request) {
        log.error("createPlayer()");

        Team team = teamRepository.findById(request.getTeamId());
        if (team == null) {
            return ResponseEntity.badRequest().build();
        }

        Player player = Player.builder()
                .name(request.getName())
                .teamId(request.getTeamId())
                .build();

        Player result = playerRepository.save(player);
        return ResponseEntity.ok(result);
    }

}
