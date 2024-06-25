package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class Transmission extends SubsystemBase {
    public void shift(final boolean high) {
        Robot.cont.pneumatics.io.shift(high);
    }
}
