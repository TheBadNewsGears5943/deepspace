package frc.robot.commands.misc;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.helpers.Utils;

public class AdjustSpeedCommand extends Command {
  private static int speed = 0;

  private boolean increment;

  /**
   * Increments or decrements speed by 10%.
   * 
   * @param increment Increases if true, decreases if false
   */
  public AdjustSpeedCommand(boolean increment) {
    this.increment = increment;
  }

  @Override
  protected void execute() {
    speed += (increment) ? 1 : -1;

    speed = Utils.clamp(speed, 0, 10);

    SmartDashboard.putNumber("Power Slider", speed / 10.0d);
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
