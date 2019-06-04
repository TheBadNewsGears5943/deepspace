package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.teleop.IntakeCommand;
import frc.robot.helpers.Map;

public class IntakeSubsystem extends Subsystem {
  private static IntakeSubsystem instance = new IntakeSubsystem();

  private final WPI_TalonSRX intakeMotor = new WPI_TalonSRX(Map.INTAKE_MOTOR_CAN);

  public void operateIntake(double speed) {
    intakeMotor.set(speed);
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new IntakeCommand());
  }

  /**
   * Return an instance of this class.
   *
   * @return IntakeSubsystem instance
   */
  public static IntakeSubsystem getInstance() {
    return instance;
  }
}
