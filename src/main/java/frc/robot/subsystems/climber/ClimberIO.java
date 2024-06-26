package frc.robot.subsystems.climber;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.*;

public abstract class ClimberIO {
    @AutoLog
    public static class ClimberIOInputs {
        public Measure<Distance> position = Units.Inches.zero();
    }

    public void move(final Measure<Velocity<Distance>> velocity) {}

    public void updateInputs(final ClimberIOInputsAutoLogged inputs) {}
}
