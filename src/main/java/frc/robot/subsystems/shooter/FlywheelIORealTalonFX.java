package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.ParentDevice;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.*;

import frc.robot.Constants;

public final class FlywheelIORealTalonFX extends FlywheelIO {
    public FlywheelIORealTalonFX() {
        TalonFXConfiguration upper = new TalonFXConfiguration();
        upper.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        upper.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        this.upper.getConfigurator().apply(upper);

        TalonFXConfiguration lower = new TalonFXConfiguration();
        lower.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        upper.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        this.lower.getConfigurator().apply(lower);

        BaseStatusSignal.setUpdateFrequencyForAll(100, this.upperVelocity, this.lowerVelocity);
        ParentDevice.optimizeBusUtilizationForAll(this.upper, this.lower);
    }

    public final TalonFX upper = new TalonFX(Constants.CAN.Shooter.upper);
    public final TalonFX lower = new TalonFX(Constants.CAN.Shooter.lower);
    
    private final StatusSignal<Double> upperVelocity = upper.getVelocity();
    private final StatusSignal<Double> lowerVelocity = lower.getVelocity();

    @Override
    public void run(final Measure<Dimensionless> upper, final Measure<Dimensionless> lower) {
        this.upper.set(upper.baseUnitMagnitude());
        this.lower.set(lower.baseUnitMagnitude());

        Logger.recordOutput("Flywheel/Upper Demand", upper);
        Logger.recordOutput("Flywheel/Lower Demand", lower);
    }

    @Override
    public void updateInputs(FlywheelIOInputsAutoLogged inputs) {
        inputs.velocityUpper = Units.RotationsPerSecond.of(this.upperVelocity.getValueAsDouble());
        inputs.velocityLower = Units.RotationsPerSecond.of(this.lowerVelocity.getValueAsDouble());
    }
}
