package com.diego.civpocket.tests;

import com.diego.civpocket.logic.Region;
import com.diego.civpocket.logic.Tribe;

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.mockito.Mock;
import static org.junit.Assert.*;

/** 
* Tribe Tester. 
* 
* @author dieort
* @since <pre>May 19, 2015</pre> 
* @version 1.0 
*/ 
public class TribeTest {

    @Mock Region testRegion;
    Tribe sut = new Tribe();

@Before
public void before() throws Exception {
} 

@After
public void after() throws Exception { 
}



/** 
* 
* Method: moveTo(Region destination) 
* 
*/ 
@Test
public void testMoveTo() throws Exception {
    //Give
    sut.moveTo(testRegion);
    //When
    Region destination = sut.getLocation();
    //then
    assertEquals(testRegion,destination);
}


} 
