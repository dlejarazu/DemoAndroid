package com.diego.civpocket.tests;

import com.diego.civpocket.logic.Biomes;
import com.diego.civpocket.logic.City;
import com.diego.civpocket.logic.Empire;
import com.diego.civpocket.logic.IllegalActionException;
import com.diego.civpocket.logic.Library;
import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.Tribe;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;

/**
 * Created by diego on 23/06/2015.
 * Tests for library class
 */
@RunWith(MockitoJUnitRunner.class)
public class LibraryTest {

    @Bind @Mock Empire mockEmpire;
    @Inject Library sut;

    @Before
    public void setup(){
        Guice.createInjector(BoundFieldModule.of(this)).injectMembers(this);
    }

    @Test(expected = IllegalActionException.class)
    public void testCantPurchaseFromARegionWithNoCity()
    {
        Region emptyRegion = mock(Region.class);
        given(mockEmpire.cityAt(emptyRegion)).willReturn(null);

        sut.acquireCartage(emptyRegion);
    }

    @Test
    public void testPurchaseAdvanceFromCityWithoutStone()
    {
        Region plainsRegion = mock(Region.class);
        given(mockEmpire.cityAt(plainsRegion)).willReturn(mock(City.class));
        assertThat(sut.canResearchCartageFrom(plainsRegion),is(false));
    }

    @Test
      public void testPurchaseAdvanceFromCityWithStone()
    {
        Region mountainsRegion = mock(Region.class);
        given(mountainsRegion.has(Biomes.Mountain)).willReturn(true);
        given(mockEmpire.cityAt(mountainsRegion)).willReturn(mock(City.class));
        given(mockEmpire.populationAt(mountainsRegion)).willReturn(2);

        assertThat(sut.canResearchCartageFrom(mountainsRegion), is(true));
    }

    @Test
         public void testPurchaseAdvanceFromCityWithNoTribes()
    {
        Region mountainsRegion = mock(Region.class);
        given(mountainsRegion.has(Biomes.Mountain)).willReturn(true);
        given(mockEmpire.cityAt(mountainsRegion)).willReturn(mock(City.class));

        assertThat(sut.canResearchCartageFrom(mountainsRegion), is(false));
    }

    @Test
    public void testPurchaseAdvanceFromCityWithNoCity()
    {
        Region mountainsRegion = mock(Region.class);
        given(mountainsRegion.has(Biomes.Mountain)).willReturn(true);
        given(mockEmpire.cityAt(mountainsRegion)).willReturn(null);
        given(mockEmpire.populationAt(mountainsRegion)).willReturn(2);

        assertThat(sut.canResearchCartageFrom(mountainsRegion), is(false));
    }
}
