package frc.robot;

import com.kauailabs.navx.frc.AHRS; 
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilib j.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class Autonomous{
  public static AHRS gyro = Constants.gyro();
  public static DifferentialDrive m_robotDrive = Constants.m_robotDrive;
  public static Timer m_timer = Constants.m_timer;
  public static P_Delivery delivery = new P_Delivery();

   public void start() {
// reset the gyro.reset();\
     m_timer.reset();
     gyro.reset(); 
    }

  public void turnRight(int angle) { //angle is always inputed as positive
    gyro.reset(); 
    
    while (gyro.getAngle() < angle) { 
      Constants.m_robotDrive.arcadeDrive(0.1, .6); //Adjust speed and turn rate as needed
    }
    //stop once angle is reached
    constants.m_robotDrive.stopMotor(); 
  }

  public void turnLeft(int angle) { //angle is always inputed as positive
    gyro.reset(); 
    
    while (gyro.getAngle() > (-1)(angle)) {
      Constants.m_robotDrive.arcadeDrive(0.1, -.6); //Adjust speed and turn rate as needed
    }
    //stop once angle is reached
    constants.m_robotDrive.stopMotor(); 
  }

  public void Autonomous_Middle(){
    if (m_timer.get() < 2.0) {
      // Drive forwards half speed, make sure to turn input squaring off
      m_robotDrive.arcadeDrive(0.6,  0.08, false);
    }
    else if (m_timer.get() > 2 && m_timer.get() < 3.0){
      m_robotDrive.stopMotor(); // stop robot
      delivery.autonomousPeriodic();
    }
    else if (m_timer.get() > 3 && m_timer.get() < 4){
      delivery.autonomousEND();
      m_robotDrive.arcadeDrive(-0.5,  -0.08, false);
    }
    else if (m_timer.get() > 4 && m_timer.get() < 4.4){
      turnLeft(90);
    }
    else if (m_timer.get() > 4.4 && m_timer.get() < 5){
      m_robotDrive.arcadeDrive(-0.6,  -0.08, false);
    }
    else if (m_timer.get() > 5 && m_timer.get() < 6){
      turnLeft(90);
    }
     else if (m_timer.get() > 6 && m_timer.get() < 9){
      m_robotDrive.arcadeDrive(-1,  -0.08, false);
    }
     else if (m_timer.get() > 9 && m_timer.get() < 10){
      m_robotDrive.stopMotor(); // stop robot
    }
    else if (m_timer.get() > 10 && m_timer.get() < 11){
      m_robotDrive.arcadeDrive(0.8,  0.08, false);
    }
    else if (m_timer.get() > 11 && m_timer.get() < 11.5){
      m_robotDrive.stopMotor(); // stop robot
      delivery.autonomousPeriodic();    
    }
    else if (m_timer.get() > 11.5 && m_timer.get() < 13.5){
      delivery.autonomousEND();
      m_robotDrive.arcadeDrive(-0.6,  -0.08, false);
    }
    else if (m_timer.get() > 11.5 && m_timer.get() < 13.5){
      m_robotDrive.stopMotor(); // stop robot
    }
  }
  
}
  

