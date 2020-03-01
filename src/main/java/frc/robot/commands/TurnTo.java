/**---------------------------------------------------------------------------
 Copyright (c) 2019 FIRST. All Rights Reserved.
 Open Source Software - may be modified and shared by FRC teams. The code
 must be accompanied by the FIRST BSD license file in the root directory of
 the project.
----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drive;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TurnTo extends PIDCommand {

  /**
   * Creates a new TurnTo.
   * @param heading  heading on a scale from -180 to 180
   * @param drive    drive subsystem that must be passed in
   */
  public TurnTo(double heading, Drive drive) {
    super(
        // The controller that the command will use
        new PIDController(.5, 0.0, 0.0),
        // This should return the measurement
        drive.navx::getYaw,
        // This should return the setpoint (can also be a constant)
        heading,
        // This uses the output
        output -> drive.tankDrive(output, -output));
    addRequirements(drive);
    getController().setTolerance(5);
    
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
}
