// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
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
    
    @Override
    public void robotInit() {
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
    if (m_timer.get() < 7.5) {
      // Drive forwards half speed, make sure to turn input squaring off
      m_robotDrive.arcadeDrive(0.20, 0.0, false);
      
    }
    else if (m_timer.get() > 7.5 && m_timer.get() < 10.0){
      m_robotDrive.stopMotor(); // stop robot
      delivery.autonomousPeriodic();
    } 
    else if(m_timer.get() > 10 && m_timer.get() < 17.5) {
      delivery.autonomousEND();
      m_robotDrive.arcadeDrive(-0.20, 0.0, false);
    }
    else{
      m_robotDrive.stopMotor(); // stop robot
    }
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
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