
package frc.robot.subsystems;

import java.util.Map;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * @author GABRIEL THE SUPER MEGA ULTRA AMAZING TERRIFIC GENIUS
 */
public class Climber extends SubsystemBase {

    private final WPI_TalonSRX winch;

    public Climber() {

        winch = new WPI_TalonSRX(5);
        addChild("Winch", winch);

        final ShuffleboardLayout layout = Shuffleboard.getTab("Subsystems").getLayout("Climber", BuiltInLayouts.kList);
        layout.withProperties(Map.of("Label position", "LEFT"));
        layout.addNumber("Winch speed", () -> winch.get());

    }

    @Override
    public void periodic() {
    }

    public void turnWinch(int speed) {
        winch.set(speed);
    }

}
