/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ColorSubsystem;
import frc.robot.subsystems.Compressor;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Leveling;
import frc.robot.subsystems.Power;

/**
 * This class takes the place of much of the old robot class, and entirely
 * replaces OI.java It initializes the joysticks, their buttons, and their axis,
 * and it binds them to commands.
 */
public class RobotContainer {
    // initializing robot subsytems
    private Climber climb;
    private ColorSubsystem color;
    private Drive drive;
    private Leveling level;
    private Conveyor conveyor;
    private Compressor compressor;
    private Power power;

    private boolean climbEnabled = false;
    private boolean colorEnabled = false;
    private boolean driveEnabled = true;
    private boolean levelEnabled = false;
    private boolean conveyorEnabled = false;
    private boolean compressorEnabled = true;
    private boolean powerEnabled = false;

    // initialize joystick and off-brand XBox conroller from Chinese Walmart
    private final Joystick driveStick = new Joystick(0);
    private final XboxController opStick = new XboxController(1);
    private DriveCommand driveCommand;

    // TODO: put in commandbase

    public RobotContainer() {
        createClimbSubsystem();
        createColorSubsystem();
        createCompressorSubsystem();
        createConveyorSubsystem();
        configureButtonBindings();
        createDriveSubsystem();
        drive.setDefaultCommand(driveCommand);
        createLevelSubsystem();
        createPowerSubsystem();

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

        

        // TODO: put in color parameters
        // green.whenPressed(new SpinToColor((byte)1));
        // red.whenPressed(new SpinToColor((byte)2));
        // blue.whenPressed(new SpinToColor((byte)3));
        // yellow.whenPressed(new SpinToColor((byte)4));
        // output.whileHeld(new ShootBalls(conveyor, .25));
        // intake.whenPressed(); output.whenPressed();
    }

    public Command getAutonomousCommand() {
        return new AutonomousCommand(drive);
    }

    public void createClimbSubsystem() {
        if (climbEnabled) {
            climb = new Climber();
        }
    }

    public void createColorSubsystem() {
        if (colorEnabled) {
            color = new ColorSubsystem();
        }
    }

    public void createCompressorSubsystem() {
        if (compressorEnabled) {
            compressor = new Compressor();
        }
    }

    public void createConveyorSubsystem() {
        if (conveyorEnabled) {
            conveyor = new Conveyor();
        }
    }

    public void createDriveSubsystem() {
        if (driveEnabled) {
            drive = new Drive();
            driveCommand = new DriveCommand(drive, () -> driveStick.getY(), () -> driveStick.getZ());
        }
    }

    public void createLevelSubsystem() {
        if (levelEnabled) {
            level = new Leveling();
        }
    }

    public void createPowerSubsystem() {
        if (powerEnabled) {
            power = new Power();
        }
    }

}
