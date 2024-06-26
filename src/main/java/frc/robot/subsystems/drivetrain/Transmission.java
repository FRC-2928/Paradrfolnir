package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public final class Transmission extends SubsystemBase {
    public final void shift(final boolean high) { Robot.instance.pneumatics.io.shift(high); }
}
