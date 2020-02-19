
package frc.robot.subsystems;

import java.util.Map;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Power extends SubsystemBase {
    private final PowerDistributionPanel pDP = new PowerDistributionPanel(20);
    private final DoubleSupplier inputVoltageSupplier,
                                 temperatureCSupplier,
                                 totalCurrentSupplier,
                                 lfMotorCurrent,
                                 lrMotorCurrent,
                                 rfMotorCurrent,
                                 rrMotorCurrent,
                                 winchMotorCurrent,
                                 levelMotorCurrent,
                                 intakeMotorCurrent,
                                 colorSpinnerMotorCurrent,
                                 conveyor1MotorCurrent,
                                 conveyor2MotorCurrent,
                                 PcmCurrent;

    public Power() {
        final ShuffleboardLayout powerDistributionPanelLayout = Shuffleboard.getTab("Power")
        .getLayout("PowerPanel", BuiltInLayouts.kList);
        powerDistributionPanelLayout.withProperties(Map.of("Label position", "LEFT"));

        inputVoltageSupplier = () -> pDP.getVoltage();
        temperatureCSupplier = () -> pDP.getTemperature();
        totalCurrentSupplier = () -> pDP.getTotalCurrent();
        lfMotorCurrent    = () -> pDP.getCurrent( 0);
        lrMotorCurrent    = () -> pDP.getCurrent( 1);
        rfMotorCurrent    = () -> pDP.getCurrent( 2);
        rrMotorCurrent    = () -> pDP.getCurrent( 3);
        winchMotorCurrent    = () -> pDP.getCurrent( 4);
        levelMotorCurrent    = () -> pDP.getCurrent( 5);
        intakeMotorCurrent    = () -> pDP.getCurrent( 6);
        colorSpinnerMotorCurrent    = () -> pDP.getCurrent( 7);
        conveyor1MotorCurrent    = () -> pDP.getCurrent( 8);
        conveyor2MotorCurrent    = () -> pDP.getCurrent( 9);
        PcmCurrent    = () -> pDP.getCurrent(14);


        powerDistributionPanelLayout.addNumber("Input Voltage", inputVoltageSupplier);
        powerDistributionPanelLayout.addNumber("Temperature (C)", temperatureCSupplier);
        powerDistributionPanelLayout.addNumber("Total Current", totalCurrentSupplier);
        powerDistributionPanelLayout.addNumber("Left Front motor current", lfMotorCurrent);
        powerDistributionPanelLayout.addNumber("Left Rear motor current", lrMotorCurrent);
        powerDistributionPanelLayout.addNumber("Right Front motor current", rfMotorCurrent);
        powerDistributionPanelLayout.addNumber("Right Rear motor current", rrMotorCurrent);
        powerDistributionPanelLayout.addNumber("Winch motor current", winchMotorCurrent);
        powerDistributionPanelLayout.addNumber("Level motor current", levelMotorCurrent);
        powerDistributionPanelLayout.addNumber("Intake motor current", intakeMotorCurrent);
        powerDistributionPanelLayout.addNumber("Color Spinner motor current", colorSpinnerMotorCurrent);
        powerDistributionPanelLayout.addNumber("Conveyor 1 current", conveyor1MotorCurrent);
        powerDistributionPanelLayout.addNumber("Conveyor 2 current", conveyor2MotorCurrent);
        powerDistributionPanelLayout.addNumber("PCM current", PcmCurrent);
    }
}
