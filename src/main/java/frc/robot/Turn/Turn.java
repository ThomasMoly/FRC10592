package frc.robot;

import com.kauailabs.navx.frc.AHRS; 
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilib j.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class Turn{
  public static AHRS gyro = Constants.gyro();
  public static DifferentialDrive m_robotDrive = Constants.m_robotDrive;

   public void start() {
// reset the gyro.reset(); 
    }

  public void turn(int angle) { //angle is always inputed as positive
    gyro.reset(); 

    if(angle < 0){
    while (gyro.getAngle() < math.abs(angle)) { 
      Constants.m_robotDrive.arcadeDrive(0.1, -1); //Adjust speed and turn rate as needed
    }
    else{
      while (gyro.getAngle() < math.abs(angle)) { 
      Constants.m_robotDrive.arcadeDrive(0.1, 1); //Adjust speed and turn rate as needed
    }
    }
    //stop once angle is reached
    constants.m_robotDrive.stopMotor(); 
  }
  }
  
}
