package frc.robot.subsystems.climber;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

// todo: io

public final class Climber extends SubsystemBase {
    public Climber() {
        if(Robot.isReal()) this.io = Constants.RealHardware.climber.get();
        else throw new Error("unimpl: ClimberIO non-real implementation");
    }

    public final ClimberIO io;
    public final ClimberIOInputsAutoLogged inputs = new ClimberIOInputsAutoLogged();
    
    public final void forward(final boolean forward) { Robot.instance.pneumatics.io.climbersForward(forward); }

    @Override
    public void periodic() {
        this.io.updateInputs(this.inputs);
        Logger.processInputs("Climber", this.inputs);
    }
}
