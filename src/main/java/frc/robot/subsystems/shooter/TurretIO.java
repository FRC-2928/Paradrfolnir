package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.*;

public abstract class TurretIO {
    @AutoLog
    public static class TurretIOInputs {
        public Measure<Angle> angle = Units.Rotations.zero();
        public Measure<Velocity<Angle>> velocity = Units.RotationsPerSecond.zero();
        public boolean limitLeft = false;
        public boolean limitRight = false;
    }

    public void turn(final Measure<Velocity<Angle>> rotation) {}

    public void to(final Measure<Angle> target) {}

    public void updateInputs(final TurretIOInputsAutoLogged inputs) {}
}
