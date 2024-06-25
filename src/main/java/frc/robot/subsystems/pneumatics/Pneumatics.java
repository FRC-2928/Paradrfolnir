package frc.robot.subsystems.pneumatics;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Pneumatics extends SubsystemBase {
    public Pneumatics() {
        if(Robot.isReal()) this.io = Constants.RealHardware.pneumatics.get();
        else throw new Error("unimpl: PneumaticsIO non-real implementation");
    }

    public final PneumaticsIO io;
    public final PneumaticsIOInputsAutoLogged inputs = new PneumaticsIOInputsAutoLogged();

    @Override
    public void periodic() { this.io.updateInputs(this.inputs); }
}
