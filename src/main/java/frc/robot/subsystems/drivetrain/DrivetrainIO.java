package frc.robot.subsystems.drivetrain;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.*;

public abstract class DrivetrainIO {
    @AutoLog
    public static class DrivetrainIOInputs {
        public LinearVelocity velocityLeft = Units.MetersPerSecond.zero();
        public LinearVelocity velocityRight = Units.MetersPerSecond.zero();
    }

    public void drive(final LinearVelocity left, final LinearVelocity right) {}

    public void updateInputs(final DrivetrainIOInputs inputs) {}
}
