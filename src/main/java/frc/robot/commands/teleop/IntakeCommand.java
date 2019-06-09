package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OperatorInterface;
import frc.robot.helpers.Map;
import frc.robot.subsystems.IntakeSubsystem;

/**
 * Command for operating the intake in teleop.
 */
public class IntakeCommand extends Command {
  public IntakeCommand() {
    requires(IntakeSubsystem.getInstance());
  }

  @Override
  protected void execute() {
    IntakeSubsystem.getInstance().operateIntake(
        OperatorInterface.xbox.getRawAxis(Map.INTAKE_IN_AXIS) != 0
          ? OperatorInterface.xbox.getRawAxis(Map.INTAKE_IN_AXIS) * 0.9d
          : -OperatorInterface.xbox.getRawAxis(Map.INTAKE_OUT_AXIS) * 0.9d
    );
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
