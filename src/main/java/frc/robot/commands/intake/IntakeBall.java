package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake.Intake;

public final class IntakeBall extends Command {
    public IntakeBall(Intake intake) {
        this.intake = intake;

        this.addRequirements(intake);
    }

    public final Intake intake;

    @Override
    public void initialize() { this.intake.lower(true); }

    @Override
    public void end(boolean interrupted) { this.intake.lower(false); }
}
