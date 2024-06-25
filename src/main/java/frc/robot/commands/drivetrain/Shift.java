package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drivetrain.Transmission;

public final class Shift extends Command {
    public Shift(final Transmission transmission) {
        this.transmission = transmission;

        this.addRequirements(transmission);
    }

    public final Transmission transmission;

    @Override
    public void initialize() { this.transmission.shift(true); }

    @Override
    public void end(boolean interrupted) { this.transmission.shift(false); }
}
