package com.diego.civpocket.tests;

import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.EventCard;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

/** 
* CivPocketGame Tester. 
* 
* @author <diego>
* @since <pre>May 24, 2015</pre> 
* @version 1.0 
*/ 
public class eventsTest {

    CivPocketGame sut = new CivPocketGame();

    @Test
    public void testDrawEvent(){
        //Given
        sut.setEra(1);
        while(sut.getActualPhase() != CivPocketGame.GamePhase.Events) sut.nextPhase();
        //When
        EventCard drawn = sut.drawEventCard();
        //then
        assertNotNull(drawn);
    }

} 
