package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drive;
import frc.robot.commands.TurnTo;
import frc.robot.commands.DriveStraight;

/**
 * Command to make the robot do things on its own.
 * @author Levi the Super Genius
 */
public class AutonomousCommand extends SequentialCommandGroup {
    
    /**
     * @param drive        The drivetrain
     */
    public AutonomousCommand(Drive drive) {
        addCommands( // drive robot in a square (hopefully!)
                new DriveStraight(3, drive, 2), // TODO: make this command do something useful!
                new TurnTo(90, drive), new DriveStraight(3, drive, 2), new TurnTo(90, drive),
                new DriveStraight(3, drive, 2), new TurnTo(90, drive), new DriveStraight(3, drive, 2),
                new TurnTo(90, drive));
    }

}
