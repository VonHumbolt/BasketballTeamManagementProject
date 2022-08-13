package com.kaankaplan.basketballplayermanagementproject.business.concretes;

import com.kaankaplan.basketballplayermanagementproject.business.abstracts.PositionService;
import com.kaankaplan.basketballplayermanagementproject.dto.PositionDao;
import com.kaankaplan.basketballplayermanagementproject.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionManager implements PositionService {

    private final PositionDao positionDao;

    @Autowired
    public PositionManager(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @Override
    public Position getPositionById(int positionId) {
        return this.positionDao.getReferenceById(positionId);
    }
}
