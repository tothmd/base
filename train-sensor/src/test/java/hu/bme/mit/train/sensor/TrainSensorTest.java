package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;


public class TrainSensorTest {


    /*
     * 1. negative speedlimit
     * 2. 500+ speed limit
     * 3. setting speed limit to less then 50% of refrence speed
     * 4. setting a normal speed limit dor no alarm
     */

    @Before
    public void before() {
        mockDA = mock(DataAccess.class);
        TrainSensorImpl trainsensor;
    }

    @Test
    public void ThisIsAnExampleTestStub() {
        // TODO Delete this and add test cases based on the issues
    }

}
