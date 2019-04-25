package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.helpers.Constants;
import frc.robot.subsystems.PneumaticSubsystem;

/**
 * Toggles the position of the piston.
 */
public class LiftCommand extends Command {
  public LiftCommand() {
    requires(PneumaticSubsystem.getInstance());
  }

  @Override
  protected void execute() {
    var currentGear = PneumaticSubsystem.getInstance().liftMechanism.get();
    PneumaticSubsystem.getInstance().liftMechanism.set(
        currentGear == Constants.HIGH_GEAR 
          ? Constants.LOW_GEAR 
          : Constants.HIGH_GEAR
    );
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
