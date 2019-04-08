package frc.robot.helpers;

import edu.wpi.first.wpilibj.DriverStation;

public class Utils {
  /**
   * Represents a object on the field.
   */
  public enum FieldObject {
    TeamSwitch(1), Scale(2), EnemySwitch(3);

    private int location;

    FieldObject(int location) {
      this.location = location;
    }

    private int getLocation() {
      return location;
    }
  }

  /**
   * Side of field.
   */
  public enum FieldSide {
    Left, Right, Center, Error;
  }

  /**
   * Side of the robot.
   */
  public enum RobotSide {
    Front, Back, Left, Right;
  }

  /**
   * Constants to be mapped to values.
   */
  public enum DashboardConstant {
    MaxRobotSpeed, MinRobotSpeed;
  }

  /**
   * Converts seconds into loop iterations, for the robot's periodic loops which
   * run every 20ms.
   *
   * @param seconds Amount of seconds
   * @return Amount of iterations it would take for the amount of seconds to pass
   */
  public static int autoTime(double seconds) {
    return (int) ((seconds * 1000.0d) / 20.0d);
  }

  /**
   * Return if a value is between a range or not.
   *
   * @param value Value to check
   * @param min   Minimum of range
   * @param max   Maximum of range
   * @return {@code true} if the value is between (inclusive) the range, otherwise
   *         {@code false}
   */
  public static boolean range(double value, double min, double max) {
    return value <= max && value >= min;
  }

  public static double clamp(double val, double min, double max) {
    return Math.max(min, Math.min(max, val));
  }

  public static int clamp(int val, int min, int max) {
    return Math.max(min, Math.min(max, val));
  }

  /**
   * Get a {@link FieldSide} object depending on the {@link FieldObject} passed
   * in.
   *
   * @param obj A FieldObject
   * @return A {@link FieldSide} corresponding where the {@link FieldObject} is on
   *         the field
   */
  public static FieldSide getFieldObject(FieldObject obj) {
    try {
      switch (DriverStation.getInstance().getGameSpecificMessage().charAt(obj.getLocation())) {
        case 'R':
          return FieldSide.Right;
        case 'L':
          return FieldSide.Left;
        default:
          return FieldSide.Error;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return FieldSide.Error;
  }
}
