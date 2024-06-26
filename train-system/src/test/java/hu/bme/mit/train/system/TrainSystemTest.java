package hu.bme.mit.train.system;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.system.TrainSystem;

public class TrainSystemTest {

	TrainController controller;
	TrainSensor sensor;
	TrainUser user;
	
	@Before
	public void before() {
		TrainSystem system = new TrainSystem();
		controller = system.getController();
		sensor = system.getSensor();
		user = system.getUser();

		sensor.overrideSpeedLimit(50);
	}
	
	@Test
	public void OverridingJoystickPosition_IncreasesReferenceSpeed() {
		sensor.overrideSpeedLimit(10);

		Assert.assertEquals(0, controller.getReferenceSpeed());
		
		user.overrideJoystickPosition(5);

		controller.followSpeed();
		Assert.assertEquals(5, controller.getReferenceSpeed());
		controller.followSpeed();
		Assert.assertEquals(10, controller.getReferenceSpeed());
		controller.followSpeed();
		Assert.assertEquals(10, controller.getReferenceSpeed());
	}

	@Test
	public void OverridingJoystickPositionToNegative_SetsReferenceSpeedToZero() {
		user.overrideJoystickPosition(4);
		controller.followSpeed();
		user.overrideJoystickPosition(-5);
		controller.followSpeed();
		Assert.assertEquals(0, controller.getReferenceSpeed());
	}

	@Test
	public void EmergencyBreak(){
		sensor.overrideSpeedLimit(10);
		user.overrideJoystickPosition(7);
		controller.emergencyBreak();
		Assert.assertEquals(0, controller.getReferenceSpeed());
	}

	@Test
	public void StartAfterEmergencyBreak(){
		sensor.overrideSpeedLimit(10);
		user.overrideJoystickPosition(7);
		controller.emergencyBreak();
		user.overrideJoystickPosition(13);
		controller.followSpeed();

		Assert.assertEquals(10, controller.getReferenceSpeed());
	}

	@Test
	public void Tachograph(){
		int before = sensor.getTachograph().size();
		sensor.recordData();
		int after = sensor.getTachograph().size();
		Assert.assertTrue(before < after);
	}

	
}
