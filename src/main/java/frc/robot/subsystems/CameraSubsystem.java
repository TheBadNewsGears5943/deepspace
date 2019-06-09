package frc.robot.subsystems;

import edu.wpi.cscore.CvSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraSubsystem extends Subsystem {
  private static CameraSubsystem instance = new CameraSubsystem();
  private CvSink frontSink;
  private CvSink backSink;

  // Reference
  private CvSink selectedCamera;

  private CameraServer cameraServer = CameraServer.getInstance();

  public void start() {
    var front = cameraServer.startAutomaticCapture(0);
    front.setResolution(320, 240);
    var back = cameraServer.startAutomaticCapture(1);
    back.setResolution(320, 240);

    frontSink = new CvSink("Front");
    backSink = new CvSink("Back");

    frontSink.setSource(front);
    backSink.setSource(back);

    selectedCamera = frontSink;
    cameraServer.getServer().setSource(selectedCamera.getSource());
    cameraServer.putVideo("Camera", 320, 240);
  }

  public void swap() {
    if (selectedCamera == frontSink) {
      // Set back sink
      selectedCamera = backSink;
      cameraServer.getServer().setSource(backSink.getSource());
    } else {
      // Set front sink
      selectedCamera = frontSink;
      cameraServer.getServer().setSource(frontSink.getSource());
    }
  }

  public static CameraSubsystem getInstance() {
    return instance;
  }

  @Override
  protected void initDefaultCommand() {

  }
}