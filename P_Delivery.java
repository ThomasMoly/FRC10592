package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class P_Delivery{
    // Kit-Bot Delivery System for the pipes
    public static final PWMSparkMax P_delivery = Constants.P_motorDelivery;
    public static final XboxController controller = Constants.P_CONTROLLER;

    public void autonomousPeriodic() {
        // should set the speed of the motor for the delivery system
        P_delivery.set(.325);
    }

    public void autonomousEND() {
        // should set the speed of the motor for the delivery system
        P_delivery.stopMotor();
    }

    public void teleopPeriodic() {
        // Control the motor with the controller trigger
        double x = 0;
        double speed = 0;
        boolean button = controller.getAButtonPressed(); // Adjust based on control scheme
        if(button == true){
            speed = .7;
        }
        while (x <= 200) {
            P_delivery.set(speed); // Runs the motor with trigger input
            x = x + .01;
        }
    }
}
