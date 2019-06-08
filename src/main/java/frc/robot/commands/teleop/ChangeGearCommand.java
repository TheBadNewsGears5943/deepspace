package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.helpers.Constants;
import frc.robot.subsystems.PneumaticSubsystem;

/**
 * Change gears in the gearbox to be either low or high gear.
 */
public class ChangeGearCommand extends Command {
  public ChangeGearCommand() {
    requires(PneumaticSubsystem.getInstance());
  }

  @Override
  protected void execute() {
    var pneumatics = PneumaticSubsystem.getInstance();
    var currentGear = pneumatics.leftGearBox.get();

    if (currentGear == Constants.HIGH_GEAR) {
      pneumatics.setGearBox(Constants.LOW_GEAR);
    } else {
      pneumatics.setGearBox(Constants.HIGH_GEAR);
    }

    SmartDashboard.putString("Current Gear", pneumatics.inHighGear() ? "High" : "Low");
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
