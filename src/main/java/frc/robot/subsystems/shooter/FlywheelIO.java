package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.*;

public abstract class FlywheelIO {
    @AutoLog
    public static class FlywheelIOInputs {
        public Measure<Velocity<Angle>> velocityPrimary = Units.RPM.zero();
        public Measure<Velocity<Angle>> velocitySecondary = Units.RPM.zero();
    }

    public void run(final Measure<Dimensionless> upper, final Measure<Dimensionless> lower) {}

    public void updateInputs(final FlywheelIOInputsAutoLogged inputs) {}
}
