package com.kaankaplan.basketballplayermanagementproject.controller;

import com.kaankaplan.basketballplayermanagementproject.business.abstracts.PlayerService;
import com.kaankaplan.basketballplayermanagementproject.dto.PlayerDto;
import com.kaankaplan.basketballplayermanagementproject.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


@Controller
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @QueryMapping
    public Iterable<Player> getAllPlayers() {
        return this.playerService.getAllPlayers();
    }

    @MutationMapping
    public Player addPlayer(@Argument PlayerDto playerDto) {
        return this.playerService.addPlayer(playerDto);
    }

    @MutationMapping
    public Integer deletePlayer(@Argument int playerId) {
        return this.playerService.deletePlayer(playerId);
    }
}
