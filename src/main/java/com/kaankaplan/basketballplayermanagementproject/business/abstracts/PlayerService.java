package com.kaankaplan.basketballplayermanagementproject.business.abstracts;

import com.kaankaplan.basketballplayermanagementproject.dto.PlayerDto;
import com.kaankaplan.basketballplayermanagementproject.entity.Player;

import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayers();

    void addPlayer(PlayerDto playerDto);

    void deletePlayer(int playerId);
}
