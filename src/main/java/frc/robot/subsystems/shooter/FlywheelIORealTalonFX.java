package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.ParentDevice;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.*;

import frc.robot.Constants;

public final class FlywheelIORealTalonFX extends FlywheelIO {
    public FlywheelIORealTalonFX() {
        final TalonFXConfiguration primary = new TalonFXConfiguration();
        primary.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        primary.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        this.primary.getConfigurator().apply(primary);

        final TalonFXConfiguration secondary = new TalonFXConfiguration();
        secondary.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        secondary.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        this.secondary.getConfigurator().apply(secondary);

        BaseStatusSignal.setUpdateFrequencyForAll(50, this.primaryVelocity, this.secondaryVelocity);
        // ParentDevice.optimizeBusUtilizationForAll(this.secondary, this.primary);
    }

    public final TalonFX primary = new TalonFX(Constants.CAN.Shooter.flywheelPrimary);
    public final TalonFX secondary = new TalonFX(Constants.CAN.Shooter.flywheelSecondary);
    
    private final StatusSignal<AngularVelocity> primaryVelocity = this.primary.getVelocity();
    private final StatusSignal<AngularVelocity> secondaryVelocity = this.secondary.getVelocity();

    @Override
    public final void run(final Dimensionless primary, final Dimensionless secondary) {
        this.primary.set(primary.in(Units.Value));
        this.secondary.set(secondary.in(Units.Value));

        Logger.recordOutput("Flywheel/Primary Demand", primary);
        Logger.recordOutput("Flywheel/Secondary Demand", secondary);
    }

    @Override
    public final void updateInputs(final FlywheelIOInputs inputs) {
        BaseStatusSignal.refreshAll(this.primaryVelocity, this.secondaryVelocity);

        inputs.velocityPrimary = Units.RotationsPerSecond.of(this.primaryVelocity.getValueAsDouble());
        inputs.velocitySecondary = Units.RotationsPerSecond.of(this.secondaryVelocity.getValueAsDouble());
    }
}
