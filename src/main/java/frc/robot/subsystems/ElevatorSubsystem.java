package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ElevatorCommand;
import frc.robot.helpers.Map;

public class ElevatorSubsystem extends Subsystem {
  private static ElevatorSubsystem instance = new ElevatorSubsystem();

  private final WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(Map.ELEVATOR_MOTOR_CAN);
  // private final WPI_VictorSPX elevatorMotor = new WPI_VictorSPX(Map.ELEVATOR_MOTOR_CAN);

  /**
   * Operates the elevator.
   * 
   * @param speed Speed the elevator moves at.
   */
  public void operateElevator(double speed) {
    elevatorMotor.set(speed);
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new ElevatorCommand());
  }

  /**
   * Return an instance of this class.
   *
   * @return ElevatorSubsystem instance
   */
  public static ElevatorSubsystem getInstance() {
    return instance;
  }
}
