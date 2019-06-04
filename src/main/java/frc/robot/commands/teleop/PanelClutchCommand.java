package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.PneumaticSubsystem;

public class PanelClutchCommand extends Command {
  Direction direction = Direction.Close;

  public enum Direction {
    Open, Close;
  }

  /**
   * Command for operating the panel clutch mechanism.
   * 
   * @param direction Direction the mechanism will be operated.
   */
  public PanelClutchCommand(Direction direction) {
    requires(PneumaticSubsystem.getInstance());

    this.direction = direction;
  }

  @Override
  protected void execute() {
    switch (direction) {
      case Open:
        PneumaticSubsystem.getInstance().panelClutch.set(Value.kForward);
        break;
      case Close:
        PneumaticSubsystem.getInstance().panelClutch.set(Value.kReverse);
        break;
      default:
        break;
    }
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
