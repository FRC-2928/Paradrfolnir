package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Turret extends SubsystemBase {
    public Turret() {
        if(Robot.isReal()) this.io = Constants.RealHardware.turret.get();
        else throw new Error("unimpl: TurretIO non-real implementation");
    }

    public final TurretIO io;
    public final TurretIOInputsAutoLogged inputs = new TurretIOInputsAutoLogged();

    @Override
    public void periodic() {
        this.io.updateInputs(this.inputs);
        Logger.processInputs("Turret", this.inputs);
    }
}
