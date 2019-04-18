package frc.robot.commands;

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
    DriveSubsystem.getInstance().setTurningThrottle(1.0d);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}