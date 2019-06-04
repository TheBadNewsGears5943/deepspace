package frc.robot.commands.teleop;

import static frc.robot.OperatorInterface.mainStick;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.helpers.Utils;
import frc.robot.subsystems.DriveSubsystem;

public class VisionCommand extends Command {
  private NetworkTableEntry tapeYawEntry;

  public VisionCommand() {
    requires(DriveSubsystem.getInstance());
  }

  @Override
  protected void initialize() {
    SmartDashboard.putString("Vision Assist", "Enabled");

    var ntInstance = NetworkTableInstance.getDefault();

    ntInstance.getEntry("/ChickenVision/Driver").setBoolean(false);

    tapeYawEntry = ntInstance.getEntry("/ChickenVision/tapeYaw");
  }

  @Override
  protected void execute() {
    var speedMultiplier = (SmartDashboard.getNumber("Power Slider", 0.5d)
        * (DriveSubsystem.getMaxSpeed() - DriveSubsystem.getMinSpeed()))
        + DriveSubsystem.getMinSpeed();

    var powerSliderDrive = SmartDashboard.getNumber("Power Slider", 0.5d) != 0;

    var tapeYaw = tapeYawEntry.getDouble(0.0d);
    var rotateSpeed = Utils.clamp(1.0d * tapeYaw, -0.35d, 0.35d);
    var forward = -mainStick.getY();

    if (mainStick.getRawButton(6) && !Utils.range(forward, -0.1d, 0.1d) && powerSliderDrive) {
      DriveSubsystem.getInstance().drive(forward * speedMultiplier, rotateSpeed);
      return;
    }

    DriveSubsystem.getInstance().drive(0, rotateSpeed * 1.1);
  }

  @Override
  protected void interrupted() {
    NetworkTableInstance.getDefault().getEntry("/ChickenVision/Driver").setBoolean(true);
    SmartDashboard.putString("Vision Assist", "Disabled");
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
