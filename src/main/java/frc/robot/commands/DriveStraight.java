package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drive;

/** 
 * A command to make the robot drive straight
 * @author Levi the Super Genius
 */
public class DriveStraight extends WaitCommand {

    private final Drive drive;
    private final double speed;

    public DriveStraight(double time, Drive drive, double speed) {
        super(time);
        this.speed = speed;
        this.drive = drive;
        addRequirements(drive);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        drive.tankDrive(speed, speed);// sets both sides to the same speed
    }

    public void end() {
        drive.stop();
    }

}
