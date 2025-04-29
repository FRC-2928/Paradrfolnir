package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.shooter.FlywheelIO.FlywheelIOInputs;

public final class Flywheel extends SubsystemBase {
    public Flywheel() {
        if(Robot.isReal()) this.io = Constants.RealHardware.flywheel.get();
        else throw new Error("unimpl: FlywheelIO non-real implementation");
    }

    public final FlywheelIO io;
    public final FlywheelIOInputs inputs = new FlywheelIOInputs();

    @Override
    public void periodic() {
        this.io.updateInputs(this.inputs);
        // Logger.processInputs("Flywheel", this.inputs);
    }
}
