package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.misc.CompressorCommand;
import frc.robot.commands.teleop.ElevatorCommand;
import frc.robot.commands.teleop.IntakeCommand;
import frc.robot.commands.teleop.TeleopCommand;
import frc.robot.helpers.Constants;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;

public class Robot extends TimedRobot {
  private TeleopCommand teleop = new TeleopCommand();
  private IntakeCommand intake = new IntakeCommand();
  private ElevatorCommand elevator = new ElevatorCommand();
  private CompressorCommand compressorCommand = new CompressorCommand();

  /**
   * Code for when the robot is first being powered on goes here.
   */
  @Override
  public void robotInit() {
    CameraSubsystem.getInstance().start();

    OperatorInterface.initialize();

    SmartDashboard.putBoolean("Gyroscope Disabled", true);
    SmartDashboard.putNumber("Power Slider", 1.0d);

    var pneumatics = PneumaticSubsystem.getInstance();
    pneumatics.compressor.setClosedLoopControl(true);
    pneumatics.compressor.start();

    pneumatics.leftGearBox.set(Constants.HIGH_GEAR);
    pneumatics.rightGearBox.set(Constants.HIGH_GEAR);

    pneumatics.frontLiftMechanism.set(Value.kReverse);
    pneumatics.rearLiftMechanism.set(Value.kReverse);

    // FIXME: Tell build team to fix hatch panel mechanism to not be reversed and then revert
    pneumatics.panelClutch.set(Value.kForward);

    SmartDashboard.putString("Current Gear",
        (pneumatics.inHighGear())
            ? "High"
            : "Low"
    );

    SmartDashboard.putBoolean(Constants.AUTO_COMPRESSOR_BROWNOUT_KEY, false);
  }

  /**
   * Code for when the robot is being enabled in sandstorm goes here.
   */
  @Override
  public void autonomousInit() {
    NetworkTableInstance.getDefault().getEntry("/ChickenVision/Driver").setBoolean(true);
    SmartDashboard.putString("Driving Direction", "Forward");
    DriveSubsystem.getInstance().setDirection(true);

    if (teleop != null) {
      teleop.start();
    }
    if (intake != null) {
      intake.start();
    }
    if (elevator != null) {
      elevator.start();
    }
  }

  /**
   * Code for when the robot is being enabled in sandstorm goes here.
   */
  @Override
  public void teleopInit() {
    if (teleop != null && !teleop.isRunning()) {
      teleop.start();
    }
    if (intake != null && !intake.isRunning()) {
      intake.start();
    }
    if (elevator != null && !elevator.isRunning()) {
      elevator.start();
    }
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();

    /*
     * Get if the SmartDashboard checkbox for turning off the compressor during a brownout is
     * pressed.
     */
    var compressorBrownoutPoweroffConfigured =
        SmartDashboard.getBoolean(Constants.AUTO_COMPRESSOR_BROWNOUT_KEY, false);

    // If the roboRIO is browning out, stop the compressor for six seconds
    if (
        compressorBrownoutPoweroffConfigured
            && RobotController.isBrownedOut()
            && compressorCommand != null
            && !compressorCommand.isRunning()
    ) {
      compressorCommand.start();
    }
  }

  /**
   * Code for when the robot is being disabled should go here.
   */
  @Override
  public void disabledInit() {
    if (teleop != null) {
      teleop.cancel();
    }
    if (intake != null) {
      intake.cancel();
    }
    if (elevator != null) {
      elevator.cancel();
    }
  }
}
