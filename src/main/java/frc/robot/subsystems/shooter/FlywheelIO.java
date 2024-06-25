package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.*;

public abstract class FlywheelIO {
    @AutoLog
    public static class FlywheelIOInputs {
        public Measure<Dimensionless> demandUpper = Units.Percent.zero();
        public Measure<Dimensionless> demandLower = Units.Percent.zero();
        public Measure<Velocity<Angle>> velocityUpper = Units.RPM.zero();
        public Measure<Velocity<Angle>> velocityLower = Units.RPM.zero();
    }

    public abstract void run(Measure<Dimensionless> upper, Measure<Dimensionless> lower);

    public abstract void updateInputs(FlywheelIOInputsAutoLogged inputs);
}
