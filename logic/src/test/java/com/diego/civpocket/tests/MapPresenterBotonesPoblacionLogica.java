package com.diego.civpocket.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.diego.civpocket.logic.CivPocketGame.GamePhase;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MapPresenterBotonesPoblacionLogica extends MapPresenterTester {


	@Parameterized.Parameters (name = "{index}: button during phase {0} active - {1}")
	public static Collection<Object[]> supportValues() {
		return Arrays.asList(new Object[][]{
						{GamePhase.Events, false},
						{GamePhase.Advances, false},
						{GamePhase.Upkeep, false}
				}
		);
	}
	@Parameterized.Parameter public GamePhase phase;
	@Parameterized.Parameter(value = 1) public boolean shouldPopButtonsBeActive;

	@Test
	public void testCheckButtonsStatusDuringPhases() {
		//Given
		faseActual(phase);
		//Then
		boolean isBtnAddPopActivo = sut.isAddTribeActive();
		assertEquals(shouldPopButtonsBeActive, isBtnAddPopActivo);
		boolean isBtnRemPopActivo = sut.isMoveTribeActive();
		assertEquals(shouldPopButtonsBeActive, isBtnRemPopActivo);
	}
}
