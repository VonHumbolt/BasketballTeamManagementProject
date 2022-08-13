package com.kaankaplan.basketballplayermanagementproject.business.concretes;

import com.kaankaplan.basketballplayermanagementproject.dto.PositionDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PositionManagerTest {

    @Mock
    private PositionDao positionDao;

    @InjectMocks
    private PositionManager positionManager;

    @Test
    void itShouldGetPositionById() {

        int positionId= 1;

        // when
        positionManager.getPositionById(positionId);

        // then
        verify(positionDao).getReferenceById(positionId);
    }
}