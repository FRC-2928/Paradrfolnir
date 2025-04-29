package frc.robot.subsystems.pneumatics;

import org.littletonrobotics.junction.AutoLog;

public abstract class PneumaticsIO {
    @AutoLog
    public static class PneumaticsIOInputs {
        public String impl = "unknown";

        public boolean compressorEnabled = false;

        public boolean shifted = false;
        public boolean intakeDown = false;
        public boolean ejectorsOpen = false;
        public boolean climbersForward = false;
    }

    public void compressor(final boolean enabled) {}

    public void shift(final boolean enabled) {}

    public void lowerIntake(final boolean enabled) {}

    public void openEjectors(final boolean enabled) {}

    public void climbersForward(final boolean enabled) {}

    public void updateInputs(final PneumaticsIOInputs inputs) {}
}
