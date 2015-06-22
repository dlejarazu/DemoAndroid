package com.diego.civpocket.tests.MapPresenter;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.diego.civpocket.logic.CityBuilder;
import com.diego.civpocket.logic.CivPocketGame;
import com.diego.civpocket.logic.Scenario;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.MapPresenter;
import com.diego.civpocket.logic.MapUpdater;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.tests.FakeCityBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;

import static org.mockito.BDDMockito.*;

public class MapPresenterTester {

    @Bind protected CivPocketGame testGame = mock(CivPocketGame.class);
    @Bind protected Scenario testScenario = mock(Scenario.class);
    @Bind protected Empire testEmpire = mock(Empire.class);
    @Bind protected MapUpdater testView = mock(MapUpdater.class);
    @Bind protected Region testRegion;

    @Inject protected MapPresenter sut;

    protected class TestPresenterModule extends AbstractModule {
		@Override
		public void configure() {
		}
		@Provides CityBuilder provideFakeBuilder(Empire empire){
			return new FakeCityBuilder(empire);
		}
	}

    @Before
    public void setup(){
        Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
        testRegion = addRegionToScenario("testRegion");
    }

    protected void setGamePhase(CivPocketGame.GamePhase phase){
        given(testGame.getActualPhase()).willReturn(phase);
    }

    protected Region addRegionToScenario(String name) {
        Region testRegion = new Region(name);
        doReturn(testRegion).when(testScenario).getRegionByName(name);
        return testRegion;
    }
}