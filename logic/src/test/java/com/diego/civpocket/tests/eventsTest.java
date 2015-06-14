package com.diego.civpocket.tests;

import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.EventCard;
import com.diego.civpocket.logic.IllegalActionException;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/** 
* CivPocketGame Tester. 
* 
* @author <diego>
* @since <pre>May 24, 2015</pre> 
* @version 1.0 
*/
@RunWith(MockitoJUnitRunner.class)
public class eventsTest {

    @Mock
    Empire mockEmpire;
    @InjectMocks
    CivPocketGame sut;

    @Test
    public void testDrawEvent() throws IllegalActionException {
        //Given
        while(sut.getActualPhase() != CivPocketGame.GamePhase.Events) sut.nextPhase();
        //When
        EventCard drawn = sut.drawEventCard();
        //then
        assertNotNull(drawn);
    }

} 
