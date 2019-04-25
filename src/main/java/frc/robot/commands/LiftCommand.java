package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.PneumaticSubsystem;

/**
 * Toggles the position of the piston.
 */
public class LiftCommand extends Command {
  private boolean extendState = false;
  private boolean frontPiston = true;

  /**
   * Command for operating the lift mechanism.
   * 
   * @param extendState If true, the piston will extend, if false, the piston will retract
   * @param frontPiston If true, the front piston will be actuated, if false, the rear piston will be 
   *        actuated
   */
  public LiftCommand(boolean extendState, boolean frontPiston) {
    requires(PneumaticSubsystem.getInstance());

    this.extendState = extendState;
    this.frontPiston = frontPiston;
  }

  @Override
  protected void execute() {
    // If we're using the front piston
    if (frontPiston) {
      PneumaticSubsystem.getInstance().frontLiftMechanism.set(
        extendState 
          ? Value.kForward
          : Value.kReverse
      );
    }
    // If we're using the rear piston
    else {
      PneumaticSubsystem.getInstance().rearLiftMechanism.set(
        extendState 
          ? Value.kForward
          : Value.kReverse
      );
    }
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
