package frc.robot.helpers;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * The RobotConstants class is used for setting the constants used by the robot for it to do
 * calculations. The values here are mostly used for autonomous, but the robot might also use them
 * for other calculations as well.
 */
public class Constants {
  /**
   * Diameter of robot wheels in inches.
   */
  public static final double DIAMETER_OF_WHEEL = 8.0d;

  /**
   * Circumference of the wheel in inches.
   */
  public static final double CIRCUMFERENCE_OF_WHEEL = DIAMETER_OF_WHEEL * Math.PI;

  /**
   * Width of the robot.
   */
  public static final double ROBOT_WIDTH = 36.5d;

  /**
   * kP value used for correcting the robot's drift when driving straight.
   */
  public static final double GYRO_CORRECTION_KP = 0.04d;

  /**
   * Value used when throttling the turning speed of the robot.
   */
  public static final double TURNING_THROTTLE = 0.5d;

  public static final double TURNING_FULL = 1.0d;

  /**
   * SmartDashboard key for maximum robot speed.
   */
  public static final String MAX_SPEED_KEY = "Maximum Robot Speed";

  /**
   * SmartDashboard key for minimum robot speed.
   */
  public static final String MIN_SPEED_KEY = "Minimum Robot Speed";

  /**
   * SmartDashboard key for configuring automatically turning the compressor on or off during a 
   * brownout.
   */
  public static final String AUTO_COMPRESSOR_BROWNOUT_KEY = "Poweroff compressor during brownout";

  /**
   * Pneumatics solenoid value for high gear.
   */
  public static final DoubleSolenoid.Value HIGH_GEAR = DoubleSolenoid.Value.kForward;

  /**
   * Pneumatics solenoid value for low gear.
   */
  public static final DoubleSolenoid.Value LOW_GEAR = DoubleSolenoid.Value.kReverse;

  /**
   * The amount of time the compressor will stop after a brownout.
   */
  public static final double COMPRESSOR_BROWNOUT_TIME = 6.0d;
}
