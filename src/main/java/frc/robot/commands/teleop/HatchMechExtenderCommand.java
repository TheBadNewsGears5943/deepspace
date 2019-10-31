package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.PneumaticSubsystem;

/**
 * Controls the solenoid that extends the actual hatch panel mechanisms.
 */
public class HatchMechExtenderCommand extends Command {
  private boolean extend;

  public HatchMechExtenderCommand(boolean extend) {
    requires(PneumaticSubsystem.getInstance());
    this.extend = extend;
  }

  @Override
  protected void execute() {
    var ps = PneumaticSubsystem.getInstance();

    if (extend) {
      ps.hatchMechExtender.set(DoubleSolenoid.Value.kForward);
    } else {
      ps.hatchMechExtender.set(DoubleSolenoid.Value.kReverse);
    }
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
