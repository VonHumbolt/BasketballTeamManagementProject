package com.kaankaplan.basketballplayermanagementproject.business.concretes;

import com.kaankaplan.basketballplayermanagementproject.business.abstracts.PositionService;
import com.kaankaplan.basketballplayermanagementproject.dataAccess.PlayerDao;
import com.kaankaplan.basketballplayermanagementproject.dto.PlayerDto;
import com.kaankaplan.basketballplayermanagementproject.entity.Player;
import com.kaankaplan.basketballplayermanagementproject.entity.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PlayerManagerTest {

    @Mock
    private PlayerDao playerDao;

    @Mock
    private PositionService positionService;

    @InjectMocks
    private PlayerManager playerManager;

    @Test
    void itShouldGetAllPlayers() {

        // when
        playerManager.getAllPlayers();

        // then
        verify(playerDao).findAll();
    }

    @Test
    void itShouldAddPlayer() {

        // given
        PlayerDto playerDto = new PlayerDto("Kaan", "Kaplan", 1);
        Position position = new Position(1, "PG", null);
        given(positionService.getPositionById(1)).willReturn(position);
        given(playerDao.getNumberOfPlayers()).willReturn(8);

        // when
        playerManager.addPlayer(playerDto);

        // then
        ArgumentCaptor<Player> playerArgumentCaptor = ArgumentCaptor.forClass(Player.class);
        verify(playerDao).save(playerArgumentCaptor.capture());
        Player result = playerArgumentCaptor.getValue();
        Player expected = Player.builder().playerId(0).name("Kaan").surname("Kaplan").position(position).build();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void willThrowException_WhenPositionIsNull() {
        // given
        PlayerDto playerDto = new PlayerDto("Kaan", "Kaplan", 1);
        given(positionService.getPositionById(1)).willReturn(null);
        given(playerDao.getNumberOfPlayers()).willReturn(8);

        // when
        assertThatThrownBy(() -> playerManager.addPlayer(playerDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Position with given id is not found");

        verify(playerDao, never()).save(any());
    }

    @Test
    void willThrowException_WhenTeamCapacityIsFull() {
        // given
        PlayerDto playerDto = new PlayerDto("Kaan", "Kaplan", 1);
        Position position = new Position(1, "PG", null);
        given(positionService.getPositionById(1)).willReturn(position);
        given(playerDao.getNumberOfPlayers()).willReturn(12);

        // when
        assertThatThrownBy(() -> playerManager.addPlayer(playerDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("The basketball team capacity is full");

        verify(playerDao, never()).save(any());
    }

    @Test
    void itShouldDeletePlayer() {
        int playerId = 1;
        Player player = Player.builder()
                            .playerId(playerId)
                            .name("Kaan")
                            .surname("Kaplan")
                            .position(null)
                            .build();

        given(playerDao.getPlayerByPlayerId(1)).willReturn(player);

        playerManager.deletePlayer(playerId);

        verify(playerDao).deleteById(playerId);
    }

    @Test
    void willThrowException_WhenPlayerNotExists_WithGivenId() {
        int playerId = 1;

        given(playerDao.getPlayerByPlayerId(1)).willReturn(null);

        assertThatThrownBy(() -> playerManager.deletePlayer(playerId))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Player with given id is not exist.");


        verify(playerDao, never()).deleteById(playerId);
    }
}