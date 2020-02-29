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
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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
import frc.robot.subsystems.Compressor;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Leveling;
import frc.robot.subsystems.Power;
import frc.robot.subsystems.Compressor;
import frc.robot.commands.ReleaseLatch;
import frc.robot.commands.RobotLift;

/**
 * This class takes the place of much of the old robot class, and entirely
 * replaces OI.java It initializes the joysticks, their buttons, and their axis,
 * and it binds them to commands.
 */
public class RobotContainer {
    // initializing robot subsytems
    private Compressor compressor;
    private Climber climb;
    private ColorSubsystem color;
    private Drive drive;
    private Leveling level;
    private Conveyor conveyor;
    private Power power;

    private boolean climbEnabled = false;
    private boolean colorEnabled = false;
    private boolean driveEnabled = true;
    private boolean levelEnabled = false;
    private boolean conveyorEnabled = false;
    private boolean compressorEnabled = true;
    private boolean powerEnabled = false;

    private final ShuffleboardTab commandTab = Shuffleboard.getTab("Commands");
    private final NetworkTableEntry targetAngle = commandTab.add("Target Angle", 1).getEntry();

    // initialize joystick and off-brand XBox conroller from Chinese Walmart
    private final Joystick driveStick = new Joystick(0);
    private final XboxController opStick = new XboxController(1);
  
    private DriveCommand driveCommand = new DriveCommand(drive, () -> driveStick.getY(), () -> driveStick.getZ());
    private final RobotLift robotLift = new RobotLift(climb, () -> opStick.getY(Hand.kRight));
    
    // TODO: put in commandbase

    public RobotContainer() {
        createClimbSubsystem();
        createColorSubsystem();
        createCompressorSubsystem();
        createConveyorSubsystem();
        configureButtonBindings();
        createDriveSubsystem();
        createLevelSubsystem();
        createPowerSubsystem();
        addCommandsToDashboard();

        LiveWindow.disableAllTelemetry();
    }

    private void configureButtonBindings() {
        // joystick buttons
        final JoystickButton intake = new JoystickButton(driveStick, 2);
        final JoystickButton output = new JoystickButton(driveStick, 1);
        intake.whenPressed(new BallIntake(conveyor));
        output.whileHeld(new ShootBalls(conveyor, .25));
      
        SmartDashboard.putData("releaseLatch", new ReleaseLatch(climb));

        final JoystickButton releaseLatch = new JoystickButton(opStick, 6);
        releaseLatch.whenPressed(new ReleaseLatch(climb));
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
            drive.setDefaultCommand(driveCommand);
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

        // turn to button
        
        ShuffleboardLayout turnToCommand = commandTab.getLayout("Target Angle", BuiltInLayouts.kList).withSize(2, 2)
                .withPosition(0, 0).withProperties(Map.of("Label position", "HIDDEN")); // hide labels for commands

        turnToCommand.add(new TurnTo(targetAngle.getDouble(1.0), drive));

    }
}
