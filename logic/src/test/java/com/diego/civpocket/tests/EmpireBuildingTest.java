package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.diego.civpocket.logic.Biomes;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.IllegalActionException;

import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmpireBuildingTest {

	Empire sut = new Empire();
	@Mock Region testRegion;
	
	@Test
	public void testEmpireBuildsFarm_If_ItHasForestAndTwoTribes() throws IllegalActionException {
		given(testRegion.has(Biomes.Farm)).willReturn(false);
		given(testRegion.has(Biomes.Forest)).willReturn(true);

		sut.sendSettler(testRegion);
		sut.sendSettler(testRegion);

		//When
		boolean success = sut.buildFarm(testRegion);

        assertTrue(success);
		then(testRegion).should(times(1)).add(Biomes.Farm);
		then(testRegion).should(times(1)).decimate(Biomes.Forest);
        assertEquals(0, sut.tribesAt(testRegion).size());
	}

    @Test
    public void testEmpireCannotBuildFarmByDefault() throws IllegalActionException
    {
        boolean success = sut.buildFarm(testRegion);
        assertFalse(success);
        then(testRegion).should(never()).add(Biomes.Farm);
    }

	@Test
	public void testSinBosqueNoSePuedeConstruir() throws IllegalActionException {
		//Given
		given(testRegion.has(Biomes.Forest)).willReturn(false);
		//When
		boolean result = sut.canBuildFarmAt(testRegion);
		//Then
		assertFalse(result);
	}


}
