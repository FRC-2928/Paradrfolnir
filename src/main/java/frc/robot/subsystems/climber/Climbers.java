package frc.robot.subsystems.climber;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class Climbers extends SubsystemBase {
    public void forward(final boolean forward) {
        Robot.cont.pneumatics.io.climbersForward(forward);
    }
}
