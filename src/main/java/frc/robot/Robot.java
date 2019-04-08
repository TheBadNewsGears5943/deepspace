package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.TeleopCommand;
import frc.robot.helpers.Constants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;

public class Robot extends TimedRobot {
  TeleopCommand teleop = new TeleopCommand();
  IntakeCommand intake = new IntakeCommand();
  ElevatorCommand elevator = new ElevatorCommand();

  /**
   * Code for when the robot is first being powered on goes here.
   */
  @Override
  public void robotInit() {
    OperatorInterface.initialize();

    SmartDashboard.putBoolean("Gyroscope Disabled", true);
    SmartDashboard.putNumber("Power Slider", 1.0d);


    PneumaticSubsystem.getInstance().compressor.setClosedLoopControl(true);
    PneumaticSubsystem.getInstance().compressor.start();

    PneumaticSubsystem.getInstance().leftGearBox.set(Constants.HIGH_GEAR);
    PneumaticSubsystem.getInstance().rightGearBox.set(Constants.HIGH_GEAR);
    SmartDashboard.putString("Current Gear",
        (PneumaticSubsystem.getInstance().leftGearBox.get() == Constants.HIGH_GEAR) ? "High"
            : "Low");
  }

  /**
   * Code for when the robot is being enabled in sandstorm goes here.
   */
  @Override
  public void autonomousInit() {
    NetworkTableInstance.getDefault().getEntry("/ChickenVision/Driver").setBoolean(true);
    SmartDashboard.putString("Driving Direction", "Forward");
    DriveSubsystem.getInstance().setDirection(true);
    System.out.println();

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
