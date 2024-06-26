package frc.robot.subsystems.drivetrain;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.*;

public abstract class DrivetrainIO {
    @AutoLog
    public static class DrivetrainIOInputs {
        public Measure<Velocity<Distance>> velocityLeft = Units.MetersPerSecond.zero();
        public Measure<Velocity<Distance>> velocityRight = Units.MetersPerSecond.zero();
    }

    public void drive(final Measure<Velocity<Distance>> left, final Measure<Velocity<Distance>> right) {}

    public void updateInputs(final DrivetrainIOInputsAutoLogged inputs) {}
}
