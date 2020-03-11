
package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Leveler;

public class Sidle extends CommandBase {
    private final Leveler leveling;
    private final DoubleSupplier speed;

    public Sidle(Leveler leveling, DoubleSupplier speed) {
        this.leveling = leveling;
        this.speed = speed;

        addRequirements(leveling);
    }

    @Override
    public void execute() {
        leveling.move(speed.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        leveling.move(0);
    }
} 