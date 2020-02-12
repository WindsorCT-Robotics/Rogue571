package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Compressor extends SubsystemBase {

    private final edu.wpi.first.wpilibj.Compressor c = new edu.wpi.first.wpilibj.Compressor(15);

    public Compressor() {
        c.start();
        c.setClosedLoopControl(true);

        final ShuffleboardLayout layout = Shuffleboard.getTab("Subsystems")
            .getLayout("Compressor");
        layout.addBoolean("compressor enabled?", () -> c.enabled());
        layout.addBoolean("pressure switch value", () -> c.getPressureSwitchValue());
        layout.addNumber("compressor current", () -> c.getCompressorCurrent());
    }

}

