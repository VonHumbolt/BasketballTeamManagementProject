package com.kaankaplan.basketballplayermanagementproject.dataAccess;

import com.kaankaplan.basketballplayermanagementproject.entity.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PlayerDaoTest {

    @Autowired
    private PlayerDao playerDao;

    @BeforeEach
    void setUp() {
        Player player = Player.builder()
                .playerId(1)
                .name("Kaan")
                .surname("Kaplan")
                .position(null)
                .build();
        Player player2 = Player.builder()
                .playerId(2)
                .name("Deniz")
                .surname("Kaplan")
                .position(null)
                .build();
        playerDao.save(player);
        playerDao.save(player2);
    }

    @AfterEach
    void tearDown() {
        playerDao.deleteAll();
    }

    @Test
    void itShouldGetNumberOfPlayers() {

        Integer result = playerDao.getNumberOfPlayers();
        Integer expected = 2;

        assertThat(expected).isEqualTo(result);
    }

    @Test
    void itShouldGetPlayerByPlayerId() {
        int playerId = 1;
        Player result = playerDao.getPlayerByPlayerId(playerId);
        Player expected = Player.builder()
                            .playerId(playerId)
                            .name("Kaan")
                            .surname("Kaplan")
                            .position(null)
                            .build();

        assertThat(expected).isEqualTo(result);
    }

    @Test
    void itShouldNotGetPlayerByPlayerId_WhenPlayerIsNotExist_WithGivenId() {
        int playerId = 7;
        Player result = playerDao.getPlayerByPlayerId(playerId);
        Player expected = null;

        assertThat(expected).isEqualTo(result);
    }
}