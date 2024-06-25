package frc.robot.subsystems.pneumatics;

import org.littletonrobotics.junction.AutoLog;

import frc.robot.Constants;

public abstract class PneumaticsIO {
    @AutoLog
    public static class PneumaticsIOInputs {
        public Constants.Implementation impl = Constants.Implementation.Unknown;

        public boolean compressorEnabled = false;

        public boolean shifted = false;
        public boolean intakeDown = false;
        public boolean ejectorsOpen = false;
        public boolean climbersForward = false;
    }

    public abstract void compressor(boolean enabled);

    public abstract void shift(boolean enabled);

    public abstract void lowerIntake(boolean enabled);

    public abstract void openEjectors(boolean enabled);

    public abstract void climbersForward(boolean enabled);

    public abstract void updateInputs(PneumaticsIOInputsAutoLogged inputs);
}
