package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.AutoLog;

public abstract class IntakeIO {
    @AutoLog
    public static class IntakeIOInputs {
        public int holding = 0;
        public boolean innerHeld = false;
        public boolean outerHeld = false;
    }

    public static enum FeederDemand {
        Idle,
        Intake,
        Shoot,
        Rectify,
    }

    public void intake(boolean run) {}

    public void feeder(FeederDemand demand) {}

    public void updateInputs(IntakeIOInputs inputs) {}
}
