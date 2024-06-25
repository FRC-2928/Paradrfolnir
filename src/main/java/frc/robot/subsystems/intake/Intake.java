package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.intake.IntakeIO.FeederDemand;

public class Intake extends SubsystemBase {
    public Intake() {
        if(Robot.isReal()) this.io = Constants.RealHardware.intake.get();
        else throw new Error("unimpl: IntakeIO non-real implementation");
    }

    public final IntakeIO io;
    public final IntakeIOInputsAutoLogged inputs = new IntakeIOInputsAutoLogged();

    private boolean requestIntake;

    public void lower(final boolean down) { this.requestIntake = down; }

    @Override
    public void periodic() {
        this.io.updateInputs(this.inputs);
        Logger.processInputs("Intake", this.inputs);

        if(this.inputs.innerHeld) {
            this.io.feeder(FeederDemand.Idle);

            if(this.inputs.outerHeld) {
                this.io.intake(false);
                Robot.cont.pneumatics.io.lowerIntake(true);
            } else {
                this.io.intake(true);
                Robot.cont.pneumatics.io.lowerIntake(this.requestIntake);
            }
        } else {
            this.io.feeder(FeederDemand.Intake);
            this.io.intake(true);
            Robot.cont.pneumatics.io.lowerIntake(this.requestIntake);
        }
    }
}
