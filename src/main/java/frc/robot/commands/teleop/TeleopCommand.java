package frc.robot.commands.teleop;

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
    // Calculate the speed multiplier using the power slider
    double speedMultiplier = (SmartDashboard.getNumber("Power Slider", 0.5d)
        * (DriveSubsystem.getMaxSpeed() - DriveSubsystem.getMinSpeed())) 
        + DriveSubsystem.getMinSpeed();

    // Cannot drive if the smartdashboard value is zero
    var powerSliderDrive = SmartDashboard.getNumber("Power Slider", 0.5d) != 0;

    // If the power slider isn't zero, and the joystick is being moved
    if (powerSliderDrive && ((mainStick.getX() >= 0.1d || mainStick.getX() <= -0.1d)
        || (mainStick.getY() >= 0.1d || mainStick.getY() <= -0.1d))) {

      // If the joystick is being moved horizontaly or the gyroscope is disabled
      if ((mainStick.getX() >= 0.1d || mainStick.getX() <= -0.1d)
          || SmartDashboard.getBoolean("Gyroscope Disabled", true)) {

        // Drive the robot without the gyroscope
        DriveSubsystem.getInstance().drive(
            -mainStick.getY() 
            * speedMultiplier 
            * DriveSubsystem.getInstance().getDirection(),

            mainStick.getX() 
            * speedMultiplier 
            * 1.1d
            * DriveSubsystem.getInstance().getTurningThrottle()
        );

        resetGyro = true;

      // If the joystick isn't being moved horizontally and the gyroscope is enabled
      } else {
        if (resetGyro) {
          DriveSubsystem.getInstance().resetGyro();
        }

        // Code for driving the robot with the gyroscope
        DriveSubsystem.getInstance().drive(
            -mainStick.getY() 
            * speedMultiplier 
            * DriveSubsystem.getInstance().getDirection(),

            -Math.signum(DriveSubsystem.getInstance().getAngle())
            * Math.min(Math.abs(DriveSubsystem.getInstance().getAngle()) * 0.2d, 0.3d)
        );

        resetGyro = false;
      }
    
    // If the power slider is zero, or the joystick isn't being moved
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
