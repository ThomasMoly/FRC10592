// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import com.kauailabs.navx.frc.AHRS; 
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilib j.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends TimedRobot {
    private final PWMSparkMax m_leftDrive1 = Constants.m_leftDrive1; // back left
    private final PWMSparkMax m_leftDrive2 = Constants.m_leftDrive2; // front left
    private final PWMSparkMax m_rightDrive1 = Constants.m_rightDrive1; // front right
    private final PWMSparkMax m_rightDrive2 = Constants.m_rightDrive2; // back right
    private final AHRS gyro = new AHRS(SPI.Port.kMXP); // gyro
    
    @Override
    public void robotInit() {
      gryo.reset(); 

        // Set m_leftDrive2 and m_rightDrive2 to follow the respective motors
        m_leftDrive2.addFollower(m_leftDrive1);  // front left motor follows back left motor
        m_rightDrive1.addFollower(m_rightDrive2); // back right motor follows front right motor
    }
  private final DifferentialDrive m_robotDrive = Constants.m_robotDrive;
  private final XboxController m_controller = Constants.m_controller;
  private final Timer m_timer = Constants.m_timer;
  private final P_Delivery delivery = new P_Delivery();

  /** Called once at the beginning of the robot program. */
  public Robot() {
    SendableRegistry.addChild(m_robotDrive, m_leftDrive2);
    SendableRegistry.addChild(m_robotDrive, m_rightDrive1);

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightDrive1.setInverted(true);
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    m_timer.restart();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // Drive for 5 seconds
    if (m_timer.get() < 5.0) {
      // Drive forwards half speed, make sure to turn input squaring off
      m_robotDrive.arcadeDrive(0.9,  0.1, false);
    }
    else if (m_timer.get() >= 5.2 && m_timer.get() < 7.0){
      m_robotDrive.stopMotor(); // stop robot
      delivery.autonomousPeriodic();
    }
    else if (m_timer.get() >= 7.2 && m_timer.get() < 9.0){
      m_robotDrive.arcadeDrive(-0.5,  0.1, false);
      //turnRight(90);
    }
    
    else{
      delivery.autonomousEND();
      m_robotDrive.stopMotor(); // stop robot
    }
  }


  public void turnRight(int angle) { //angle is always inputed as positive
    gyro.reset(); 
    
    while (gyro.getAngle() < angle) { 
      Constants.m_robotDrive.arcadeDrive(0.1, 1); //Adjust speed and turn rate as needed
    }
    //stop once angle is reached
    constants.m_robotDrive.stopMotor(); 
  }

  public void turnLeft(int angle) { //angle is always inputed as positive
    gyro.reset(); 
    
    while (gyro.getAngle() > (-1)(angle)) {
      Constants.m_robotDrive.arcadeDrive(0.1, -1); //Adjust speed and turn rate as needed
    }
    //stop once angle is reached
    constants.m_robotDrive.stopMotor(); 
  }


   
  // This function is called once each time the robot enters teleoperated mode. 
  @Override
  public void teleopInit() {}

  // This function is called periodically during teleoperated mode.
  @Override
  public void teleopPeriodic() {
    m_robotDrive.arcadeDrive(-m_controller.getLeftY()/1.25, m_controller.getRightX()/1.25);
    delivery.teleopPeriodic();
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}