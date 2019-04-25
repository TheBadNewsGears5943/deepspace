package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.PneumaticSubsystem;

/**
 * Toggles the position of the piston.
 */
public class LiftCommand extends Command {
  private boolean extendState = false;

  /**
   * Command for operating the lift mechanism.
   * 
   * @param extendState If true, the piston will extend, if false, the piston will retract
   */
  public LiftCommand(boolean extendState) {
    requires(PneumaticSubsystem.getInstance());

    this.extendState = extendState;
  }

  @Override
  protected void execute() {
    PneumaticSubsystem.getInstance().liftMechanism.set(
        extendState 
          ? Value.kForward
          : Value.kReverse
    );
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
