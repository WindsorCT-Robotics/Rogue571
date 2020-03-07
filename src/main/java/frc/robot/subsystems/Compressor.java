package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Compressor extends SubsystemBase {

    private final edu.wpi.first.wpilibj.Compressor compressor = new edu.wpi.first.wpilibj.Compressor(15);

    public Compressor() {
        addChild("compressor", compressor);
        compressor.start();
        compressor.setClosedLoopControl(true);

        
        final ShuffleboardLayout layout = Shuffleboard.getTab("Subsystems")
            .getLayout("Compressor",BuiltInLayouts.kList);
        layout.withProperties(Map.of("Label position", "LEFT"));
        layout.addBoolean("compressor enabled?", () -> compressor.enabled());
        layout.addBoolean("pressure switch value", () -> compressor.getPressureSwitchValue());
        layout.addNumber("compressor current", () -> compressor.getCompressorCurrent());
        
    }

}

