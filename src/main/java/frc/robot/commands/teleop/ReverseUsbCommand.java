package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.DriveSubsystem;

public class ReverseUsbCommand extends Command {
  private boolean direction = true;

  @Override
  public synchronized void start() {
    direction = !direction;
    DriveSubsystem.getInstance().setDirection(direction);
    CameraSubsystem.getInstance().swap();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
