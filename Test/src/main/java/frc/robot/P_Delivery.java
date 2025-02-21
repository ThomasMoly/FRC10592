package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class P_Delivery extends TimedRobot {
    //Kit-Bot Delivery System for the pipes
    public static final PWMSparkMax P_delivery = Constants.P_motorDelivery;

    @Override
    public void Autonomous(){
        //should set the speed of the motor for the delivery system
        P_delivery.set(1); 
    }

    @Override
    public void Teleop{
        // Control the motor with the controller trigger
        double speed = controller.getRightTriggerAxis(); // Adjust based on control scheme
        P_delivery.set(speed); // Runs the motor with trigger input
    }
    
}
