/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.DriveCommand;
import frc.robot.commands.ShootBalls;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Color;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Leveling;
import frc.robot.subsystems.Compressor;

/**
 * This class takes the place of much of the old robot class, and entirely
 * replaces OI.java It initializes the joysticks, their buttons, and their axis,
 * and it binds them to commands.
 */
public class RobotContainer {
    // initializing robot subsytems
    private final Climber climb = new Climber();
    private final Color color = new Color();
    private final Drive drive = new Drive();
    private final Leveling level = new Leveling();
    private final Conveyor conveyor = new Conveyor();
    private final Compressor compressor = new Compressor();

    // initialize joystick and off-brand XBox conroller from Chinese Walmart
    private final Joystick driveStick = new Joystick(0);
    private final XboxController opStick = new XboxController(1);
    private final DriveCommand driveCommand = new DriveCommand(drive, () -> driveStick.getY(), () -> driveStick.getZ());
    
    // TODO: put in commandbase

    public RobotContainer() {
        configureButtonBindings();
        drive.setDefaultCommand(driveCommand);
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
        output.whileHeld(new ShootBalls(conveyor, .25));
        // intake.whenPressed(); output.whenPressed();
    }

}
