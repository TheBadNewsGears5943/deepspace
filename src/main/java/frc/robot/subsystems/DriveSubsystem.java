package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.TeleopCommand;
import frc.robot.helpers.Constants;
import frc.robot.helpers.Map;
import frc.robot.helpers.Utils.RobotSide;

/**
 * This subsystem is used to manage the components used to drive the robot, including the drive
 * train, the gyroscope, and the encoders.
 */
public class DriveSubsystem extends Subsystem {
  private static DriveSubsystem instance = new DriveSubsystem();

  private final WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(Map.FRONT_LEFT_MOTOR_CAN);
  private final WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(Map.FRONT_RIGHT_MOTOR_CAN);
  private final WPI_TalonSRX rearLeftMotor = new WPI_TalonSRX(Map.REAR_LEFT_MOTOR_CAN);
  private final WPI_TalonSRX rearRightMotor = new WPI_TalonSRX(Map.REAR_RIGHT_MOTOR_CAN);

  private final SpeedControllerGroup leftDrive =
      new SpeedControllerGroup(frontLeftMotor, rearLeftMotor);
  private final SpeedControllerGroup rightDrive =
      new SpeedControllerGroup(frontRightMotor, rearRightMotor);

  private final DifferentialDrive differentialDrive = new DifferentialDrive(leftDrive, rightDrive);

  private final AHRS gyro = new AHRS(SPI.Port.kMXP);

  private int driveDirection = 1;
  private double turningMultiplier = 1.0d;

  /**
   * Drives the robot using the given parameters.
   *
   * @param driveSpeed  Robot's forward/backward driving speed
   * @param rotateSpeed Robot's rotation driving speed
   */
  public void drive(double driveSpeed, double rotateSpeed) {
    differentialDrive.arcadeDrive(driveSpeed, rotateSpeed);
  }

  /**
   * Drives the robot without squaring the inputs, useful for autonomous.
   *
   * @param driveSpeed  Robot's forward/backward driving speed
   * @param rotateSpeed Robot's rotation driving speed
   */
  public void autoDrive(double driveSpeed, double rotateSpeed) {
    differentialDrive.arcadeDrive(driveSpeed, rotateSpeed, false);
  }

  /**
   * Sets the direction the robot is driving.
   * 
   * @param forward Driving forwards is true, driving backwards is false.
   */
  public void setDirection(boolean forward) {
    driveDirection = (forward) ? 1 : -1;
  }

  /**
   * Gets the direction the robot is driving.
   *
   * @return The direction the robot is driving, 1.0d for forward, -1.0d for 
   *     backwards
   */
  public int getDirection() {
    return driveDirection;
  }

  /**
   * Sets the throttle used when turning the robot.
   * 
   * @param turningMultiplier The amount to throttle turning by
   */
  public void setTurningThrottle(double turningMultiplier) {
    this.turningMultiplier = turningMultiplier;
  }

  /**
   * Gets the throttle used when turning the robot.
   * 
   * @return The amount to throttle turning by
   */
  public double getTurningThrottle() {
    return turningMultiplier;
  }

  /**
   * Get the encoder value for the master of the encoders.
   *
   * @return position
   */
  public double getEncoderValue() {
    return frontLeftMotor.getSelectedSensorPosition(0);
  }

  /**
   * Get the encoder value for a certain side.
   *
   * @param side Side of a robot
   * @return position
   */
  public double getEncoderValue(RobotSide side) {
    switch (side) {
      case Right:
        return frontRightMotor.getSelectedSensorPosition(0);
      case Left:
        return frontLeftMotor.getSelectedSensorPosition(0);
      default:
        return 0.0d;
    }
  }

  /**
   * Reset encoder positions for both front motors.
   */
  public void resetEncoder() {
    frontLeftMotor.setSelectedSensorPosition(0, 0, 1000);
    frontRightMotor.setSelectedSensorPosition(0, 0, 1000);
  }

  /**
   * Reset the front motor of the specified {@link RobotSide}.
   *
   * @param side Side of the robot to reset the front motor of
   */
  public void resetEncoder(RobotSide side) {
    switch (side) {
      case Left:
        frontLeftMotor.setSelectedSensorPosition(0, 0, 1000);
        break;
      case Right:
        frontRightMotor.setSelectedSensorPosition(0, 0, 1000);
        break;
      default:
        break;
    }
  }

  /**
   * Initialize front two motors of the robot.
   */
  public void initEncoder() {
    frontLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 1000);
    frontRightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 1000);
  }

  /**
   * Initialize the front motor of the specified {@link RobotSide}.
   *
   * @param side Side of robot to check encoder for
   */
  public void initEncoder(RobotSide side) {
    switch (side) {
      case Left:
        frontLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0,
            1000);
        break;
      case Right:
        frontRightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0,
            1000);
        break;
      default:
        break;
    }
  }

  /**
   * Get the current amount of inches the encoders have tracked.
   *
   * @return Amount of inches encoders have tracked
   */
  public double getEncoderInches() {
    return ticks2Inches(getEncoderValue());
  }

  /**
   * Get the current amount of inches encoders have tracked for a certain {@link RobotSide}.
   *
   * @param side Side of a robot
   * @return The amount of inches the encoder has tracked on the specified {@link RobotSide}
   */
  public double getEncoderInches(RobotSide side) {
    return ticks2Inches(getEncoderValue(side));
  }

  /**
   * Converts inches into encoder ticks, using the circumference of the wheel.
   *
   * @param inches Amount of inches the robot has traveled
   * @return Amount of encoder ticks this would represent
   */
  public static int inches2ticks(double inches) {
    return (int) ((4096 / Constants.CIRCUMFERENCE_OF_WHEEL) * inches);
  }

  /**
   * Converts encoder ticks (4096 in a full rotation) to inches, using the circumference of the
   * wheel.
   *
   * @param ticks Encoder ticks
   * @return Amount of inches this amount of ticks represents
   */
  public static int ticks2Inches(double ticks) {
    return (int) ((ticks / 4096) * Constants.CIRCUMFERENCE_OF_WHEEL);
  }

  /**
   * Resets the gyroscope.
   */
  public void resetGyro() {
    gyro.reset();
  }

  /**
   * Gets the current value of the gyroscope.
   *
   * @return The value of the gyroscope in degrees
   */
  public double getAngle() {
    return gyro.getAngle();
  }

  /**
   * Gets the maximum speed the robot is set to move at.
   *
   * @return Maximum Robot Speed
   */
  public static double getMaxSpeed() {
    return SmartDashboard.getNumber(Constants.MAX_SPEED_KEY, 1.0d);
  }

  /**
   * Gets the minimum speed the robot is set to move at.
   *
   * @return Minimum Robot Speed
   */
  public static double getMinSpeed() {
    return SmartDashboard.getNumber(Constants.MIN_SPEED_KEY, 0.5d);
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new TeleopCommand());
  }

  /**
   * Return an instance of this class.
   *
   * @return DriveSubsystem instance
   */
  public static DriveSubsystem getInstance() {
    return instance;
  }
}
