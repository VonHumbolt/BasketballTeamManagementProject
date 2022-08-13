package com.kaankaplan.basketballplayermanagementproject.business.concretes;

import com.kaankaplan.basketballplayermanagementproject.business.abstracts.PlayerService;
import com.kaankaplan.basketballplayermanagementproject.business.abstracts.PositionService;
import com.kaankaplan.basketballplayermanagementproject.dataAccess.PlayerDao;
import com.kaankaplan.basketballplayermanagementproject.dto.PlayerDto;
import com.kaankaplan.basketballplayermanagementproject.entity.Player;
import com.kaankaplan.basketballplayermanagementproject.entity.Position;
import com.kaankaplan.basketballplayermanagementproject.utils.Constant;
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
    public Player addPlayer(PlayerDto playerDto) {

        Position position = this.positionService.getPositionById(playerDto.positionId());
        int numberOfPlayersInTeam = this.playerDao.getNumberOfPlayers();

        if (position != null){
            if (numberOfPlayersInTeam < Constant.TEAM_CAPACITY){
                Player player = Player.builder()
                        .name(playerDto.name())
                        .surname(playerDto.surname())
                        .position(position)
                        .build();
                return this.playerDao.save(player);
            }
            throw new RuntimeException("The basketball team capacity is full");
        }

        throw new RuntimeException("Position with given id is not found");
    }

    @Override
    public Integer deletePlayer(int playerId) {

        Player playerFromDb = this.playerDao.getPlayerByPlayerId(playerId);

        if(playerFromDb != null) {
            this.playerDao.deleteById(playerId);
            return playerId;
        }
        throw new RuntimeException("Player with given id is not exist.");
    }
}
