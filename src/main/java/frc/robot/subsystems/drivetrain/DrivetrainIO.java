package frc.robot.subsystems.drivetrain;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.*;

public abstract class DrivetrainIO {
    @AutoLog
    public static class DrivetrainIOInputs {
        // todo
    }

    public abstract void drive(Measure<Velocity<Distance>> left, Measure<Velocity<Distance>> right);
}
