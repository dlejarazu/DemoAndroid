package com.diego.civpocket.logic;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class CivPocketModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(Scenario.class).to(FirstScenario.class);
    }

    @Provides
    CityBuilder provideInitialBuilder(Empire empire){
        return new DefaultCityBuilder(empire);
    }
}
