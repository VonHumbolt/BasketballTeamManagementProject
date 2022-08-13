package com.kaankaplan.basketballplayermanagementproject.dto;

import com.kaankaplan.basketballplayermanagementproject.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionDao extends JpaRepository<Position, Integer> {
}
