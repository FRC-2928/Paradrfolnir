package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public final class Flywheel extends SubsystemBase {
    public Flywheel() {
        if(Robot.isReal()) this.io = Constants.RealHardware.flywheel.get();
        else throw new Error("unimpl: FlywheelIO non-real implementation");
    }

    public final FlywheelIO io;
    public final FlywheelIOInputsAutoLogged inputs = new FlywheelIOInputsAutoLogged();

    @Override
    public void periodic() {

    }
}
