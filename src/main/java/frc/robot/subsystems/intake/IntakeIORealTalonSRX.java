package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import frc.robot.Constants;

public final class IntakeIORealTalonSRX extends IntakeIO {
    public IntakeIORealTalonSRX() {
        final TalonSRXConfiguration roller = new TalonSRXConfiguration();
        roller.peakOutputReverse = 0; // never let it run in reverse, ratchet will stall
        roller.forwardLimitSwitchNormal = LimitSwitchNormal.NormallyOpen;
        this.roller.configAllSettings(roller);
        this.roller.setNeutralMode(NeutralMode.Brake);
        this.roller.overrideLimitSwitchesEnable(false);

        final TalonSRXConfiguration interiorLeft = new TalonSRXConfiguration();
        interiorLeft.forwardLimitSwitchNormal = LimitSwitchNormal.NormallyClosed;
        this.interiorLeft.configAllSettings(interiorLeft);
        this.interiorLeft.setInverted(true);
        this.interiorLeft.setNeutralMode(NeutralMode.Brake);

        final TalonSRXConfiguration interiorRight = new TalonSRXConfiguration();
        this.interiorRight.configAllSettings(interiorRight);
        this.interiorRight.setNeutralMode(NeutralMode.Brake);
        this.interiorRight.set(ControlMode.Follower, this.interiorLeft.getDeviceID());
    }

    public final TalonSRX roller = new TalonSRX(Constants.CAN.Intake.roller);

    public final TalonSRX interiorLeft = new TalonSRX(Constants.CAN.Intake.interiorLeft);
    public final TalonSRX interiorRight = new TalonSRX(Constants.CAN.Intake.interiorRight);

    @Override
    public final void intake(final boolean run) {
        this.roller.set(ControlMode.PercentOutput, run ? 1 : 0);
    }

    @Override
    public final void feeder(final FeederDemand demand) {
        this.interiorLeft.overrideLimitSwitchesEnable(demand == FeederDemand.Intake);
        this.interiorLeft.set(ControlMode.PercentOutput, switch(demand) {
            case Idle -> 0;
            case Intake -> 0.5;
            case Shoot -> 1;
            case Rectify -> -0.5;
        });
    }

    @Override
    public final void updateInputs(final IntakeIOInputsAutoLogged inputs) {
        inputs.innerHeld = !this.interiorLeft.getSensorCollection().isFwdLimitSwitchClosed();
        inputs.outerHeld = this.roller.getSensorCollection().isFwdLimitSwitchClosed();
        inputs.holding = inputs.innerHeld ? (inputs.outerHeld ? 2 : 1) : 0;
    }
}
