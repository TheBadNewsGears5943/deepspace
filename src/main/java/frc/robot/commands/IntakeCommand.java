package frc.robot.commands;

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
          ? -OperatorInterface.xbox.getRawAxis(Map.INTAKE_IN_AXIS) * 0.5d
          : OperatorInterface.xbox.getRawAxis(Map.INTAKE_OUT_AXIS) * 0.5d
    );
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
