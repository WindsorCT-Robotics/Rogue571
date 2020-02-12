package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

/** 
 * A command to make the robot drive straight
 * @author Levi the Super Genius
 */
public class DriveStraight extends CommandBase {

    private final Drive drive;
    private final double speed;

    public DriveStraight(double time, Drive drive, double speed) {
        this.speed = speed;
        this.drive = drive;
        addRequirements(drive);
        withTimeout(time);
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        drive.tankDrive(speed, speed);// sets both sides to the same speed

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

}
