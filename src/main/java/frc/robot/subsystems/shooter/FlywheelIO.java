package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.*;

public abstract class FlywheelIO {
    @AutoLog
    public static class FlywheelIOInputs {
        public AngularVelocity velocityPrimary = Units.RPM.zero();
        public AngularVelocity velocitySecondary = Units.RPM.zero();
    }

    public void run(final Dimensionless upper, final Dimensionless lower) {}

    public void updateInputs(final FlywheelIOInputs inputs) {}
}
