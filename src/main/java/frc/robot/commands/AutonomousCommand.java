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
    private Climber climber;

    /**
     * @param drive The drivetrain
     */
    public AutonomousCommand(Drive drive, Climber climber) {
        this.drive = drive;
        this.climber = climber;
        addRequirements(this.drive, this.climber);
        // addCommands( // drive robot in a square (hopefully!)
        // new DriveStraight(3, drive, 2),
        // new TurnTo(90, drive),
        // new DriveStraight(3, drive, 2),
        // new TurnTo(90, drive),
        // new DriveStraight(3, drive, 2),
        // new TurnTo(90, drive),
        // new DriveStraight(3, drive, 2),
        // new TurnTo(90, drive));
    }

    @Override
    public void initialize() {
        addCommands(new DriveStraight(.5, drive, .5),
        new TurnTo(90, drive),
        new InstantCommand(climber::releaseLatch, climber));
        // addCommands();
        super.initialize();
    }

}
