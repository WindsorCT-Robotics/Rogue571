
package frc.robot.subsystems;

import java.util.Map;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * @author GABRIEL THE SUPER MEGA ULTRA AMAZING TERRIFIC GENIUS
 */
public class Climber extends SubsystemBase {

    private final WPI_TalonSRX winch;
    private final Solenoid latchSolenoid;
    private final Leveler levelingMotor;

    public Climber(Leveler levelingMotor) {
        winch = new WPI_TalonSRX(5);
        addChild("Winch", winch);
        this.levelingMotor = levelingMotor;

        final ShuffleboardLayout layout = Shuffleboard.getTab("Subsystems").getLayout("Climber", BuiltInLayouts.kList);
        layout.withProperties(Map.of("Label position", "LEFT"));
        layout.addNumber("Winch speed", () -> winch.get());

        latchSolenoid = new Solenoid(15,0);
        addChild("latchSolenoid", latchSolenoid);

    }

    @Override
    public void periodic() {
    }

    public void turn(double speed) {
        if(latchSolenoid.get())
            winch.set(speed);
        else
            stop();
    }

    public void stop(){
        winch.set(0);
    }

    public void releaseLatch(){
        levelingMotor.setLockEnabled(false);
        latchSolenoid.set(true);
    }

    public void closeLatch(){
        levelingMotor.setLockEnabled(false);
        latchSolenoid.set(false);
    }
}
