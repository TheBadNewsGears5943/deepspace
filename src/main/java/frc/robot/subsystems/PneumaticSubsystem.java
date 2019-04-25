package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.helpers.Map;

public class PneumaticSubsystem extends Subsystem {
  private static PneumaticSubsystem instance = new PneumaticSubsystem();

  public final Compressor compressor = new Compressor(Map.PCM_CAN_INDEX);

  public final DoubleSolenoid leftGearBox =
      new DoubleSolenoid(Map.LEFT_GEARBOX_EXTENDED, Map.LEFT_GEARBOX_REVERSED);
  public final DoubleSolenoid rightGearBox =
      new DoubleSolenoid(Map.RIGHT_GEARBOX_EXTENDED, Map.RIGHT_GEARBOX_REVERSED);

  // public final DoubleSolenoid panelClutch = new DoubleSolenoid(4, 5);

  public final DoubleSolenoid frontLiftMechanism = new DoubleSolenoid(6, 7);
  public final DoubleSolenoid rearLiftMechanism = new DoubleSolenoid(4, 5);

  @Override
  protected void initDefaultCommand() {

  }

  public static PneumaticSubsystem getInstance() {
    return instance;
  }
}
