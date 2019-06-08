package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.helpers.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class FineTurningCommand extends Command {
  @Override
  protected void execute() {
    DriveSubsystem.getInstance().setTurningThrottle(Constants.TURNING_THROTTLE);
  }

  @Override
  protected void interrupted() {
    DriveSubsystem.getInstance().setTurningThrottle(Constants.TURNING_FULL);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}