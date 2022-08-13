package com.kaankaplan.basketballplayermanagementproject.business.concretes;

import com.kaankaplan.basketballplayermanagementproject.business.abstracts.PlayerService;
import com.kaankaplan.basketballplayermanagementproject.business.abstracts.PositionService;
import com.kaankaplan.basketballplayermanagementproject.dataAccess.PlayerDao;
import com.kaankaplan.basketballplayermanagementproject.dto.PlayerDto;
import com.kaankaplan.basketballplayermanagementproject.entity.Player;
import com.kaankaplan.basketballplayermanagementproject.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerManager implements PlayerService {

    private final PlayerDao playerDao;
    private final PositionService positionService;

    @Autowired
    public PlayerManager(PlayerDao playerDao, PositionService positionService) {
        this.playerDao = playerDao;
        this.positionService = positionService;
    }

    @Override
    public List<Player> getAllPlayers() {
        return this.playerDao.findAll();
    }

    @Override
    public void addPlayer(PlayerDto playerDto) {

        Position position = this.positionService.getPositionById(playerDto.getPositionId());

        if (position != null){
            Player player = Player.builder()
                    .name(playerDto.getName())
                    .surname(playerDto.getSurname())
                    .position(position)
                    .build();
            this.playerDao.save(player);
        }

        throw new NullPointerException("Position with given id is not found");
    }

    @Override
    public void deletePlayer(int playerId) {

        this.playerDao.deleteById(playerId);
    }
}
