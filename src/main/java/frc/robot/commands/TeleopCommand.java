package frc.robot.commands;

import static frc.robot.OperatorInterface.mainStick;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveSubsystem;

public class TeleopCommand extends Command {
  private boolean resetGyro = true;

  public TeleopCommand() {
    requires(DriveSubsystem.getInstance());
  }

  @Override
  protected void initialize() {
    DriveSubsystem.getInstance().resetGyro();
  }

  @Override
  protected void execute() {
    // double speedMultiplier = (((-mainStick.getThrottle() + 1.0d) / 2.0d)
    double speedMultiplier = (SmartDashboard.getNumber("Power Slider", 0.5d)
        * (DriveSubsystem.getMaxSpeed() - DriveSubsystem.getMinSpeed()))
        + DriveSubsystem.getMinSpeed();

    // Cannot drive if the smartdashboard value is 0.0
    var powerSliderDrive = SmartDashboard.getNumber("Power Slider", 0.5d) != 0;
    // System.out.println(powerSliderDrive);
    if (powerSliderDrive && ((mainStick.getX() >= 0.1d || mainStick.getX() <= -0.1d)
        || (mainStick.getY() >= 0.1d || mainStick.getY() <= -0.1d))) {
      if ((mainStick.getX() >= 0.1d || mainStick.getX() <= -0.1d)
          || SmartDashboard.getBoolean("Gyroscope Disabled", true)) {
        DriveSubsystem.getInstance().drive(
            -mainStick.getY() * speedMultiplier * DriveSubsystem.getInstance().getDirection(),
            mainStick.getX() * speedMultiplier * 1.1d);

        resetGyro = true;
      } else {
        if (resetGyro) {
          DriveSubsystem.getInstance().resetGyro();
        }

        DriveSubsystem.getInstance().drive(
            -mainStick.getY() * speedMultiplier * DriveSubsystem.getInstance().getDirection(),
            -Math.signum(DriveSubsystem.getInstance().getAngle())
                * Math.min(Math.abs(DriveSubsystem.getInstance().getAngle()) * 0.2d, 0.3d));

        resetGyro = false;
      }
    } else {
      DriveSubsystem.getInstance().drive(0.0d, 0.0d);
      resetGyro = true;
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
