package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.*;

public abstract class TurretIO {
    @AutoLog
    public static class TurretIOInputs {
        public Angle angle = Units.Rotations.zero();
        public AngularVelocity velocity = Units.RotationsPerSecond.zero();
        public boolean limitLeft = false;
        public boolean limitRight = false;
    }

    public void turn(final AngularVelocity rotation) {}

    public void to(final Angle target) {}

    public void updateInputs(final TurretIOInputs inputs) {}
}
