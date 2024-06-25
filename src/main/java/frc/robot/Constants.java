package frc.robot;

import java.util.function.Supplier;

import edu.wpi.first.units.*;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.oi.OI;
import frc.robot.oi.OIRealXbox;
import frc.robot.subsystems.drivetrain.*;
import frc.robot.subsystems.intake.*;
import frc.robot.subsystems.pneumatics.*;
import frc.robot.subsystems.shooter.*;

public final class Constants {
    private Constants() throws Exception { throw new Exception("Cannot instantiate `Constants`"); }

    public static enum Implementation {
        Unknown, Real, Simulation, Replay,
    }

    public static Implementation impl = Implementation.Unknown;

    static {
        if(Robot.isReal()) Constants.impl = Implementation.Real;
    }

    public static final class RealHardware {
        private RealHardware() throws Exception { throw new Exception("Cannot instantiate `Constants.RealHardware"); }

        public static final Supplier<OI> oi = () -> new OIRealXbox(new CommandXboxController(0));

        public static final Supplier<PneumaticsIO> pneumatics = PneumaticsIORealCTREPCM::new;

        public static final Supplier<DrivetrainIO> drivetrain = DrivetrainIORealFalcon500::new;
        public static final Supplier<IntakeIO> intake = IntakeIORealTalonSRX::new;
        public static final Supplier<FlywheelIO> flywheel = FlywheelIORealTalonFX::new;
    }

    public static final class CAN {
        private CAN() throws Exception { throw new Exception("CAN-not instantiate `Constants.CAN`"); }

        // todo
        public static final int pcm = 0;

        public static final class Drivetrain {
            private Drivetrain() throws Exception {
                throw new Exception("Cannot instantiate `Constants.CAN.Drivetrain`");
            }

            public static final int leftPrimary = 0;
            public static final int leftSecondary = 1;

            public static final int rightPrimary = 14;
            public static final int rightSecondary = 15;
        }

        public static final class Intake {
            private Intake() throws Exception { throw new Exception("Cannot instantiate `Constants.CAN.Intake`"); }

            public static final int roller = 4;

            public static final int interiorLeft = 10;
            public static final int interiorRight = 11;
        }

        public static final class Shooter {
            private Shooter() throws Exception { throw new Exception("Cannot instantiate `Constants.CAN.Shooter`"); }

            public static final int upper = 2;
            public static final int lower = 3;
        }

        public static final class Climber {
            private Climber() throws Exception { throw new Exception("Cannot instantiate `Constants.CAN.Climber`"); }

            public static final int primary = 0;
            public static final int secondary = 0;
        }
    }

    public static final class Pneumatics {
        private Pneumatics() throws Exception { throw new Exception("Cannot instantiate `Constants.Pneumatics`"); }

        // todo
        public static final int shifter = 0;
        public static final int ejectors = 1;
        public static final int climbers = 2;
        public static final int intake = 3;
    }

    public static final class Drivetrain {
        private Drivetrain() throws Exception { throw new Exception("Cannot instantiate `Constants.Drivetrain`"); }

        public static final Measure<Velocity<Distance>> maxSpeed = Units.MetersPerSecond.of(4);
    }
}
