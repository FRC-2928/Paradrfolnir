package frc.robot.subsystems.climber;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.*;

public abstract class ClimberIO {
    @AutoLog
    public static class ClimberIOInputs {
        public Distance position = Units.Inches.zero();
    }

    public void move(final LinearVelocity velocity) {}

    public void updateInputs(final ClimberIOInputs inputs) {}
}
