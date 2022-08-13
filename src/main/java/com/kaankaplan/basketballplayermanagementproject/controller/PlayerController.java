package com.kaankaplan.basketballplayermanagementproject.controller;

import com.kaankaplan.basketballplayermanagementproject.business.abstracts.PlayerService;
import com.kaankaplan.basketballplayermanagementproject.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @QueryMapping
    public List<Player> getAllPlayers() {
        return this.playerService.getAllPlayers();
    }
}
