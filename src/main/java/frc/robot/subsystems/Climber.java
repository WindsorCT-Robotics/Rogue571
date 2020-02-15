

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
/**
 * @author GABRIEL THE SUPER MEGA ULTRA AMAZING TERRIFIC GENIUS
 */
public class Climber extends SubsystemBase {

    private final WPI_TalonSRX winch;

    public Climber() {

        winch = new WPI_TalonSRX(5);

    }

    @Override
    public void periodic() {}

    public void turnWinch(int speed){
        winch.set(speed);
        }

}
