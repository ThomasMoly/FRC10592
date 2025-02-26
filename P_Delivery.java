package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class P_Delivery extends TimedRobot {
    // Kit-Bot Delivery System for the pipes
    public static final PWMSparkMax P_delivery = Constants.P_motorDelivery;
    public static final XboxController controller = Constants.P_CONTROLLER;

    @Override
    public void autonomousPeriodic() {
        // should set the speed of the motor for the delivery system
        P_delivery.set(.325);
    }

    public void autonomousEND() {
        // should set the speed of the motor for the delivery system
        P_delivery.stopMotor();
    }

    @Override
    public void teleopPeriodic() {
        // Control the motor with the controller trigger
        double speed = controller.getRightTriggerAxis(); // Adjust based on control scheme
        P_delivery.set(speed); // Runs the motor with trigger input
    }
}
