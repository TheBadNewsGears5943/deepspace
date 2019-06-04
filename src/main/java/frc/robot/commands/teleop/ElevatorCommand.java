package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OperatorInterface;
import frc.robot.helpers.Map;
import frc.robot.helpers.Utils;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorCommand extends Command {
  public ElevatorCommand() {
    requires(ElevatorSubsystem.getInstance());
  }

  @Override
  protected void execute() {
    ElevatorSubsystem.getInstance().operateElevator(
        !Utils.range(OperatorInterface.xbox.getRawAxis(Map.ELEVATOR_FAST_AXIS), -0.1d, 0.1d)
          ? OperatorInterface.xbox.getRawAxis(Map.ELEVATOR_FAST_AXIS) * 1.0d
          : OperatorInterface.xbox.getRawAxis(Map.ELEVATOR_SLOW_AXIS) * 0.2d
    );
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
