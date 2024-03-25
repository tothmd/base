package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class TrainSensorTest {


    TrainSensor sensor;
    TrainController controller;
    TrainUser user;

    /*
     * 1. negative speedlimit
     * 2. 500+ speed limit
     * 3. setting speed limit to less then 50% of refrence speed
     * 4. setting a normal speed limit dor no alarm
     */

    @Before
    public void before() {
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);
        sensor = new TrainSensorImpl(controller, user);
    }

    @Test
    public void TestNegativeSpeedLimit() {
        
    }

    @Test
    public void TestDrasticDecreaseInSpeed(){
        when(controller.getReferenceSpeed()).thenReturn(150);
        sensor.overrideSpeedLimit(50);
        verify(user, times(1)).setAlarmState(true);;
    }



}
