package frc.robot.subsystems.pneumatics;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants;

public class PneumaticsIORealCTREPCM extends PneumaticsIO {
    public final PneumaticsControlModule pcm = new PneumaticsControlModule(Constants.CAN.pcm);

    public final Compressor compressor = this.pcm.makeCompressor();

    public final Solenoid shifter = this.pcm.makeSolenoid(Constants.Pneumatics.shifter);
    public final Solenoid intake = this.pcm.makeSolenoid(Constants.Pneumatics.intake);
    public final Solenoid ejectors = this.pcm.makeSolenoid(Constants.Pneumatics.ejectors);
    public final Solenoid climbers = this.pcm.makeSolenoid(Constants.Pneumatics.climbers);

    @Override
    public final void compressor(final boolean enabled) {
        if(enabled) this.compressor.enableDigital();
        else this.compressor.disable();
    }

    @Override
    public final void shift(final boolean value) { this.shifter.set(value); }

    @Override
    public final void lowerIntake(final boolean value) { this.intake.set(value); }

    @Override
    public final void openEjectors(final boolean value) { this.ejectors.set(value); }

    @Override
    public final void climbersForward(final boolean value) { this.climbers.set(value); }

    @Override
    public final void updateInputs(final PneumaticsIOInputsAutoLogged inputs) {
        inputs.impl = "RealCTREPCM";

        inputs.compressorEnabled = this.compressor.isEnabled();

        inputs.shifted = this.shifter.get();
        inputs.intakeDown = this.intake.get();
        inputs.ejectorsOpen = this.ejectors.get();
        inputs.climbersForward = this.climbers.get();
    }
}
