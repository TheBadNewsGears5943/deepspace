package frc.robot.commands;

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
    var currentGear = PneumaticSubsystem.getInstance().leftGearBox.get();

    if (currentGear == Constants.HIGH_GEAR) {
      PneumaticSubsystem.getInstance().leftGearBox.set(Constants.LOW_GEAR);
      PneumaticSubsystem.getInstance().rightGearBox.set(Constants.LOW_GEAR);
    } else {
      PneumaticSubsystem.getInstance().leftGearBox.set(Constants.HIGH_GEAR);
      PneumaticSubsystem.getInstance().rightGearBox.set(Constants.HIGH_GEAR);
    }

    SmartDashboard.putString("Current Gear", (currentGear == Constants.HIGH_GEAR) ? "High" : "Low");
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
