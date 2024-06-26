package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.intake.IntakeIO.FeederDemand;

public final class Intake extends SubsystemBase {
    public Intake() {
        if(Robot.isReal()) this.io = Constants.RealHardware.intake.get();
        else throw new Error("unimpl: IntakeIO non-real implementation");
    }

    public final IntakeIO io;
    public final IntakeIOInputsAutoLogged inputs = new IntakeIOInputsAutoLogged();

    public final void ejectors(final boolean open) { Robot.instance.pneumatics.io.openEjectors(open); }

    public final void config(final Robot robot) {
        this.setDefaultCommand(new RunCommand(() -> {
            if(this.inputs.innerHeld) {
                this.io.feeder(FeederDemand.Idle);

                if(this.inputs.outerHeld) {
                    this.io.intake(false);
                    robot.pneumatics.io.lowerIntake(true);
                } else {
                    this.io.intake(true);
                    robot.pneumatics.io.lowerIntake(robot.oi.intake.getAsBoolean());
                }
            } else {
                this.io.feeder(FeederDemand.Intake);
                this.io.intake(true);
                robot.pneumatics.io.lowerIntake(robot.oi.intake.getAsBoolean());
            }
        }, this));
    }

    @Override
    public final void periodic() {
        this.io.updateInputs(this.inputs);
        Logger.processInputs("Intake", this.inputs);
    }
}
