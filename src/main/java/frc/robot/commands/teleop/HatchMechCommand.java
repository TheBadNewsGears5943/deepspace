package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.PneumaticSubsystem;

/**
 * Controls the hatch panel mechanism to grab panels
 */
public class HatchMechCommand extends Command {
  private boolean extend;

  public HatchMechCommand(boolean extend) {
    requires(PneumaticSubsystem.getInstance());
    this.extend = extend;
  }

  @Override
  protected void execute() {
    var ps = PneumaticSubsystem.getInstance();

    if (extend) {
      ps.hatchMech.set(DoubleSolenoid.Value.kForward);
    } else {
      ps.hatchMech.set(DoubleSolenoid.Value.kReverse);
    }
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
