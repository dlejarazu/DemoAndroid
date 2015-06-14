package com.diego.civpocket.tests;

import com.diego.civpocket.logic.Biomes;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.Scenario;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by diego on 14/06/2015.
 */
public class ScenarioTest {

    @Test
    public void testFirstScenario()
    {
        //Given
        Scenario sut = new Scenario("A New World");
        //When
        Region[] scenarioMap = sut.getMap();
        //Then
        assertEquals(7,scenarioMap.length);
        assertTrue(sut.getRegionByName("1").has(Biomes.Forest));//        Region 1: 1 Forest
        assertTrue(sut.getRegionByName("2").has(Biomes.Dessert));//        Region 2: 1 Desert
        assertTrue(sut.getRegionByName("3").has(Biomes.Dessert));//        Region 3: 1 Desert
        assertTrue(sut.getRegionByName("4").has(Biomes.Dessert));//        Region 4: 1 Desert
        assertTrue(sut.getRegionByName("5").has(Biomes.Mountain));//        Region 5: 1 Mountain
        assertTrue(sut.getRegionByName("7").has(Biomes.Forest));//        Region 7: 1 Forest
        assertTrue(sut.getRegionByName("8").has(Biomes.Mountain));//        Region 8: 1 Mountain, 1 Forest
        assertTrue(sut.getRegionByName("8").has(Biomes.Forest));
    }

}
