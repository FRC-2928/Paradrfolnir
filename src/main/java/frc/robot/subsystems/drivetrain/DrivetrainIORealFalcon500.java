package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.ParentDevice;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.units.*;
import frc.robot.Constants;

public class DrivetrainIORealFalcon500 extends DrivetrainIO {
    public DrivetrainIORealFalcon500() {
        final TalonFXConfiguration leftPrimary = new TalonFXConfiguration();
        leftPrimary.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        this.leftPrimary.getConfigurator().apply(leftPrimary);
        
        final TalonFXConfiguration leftSecondary = new TalonFXConfiguration();
        this.leftSecondary.getConfigurator().apply(leftSecondary);
        this.leftSecondary.setControl(new Follower(this.leftPrimary.getDeviceID(), false));

        final TalonFXConfiguration rightPrimary = new TalonFXConfiguration();
        rightPrimary.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        this.rightPrimary.getConfigurator().apply(rightPrimary);

        final TalonFXConfiguration rightSecondary = new TalonFXConfiguration();
        this.rightSecondary.getConfigurator().apply(rightSecondary);
        this.rightSecondary.setControl(new Follower(this.rightPrimary.getDeviceID(), false));

        BaseStatusSignal.setUpdateFrequencyForAll(100, this.leftVelocity, this.rightVelocity);
        ParentDevice.optimizeBusUtilizationForAll(this.leftPrimary, this.leftSecondary, this.rightPrimary, this.rightSecondary);
    }

    public final TalonFX leftPrimary = new TalonFX(Constants.CAN.Drivetrain.leftPrimary);
    public final TalonFX leftSecondary = new TalonFX(Constants.CAN.Drivetrain.leftSecondary);
    public final StatusSignal<Double> leftVelocity = this.leftPrimary.getVelocity();
    
    public final TalonFX rightPrimary = new TalonFX(Constants.CAN.Drivetrain.rightPrimary);
    public final TalonFX rightSecondary = new TalonFX(Constants.CAN.Drivetrain.rightSecondary);
    public final StatusSignal<Double> rightVelocity = this.rightPrimary.getVelocity();

    @Override
    public void drive(final Measure<Velocity<Distance>> left, final Measure<Velocity<Distance>> right) {
        this.leftPrimary.set(left.in(Units.MetersPerSecond) / Constants.Drivetrain.maxSpeed.in(Units.MetersPerSecond));
        this.rightPrimary.set(right.in(Units.MetersPerSecond) / Constants.Drivetrain.maxSpeed.in(Units.MetersPerSecond));
    }

    @Override
    public void updateInputs(final DrivetrainIOInputsAutoLogged inputs) {
        BaseStatusSignal.refreshAll(this.leftVelocity, this.rightVelocity);

        inputs.velocityLeft = Units.MetersPerSecond.of(this.leftVelocity.getValueAsDouble());
        inputs.velocityRight = Units.MetersPerSecond.of(this.rightVelocity.getValueAsDouble());
    }
}
