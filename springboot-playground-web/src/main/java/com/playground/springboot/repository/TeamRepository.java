package com.playground.springboot.repository;

import com.playground.springboot.Team;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Team.class, idClass = String.class)
public interface TeamRepository {

    Team save(Team team);

    List<Team> findAll();

    Team findById(Long id);

}
