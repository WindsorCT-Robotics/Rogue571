
package frc.robot.subsystems;

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
                                 channel00Supplier,
                                 channel01Supplier,
                                 channel02Supplier,
                                 channel03Supplier,
                                 channel04Supplier,
                                 channel05Supplier,
                                 channel06Supplier,
                                 channel07Supplier,
                                 channel08Supplier,
                                 channel09Supplier,
                                 channel10Supplier,
                                 channel11Supplier,
                                 channel12Supplier,
                                 channel13Supplier,
                                 channel14Supplier,
                                 channel15Supplier;

    public Power() {
        final ShuffleboardLayout powerDistributionPanelLayout = Shuffleboard.getTab("Subsystems")
        .getLayout("Power Distribution Panel", BuiltInLayouts.kList);

        inputVoltageSupplier = () -> pDP.getVoltage();
        temperatureCSupplier = () -> pDP.getTemperature();
        totalCurrentSupplier = () -> pDP.getTotalCurrent();
        channel00Supplier    = () -> pDP.getCurrent( 0);
        channel01Supplier    = () -> pDP.getCurrent( 1);
        channel02Supplier    = () -> pDP.getCurrent( 2);
        channel03Supplier    = () -> pDP.getCurrent( 3);
        channel04Supplier    = () -> pDP.getCurrent( 4);
        channel05Supplier    = () -> pDP.getCurrent( 5);
        channel06Supplier    = () -> pDP.getCurrent( 6);
        channel07Supplier    = () -> pDP.getCurrent( 7);
        channel08Supplier    = () -> pDP.getCurrent( 8);
        channel09Supplier    = () -> pDP.getCurrent( 9);
        channel10Supplier    = () -> pDP.getCurrent(10);
        channel11Supplier    = () -> pDP.getCurrent(11);
        channel12Supplier    = () -> pDP.getCurrent(12);
        channel13Supplier    = () -> pDP.getCurrent(13);
        channel14Supplier    = () -> pDP.getCurrent(14);
        channel15Supplier    = () -> pDP.getCurrent(15);

        powerDistributionPanelLayout.addNumber("Input Voltage", inputVoltageSupplier);
        powerDistributionPanelLayout.addNumber("Temperature (C)", temperatureCSupplier);
        powerDistributionPanelLayout.addNumber("Total Current", totalCurrentSupplier);
        powerDistributionPanelLayout.addNumber("Channel 00", channel00Supplier);
        powerDistributionPanelLayout.addNumber("Channel 01", channel01Supplier);
        powerDistributionPanelLayout.addNumber("Channel 02", channel02Supplier);
        powerDistributionPanelLayout.addNumber("Channel 03", channel03Supplier);
        powerDistributionPanelLayout.addNumber("Channel 04", channel04Supplier);
        powerDistributionPanelLayout.addNumber("Channel 05", channel05Supplier);
        powerDistributionPanelLayout.addNumber("Channel 06", channel06Supplier);
        powerDistributionPanelLayout.addNumber("Channel 07", channel07Supplier);
        powerDistributionPanelLayout.addNumber("Channel 08", channel08Supplier);
        powerDistributionPanelLayout.addNumber("Channel 09", channel09Supplier);
        powerDistributionPanelLayout.addNumber("Channel 10", channel10Supplier);
        powerDistributionPanelLayout.addNumber("Channel 11", channel11Supplier);
        powerDistributionPanelLayout.addNumber("Channel 12", channel12Supplier);
        powerDistributionPanelLayout.addNumber("Channel 13", channel13Supplier);
        powerDistributionPanelLayout.addNumber("Channel 14", channel14Supplier);
        powerDistributionPanelLayout.addNumber("Channel 15", channel15Supplier);
    }
}
