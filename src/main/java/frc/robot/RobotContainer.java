/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Map;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutonomousCommand;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.commands.BallIntake;
import frc.robot.commands.BallOutput;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ShootBalls;
import frc.robot.commands.SpinToColor;
import frc.robot.commands.TurnTo;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ColorSubsystem;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Leveling;
import frc.robot.subsystems.Power;
import frc.robot.subsystems.Compressor;

/**
 * This class takes the place of much of the old robot class, and entirely
 * replaces OI.java It initializes the joysticks, their buttons, and their axis,
 * and it binds them to commands.
 */
public class RobotContainer {
    // initializing robot subsytems
    private final Climber climb = new Climber();
    private final ColorSubsystem color = new ColorSubsystem();
    private final Drive drive = new Drive();
    private final Leveling level = new Leveling();
    private final Conveyor conveyor = new Conveyor();
    private final Compressor compressor = new Compressor();
    private final Power power = new Power();

    private final ShuffleboardTab commandTab = Shuffleboard.getTab("Commands");
    private final NetworkTableEntry targetAngle = commandTab.add("Target Angle", 1).getEntry();

    // initialize joystick and off-brand XBox conroller from Chinese Walmart
    private final Joystick driveStick = new Joystick(0);
    private final XboxController opStick = new XboxController(1);
    private final DriveCommand driveCommand = new DriveCommand(drive, () -> driveStick.getY(), () -> driveStick.getZ());

    // TODO: put in commandbase

    public RobotContainer() {
        configureButtonBindings();
        drive.setDefaultCommand(driveCommand);
        addCommandsToDashboard();
    }

    private void configureButtonBindings() {
        // xbox buttons
        final JoystickButton green = new JoystickButton(opStick, 1);
        final JoystickButton red = new JoystickButton(opStick, 2);
        final JoystickButton blue = new JoystickButton(opStick, 3);
        final JoystickButton yellow = new JoystickButton(opStick, 4);
        // joystick buttons
        final JoystickButton intake = new JoystickButton(driveStick, 2);
        final JoystickButton output = new JoystickButton(driveStick, 1);

        // TODO: Add actions for color wheel
                output.whileHeld(new ShootBalls(conveyor, .25));
    }
    
    public Command getAutonomousCommand(){
        return new AutonomousCommand(drive);
    }


    private void addCommandsToDashboard() {

        ShuffleboardLayout BallIntakeCommand = commandTab.getLayout("BallIntake", BuiltInLayouts.kList).withSize(2, 2)
                .withPosition(0, 0).withProperties(Map.of("Label position", "HIDDEN")); // hide labels for commands

        BallIntakeCommand.add(new BallIntake(conveyor));

        ShuffleboardLayout BallOutputCommand = commandTab.getLayout("BallOutput", BuiltInLayouts.kList).withSize(2, 2)
                .withPosition(0, 0).withProperties(Map.of("Label position", "HIDDEN")); // hide labels for commands

        BallOutputCommand.add(new BallOutput(conveyor));

        ShuffleboardLayout ShootBallsCommand = commandTab.getLayout("ShootBalls", BuiltInLayouts.kList).withSize(2, 2)
                .withPosition(0, 0).withProperties(Map.of("Label position", "HIDDEN")); // hide labels for commands

        ShootBallsCommand.add(new ShootBalls(conveyor, 0.5));

        ShuffleboardLayout colorWheelCommand = commandTab.getLayout("ColorWheel", BuiltInLayouts.kList).withSize(2, 2)
                .withPosition(0, 0).withProperties(Map.of("Label position", "HIDDEN")); // hide labels for commands

        colorWheelCommand.add(new SpinToColor(color, Color.kRed));
        colorWheelCommand.add(new SpinToColor(color, Color.kBlue));
        colorWheelCommand.add(new SpinToColor(color, Color.kGreen));
        colorWheelCommand.add(new SpinToColor(color, Color.kYellow));

        // turn to button
        
        ShuffleboardLayout turnToCommand = commandTab.getLayout("Target Angle", BuiltInLayouts.kList).withSize(2, 2)
                .withPosition(0, 0).withProperties(Map.of("Label position", "HIDDEN")); // hide labels for commands

        turnToCommand.add(new TurnTo(targetAngle.getDouble(1.0), drive));

    }
}
