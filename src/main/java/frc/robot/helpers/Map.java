package frc.robot.helpers;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name.
 * This provides flexibility changing wiring, makes checking the wiring easier and significantly
 * reduces the number of magic numbers floating around.
 */
public class Map {
  /**
   * PCM CAN index, set using the Phoenix Tuner software.
   */
  public static final int PCM_CAN_INDEX = 0;

  /**
   * Front left motor CAN index, set using the Phoenix Tuner software.
   */
  public static final int FRONT_LEFT_MOTOR_CAN = 2;

  /**
   * Rear left motor CAN index, set using the Phoenix Tuner software.
   */
  public static final int REAR_LEFT_MOTOR_CAN = 3;

  /**
   * Rear right motor CAN index, set using the Phoenix Tuner software.
   */
  public static final int REAR_RIGHT_MOTOR_CAN = 5;

  /**
   * Front right motor CAN index, set using the Phoenix Tuner software.
   */
  public static final int FRONT_RIGHT_MOTOR_CAN = 6;

  /**
   * Intake CAN index, set using the Phoenix Tuner software.
   */
  public static final int INTAKE_MOTOR_CAN = 4;

  /**
   * Elevator CAN index, set using the Phoenix Tuner software.
   */
  public static final int ELEVATOR_MOTOR_CAN = 7;

  /**
   * Index of the joystick used to drive the robot, can be accessed from the USB section of the
   * driver station.
   */
  public static final int MAIN_JOYSTICK_INDEX = 0;

  /**
   * Index of the xbox controller used to operate the mechanisms, can be accessed from the USB
   * section of the driver station.
   */
  public static final int XBOX_INDEX = 1;

  /**
   * While holding this button, the direction the robot drives will be reversed, can be accessed
   * from the USB section of the driver station.
   */
  public static final int REVERSE_DIRECTION = 4;

  /**
   * Button to change gears.
   */
  public static final int SHIFT_BUTTON = 2;

  /**
   * Solenoid index for right gearbox solenoid extended.
   */
  public static final int RIGHT_GEARBOX_EXTENDED = 0;

  /**
   * Solenoid index for right gearbox reversed.
   */
  public static final int RIGHT_GEARBOX_REVERSED = 1;

  /**
   * Solenoid index for left gearbox extended.
   */
  public static final int LEFT_GEARBOX_EXTENDED = 2;

  /**
   * Solenoid index for left gearbox reversed.
   */
  public static final int LEFT_GEARBOX_REVERSED = 3;

  /**
   * Axis for operating the intake mechanism inward.
   */
  public static final int INTAKE_IN_AXIS = 3;

  /**
   * Axis for operating the intake mechanism outward.
   */
  public static final int INTAKE_OUT_AXIS = 2;

  /**
   * Axis for operating the elevator at 20% speed.
   */
  public static final int ELEVATOR_SLOW_AXIS = 5;

  /**
   * Axis for operating the elevator at 80% speed.
   */
  public static final int ELEVATOR_FAST_AXIS = 1;

  /**
   * Button for opening the panel clutch mechanism.
   */
  public static final int PANEL_CLUTCH_OPEN_BUTTON = 5;

  /**
   * Button for closing the panel clutch mechanism.
   */
  public static final int PANEL_CLUTCH_CLOSE_BUTTON = 6;

  /**
   * Button for switching the cameras.
   */
  public static final int SWITCH_CAMERA_BUTTON = 5;

  /**
   * Button for incrementing the speed by 10%.
   */
  public static final int INCREMENT_SPEED_BUTTON = 7;

  /**
   * Button for decrementing the speed by 10%.
   */
  public static final int DECREMENT_SPEED_BUTTON = 9;

  /**
   * Button for toggling the gyroscope during teleop.
   */
  public static final int TOGGLE_GYROSCOPE_BUTTON = 3;

  /**
   * Button for operating the robot with vision assist.
   */
  public static final int VISION_ASSIST_BUTTON = 1;

  /**
   * Button for operating the robot with fine turning.
   */
  public static final int FINE_TURNING_BUTTON = 16;

  /**
   * POV button for extending the front lift piston.
   */
  public static final int FRONT_LIFT_UP_BUTTON = 0;

  /**
   * POV button for retracting the front lift piston.
   */
  public static final int FRONT_LIFT_DOWN_BUTTON = 180;

  /**
   * POV button for extending the rear lift piston.
   */
  public static final int REAR_LIFT_UP_BUTTON = 90;

  /**
   * POV button for retracting the rear lift piston.
   */
  public static final int REAR_LIFT_DOWN_BUTTON = 270;

  /**
   * Button for disabling the compressor.
   */
  public static final int COMPRESSOR_DISABLE_BUTTON = 1;
}
