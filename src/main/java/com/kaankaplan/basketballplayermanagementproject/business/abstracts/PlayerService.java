package com.kaankaplan.basketballplayermanagementproject.business.abstracts;

import com.kaankaplan.basketballplayermanagementproject.dto.PlayerDto;
import com.kaankaplan.basketballplayermanagementproject.entity.Player;

import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayers();

    Player addPlayer(PlayerDto playerDto);

    Integer deletePlayer(int playerId);
}
