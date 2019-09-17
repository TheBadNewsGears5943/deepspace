package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.helpers.Constants;
import frc.robot.helpers.Map;

public class PneumaticSubsystem extends Subsystem {
  private static PneumaticSubsystem instance = new PneumaticSubsystem();

  public final Compressor compressor = new Compressor(Map.PCM_CAN_PRIMARY_INDEX);

  public final DoubleSolenoid leftGearBox =
      new DoubleSolenoid(Map.LEFT_GEARBOX_EXTENDED, Map.LEFT_GEARBOX_REVERSED);
  public final DoubleSolenoid rightGearBox =
      new DoubleSolenoid(Map.RIGHT_GEARBOX_EXTENDED, Map.RIGHT_GEARBOX_REVERSED);

  public final DoubleSolenoid hatchMechExtender =
      new DoubleSolenoid(Map.PCM_CAN_SECONDARY_INDEX,
          Map.HATCH_MECH_FORWARD,
          Map.HATCH_MECH_REVERSE);

  public final DoubleSolenoid hatchMech =
      new DoubleSolenoid(Map.PCM_CAN_SECONDARY_INDEX,
          Map.HATCH_MECH_EXTENDER_FORWARD,
          Map.HATCH_MECH_EXTENDER_REVERSE);

  public final DoubleSolenoid frontLiftMechanism =
      new DoubleSolenoid(Map.FRONT_LIFT_EXTENDED, Map.FRONT_LIFT_REVERSED);
  public final DoubleSolenoid rearLiftMechanism =
      new DoubleSolenoid(Map.REAR_LIFT_EXTENDED, Map.REAR_LIFT_REVERSED);

  public static PneumaticSubsystem getInstance() {
    return instance;
  }

  @Override
  protected void initDefaultCommand() {
  }

  public boolean inHighGear() {
    return leftGearBox.get() == Constants.HIGH_GEAR;
  }

  public void setGearBox(Value gear) {
    leftGearBox.set(gear);
    rightGearBox.set(gear);
  }
}
