/**---------------------------------------------------------------------------
 Copyright (c) 2019 FIRST. All Rights Reserved.
 Open Source Software - may be modified and shared by FRC teams. The code
 must be accompanied by the FIRST BSD license file in the root directory of
 the project.
----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drive;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TurnTo extends PIDCommand {
  private final ShuffleboardTab tab = Shuffleboard.getTab("Commands");
  private final ShuffleboardLayout pidLayout = tab.getLayout("pid", BuiltInLayouts.kList);

  /**
   * Creates a new TurnTo.
   * 
   * @param heading heading on a scale from -180 to 180
   * @param drive   drive subsystem that must be passed in
   */
  public TurnTo(double heading, Drive drive) {
    super(
        // The controller that the command will use
        new PIDController(0.5, 0.0, 0.0),
        // This should return the measurement
        drive.navx::getYaw,
        // This should return the setpoint (can also be a constant)
        heading,
        // This uses the output
        output -> drive.tankDrive(output, -output));
    addRequirements(drive);
    getController().setTolerance(5);
    LiveWindow.enableTelemetry(this.getController());
  }

  @Override
  public void initialize() {
    super.initialize();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }

  public double getP() {
    NetworkTableEntry kP = pidLayout.add("kP", 0.5).getEntry();
    return kP.getDouble(0);
  }

  public double getI() {
    NetworkTableEntry kI = pidLayout.add("kI", 0.5).getEntry();
    return kI.getDouble(0);
  }

  public double getD() {
    NetworkTableEntry kD = pidLayout.add("kD", 0.5).getEntry();
    return kD.getDouble(0);
  }
}
