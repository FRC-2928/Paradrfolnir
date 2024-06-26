package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.units.*;

import frc.robot.Constants;

public final class TurretIORealTalonSRX extends TurretIO {
    public TurretIORealTalonSRX() {
        final TalonSRXConfiguration turret = new TalonSRXConfiguration();
        turret.forwardLimitSwitchSource = LimitSwitchSource.Deactivated;
        turret.reverseLimitSwitchSource = LimitSwitchSource.Deactivated;
        this.turret.configAllSettings(turret);
        this.turret.setInverted(false);
        this.turret.setNeutralMode(NeutralMode.Brake);
    }

    public final TalonSRX turret = new TalonSRX(Constants.CAN.Shooter.turret);

    @Override
    public final void turn(final Measure<Velocity<Angle>> rotation) {
        this.turret.set(ControlMode.PercentOutput, rotation.in(Units.RotationsPerSecond));

        Logger.recordOutput("Turret/Primary Demand", rotation.in(Units.RotationsPerSecond));
    }

    @Override
    public final void to(final Measure<Angle> target) { throw new Error("unimpl: TurretIORealTalonSRX.to"); }

    @Override
    public final void updateInputs(final TurretIOInputsAutoLogged inputs) {
        // todo: sens:mech ratio
        inputs.angle = Units.Rotations.of(this.turret.getSelectedSensorPosition());
        inputs.velocity = Units.RotationsPerSecond.of(this.turret.getSelectedSensorVelocity());
    }
}
