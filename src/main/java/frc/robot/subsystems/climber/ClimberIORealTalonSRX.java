package frc.robot.subsystems.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.units.*;
import frc.robot.Constants;

public final class ClimberIORealTalonSRX extends ClimberIO {
    public ClimberIORealTalonSRX() {
        final TalonSRXConfiguration primary = new TalonSRXConfiguration();
        this.primary.configAllSettings(primary);
        this.primary.setNeutralMode(NeutralMode.Brake);

        final TalonSRXConfiguration secondary = new TalonSRXConfiguration();
        this.secondary.configAllSettings(secondary);
        this.secondary.setNeutralMode(NeutralMode.Brake);
        this.secondary.set(ControlMode.Follower, this.primary.getDeviceID());
    }

    public final TalonSRX primary = new TalonSRX(Constants.CAN.Climber.primary);
    public final TalonSRX secondary = new TalonSRX(Constants.CAN.Climber.secondary);

    // todo: sens:mech ratio
    @Override
    public final void move(final Measure<Velocity<Distance>> velocity) { this.primary.set(ControlMode.PercentOutput, velocity.in(Units.MetersPerSecond)); }

    @Override
    public final void updateInputs(final ClimberIOInputsAutoLogged inputs) {
        // todo: sens:mech ratio
        inputs.position = Units.Inches.of(this.primary.getSelectedSensorPosition());
    }
}
