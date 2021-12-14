package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drive;
import frc.robot.commands.TurnTo;
import frc.robot.commands.DriveStraight;

/**
 * Command to make the robot do things on its own.
 * 
 * @author Levi the Super Genius
 */
public class AutonomousCommand extends SequentialCommandGroup {

    private final Drive drive;

    /**
     * @param drive The drivetrain
     */
    public AutonomousCommand(Drive drive) {
        this.drive = drive;
        addRequirements(this.drive);
  /*      addCommands( // drive robot in a square (hopefully!)
        new DriveStraight(.5, drive, .5),
       new TurnTo(90, drive),
        new DriveStraight(.5, drive, .5),
        new TurnTo(180, drive),
        new DriveStraight(.5, drive, .5),
        new TurnTo(-90, drive),
        new DriveStraight(.5, drive, .5),
        new TurnTo(0, drive));*/
    }
}