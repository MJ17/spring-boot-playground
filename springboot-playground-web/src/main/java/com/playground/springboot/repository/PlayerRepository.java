package com.playground.springboot.repository;

import com.playground.springboot.Player;
import com.playground.springboot.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.List;

@RepositoryDefinition(domainClass = Player.class, idClass = String.class)
public interface PlayerRepository {

    Player save(Player player);

    Player findById(Long id);

    @Query("SELECT player FROM Player player WHERE player.teamId = :teamId")
    List<Player> findAllByTeamId(@Param("teamId") Long teamId);

}
