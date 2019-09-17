package frc.robot.commands.teleop;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Switches the direction the robot is driving in, and the swaps the current camera.
 */
public class ReverseCommand extends Command {
  @Override
  public synchronized void start() {
    var nt = NetworkTableInstance.getDefault().getEntry("/SelectedCamera");

    nt.setString(nt.getString("Back").equals("Front") ? "Back" : "Front");
    DriveSubsystem.getInstance().setDirection(nt.getString("Front").equals("Front"));
    SmartDashboard.putString("Driving Direction",
        nt.getString("Front").equals("Front")
          ? "Forward" 
          : "Backward"
    );
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
