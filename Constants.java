package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class Constants{

    // Motor controllers for the left and right drive motors
    public static final PWMSparkMax m_leftDrive1 = new PWMSparkMax(3); // back left
    public static final PWMSparkMax m_leftDrive2 = new PWMSparkMax(2); // front left
    public static final PWMSparkMax m_rightDrive1 = new PWMSparkMax(1); // front right
    public static final PWMSparkMax m_rightDrive2 = new PWMSparkMax(0); // back right
    public static final PWMSparkMax P_motorDelivery = new PWMSparkMax(4); // Motor for the pipe delivery system
    
    public static final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftDrive2::set, m_rightDrive1::set);
    public static final XboxController m_controller = new XboxController(0);
    public static final XboxController P_CONTROLLER = new XboxController(1);
    public static final Timer m_timer = new Timer();
}