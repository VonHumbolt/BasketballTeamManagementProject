package com.kaankaplan.basketballplayermanagementproject.dataAccess;

import com.kaankaplan.basketballplayermanagementproject.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerDao extends JpaRepository<Player, Integer> {

}
