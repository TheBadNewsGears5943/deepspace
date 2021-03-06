package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.misc.AdjustSpeedCommand;
import frc.robot.commands.teleop.ChangeGearCommand;
import frc.robot.commands.teleop.FineTurningCommand;
import frc.robot.commands.teleop.HatchMechCommand;
import frc.robot.commands.teleop.HatchMechExtenderCommand;
import frc.robot.commands.teleop.LiftCommand;
import frc.robot.commands.teleop.ReverseCommand;
import frc.robot.commands.teleop.ToggleGyroscopeCommand;
import frc.robot.commands.teleop.VisionCommand;
import frc.robot.helpers.Map;

/**
 * Operator Interface class, used for defining the joysticks and buttons used to control the robot.
 */
public class OperatorInterface {

  /**
   * Joystick used to drive the robot.
   */
  public static final Joystick mainStick = new Joystick(Map.MAIN_JOYSTICK_INDEX);

  /**
   * Xbox controller used to operate the robot's mechanisms.
   */
  public static final XboxController xbox = new XboxController(Map.XBOX_INDEX);

  /**
   * Button for reversing the direction the robot is driving.
   */
  private static final Button reverseDirection =
    new JoystickButton(mainStick, Map.REVERSE_DIRECTION);

  /**
   * Button for change between high and low gears.
   */
  private static final Button gearShift = new JoystickButton(mainStick, Map.SHIFT_BUTTON);

  private static final Button extendHatchMech =
    new JoystickButton(xbox, Map.EXTEND_HATCH_MECH_BUTTON);

  private static final Button retractHatchMech =
    new JoystickButton(xbox, Map.RETRACT_HATCH_MECH_BUTTON);

  private static final Button extendHatchMechExtender =
    new JoystickButton(xbox, Map.EXTEND_HATCH_MECH_EXTENDER_BUTTON);

  private static final Button retractHatchMechExtender =
    new JoystickButton(xbox, Map.RETRACT_HATCH_MECH_EXTENDER_BUTTON);

  /**
   * Button for incrementing the speed.
   */
  private static final Button incrementSpeed =
    new JoystickButton(mainStick, Map.INCREMENT_SPEED_BUTTON);

  /**
   * Button for decrementing the speed.
   */
  private static final Button decrementSpeed =
    new JoystickButton(mainStick, Map.DECREMENT_SPEED_BUTTON);

  /**
   * Button for disabling the gyroscope.
   */
  private static final Button toggleGyroscope =
    new JoystickButton(mainStick, Map.TOGGLE_GYROSCOPE_BUTTON);

  /**
   * Button for operating the robot with vision control.
   */
  private static final Button visionAssist =
    new JoystickButton(mainStick, Map.VISION_ASSIST_BUTTON);

  /**
   * Button for throttling turning.
   */
  private static final Button fineTurningButton =
    new JoystickButton(mainStick, Map.FINE_TURNING_BUTTON);

  /**
   * Button for extending the front lift piston.
   */
  private static final POVButton frontLiftUpButton =
    new POVButton(xbox, Map.FRONT_LIFT_UP_BUTTON);

  /**
   * Button for retracting the front lift piston.
   */
  private static final POVButton frontLiftDownButton =
    new POVButton(xbox, Map.FRONT_LIFT_DOWN_BUTTON);

  /**
   * Button for extending the rear lift piston.
   */
  private static final POVButton rearLiftUpButton =
    new POVButton(xbox, Map.REAR_LIFT_UP_BUTTON);

  /**
   * Button for retracting the rear lift piston.
   */
  private static final POVButton rearLiftDownButton =
    new POVButton(xbox, Map.REAR_LIFT_DOWN_BUTTON);

  /**
   * Method used to initialize the commands controlled by the joystick buttons.
   */
  public static void initialize() {
    reverseDirection.whenPressed(new ReverseCommand());

    fineTurningButton.whileHeld(new FineTurningCommand());

    gearShift.whenPressed(new ChangeGearCommand());

    frontLiftUpButton.whenPressed(new LiftCommand(true, true));

    frontLiftDownButton.whenPressed(new LiftCommand(false, true));

    rearLiftUpButton.whenPressed(new LiftCommand(true, false));

    rearLiftDownButton.whenPressed(new LiftCommand(false, false));

    extendHatchMech.whenPressed(new HatchMechExtenderCommand(true));

    retractHatchMech.whenPressed(new HatchMechExtenderCommand(false));

    extendHatchMechExtender.whenPressed(new HatchMechCommand(true));

    retractHatchMechExtender.whenPressed(new HatchMechCommand(false));

    incrementSpeed.whenPressed(new AdjustSpeedCommand(true));

    decrementSpeed.whenPressed(new AdjustSpeedCommand(false));

    toggleGyroscope.whenPressed(new ToggleGyroscopeCommand());

    visionAssist.whileHeld(new VisionCommand());
  }
}
