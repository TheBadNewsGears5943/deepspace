package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.helpers.Constants;
import frc.robot.subsystems.PneumaticSubsystem;

public class CompressorCommand extends Command {
  public CompressorCommand() {
    setTimeout(Constants.COMPRESSOR_BROWNOUT_TIME);
  }

  @Override
  protected void execute() {
    var compressor = PneumaticSubsystem.getInstance().compressor;
  
    compressor.setClosedLoopControl(false);
    compressor.stop();
  }

  @Override
  protected void end() {
    var compressor = PneumaticSubsystem.getInstance().compressor;

    compressor.setClosedLoopControl(true);
    compressor.start();
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }
}
