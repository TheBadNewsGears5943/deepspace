package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ToggleGyroscopeCommand extends Command {
  @Override
  protected void execute() {
    SmartDashboard.putBoolean("Gyroscope Disabled",
        !SmartDashboard.getBoolean("Gyroscope Disabled", true));
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
