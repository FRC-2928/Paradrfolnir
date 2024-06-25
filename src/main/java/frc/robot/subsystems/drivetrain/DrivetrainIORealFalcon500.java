package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.Velocity;
import frc.robot.Constants;

public class DrivetrainIORealFalcon500 extends DrivetrainIO {
    public DrivetrainIORealFalcon500() {
        TalonFXConfiguration leftPrimary = new TalonFXConfiguration();
        leftPrimary.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        this.leftPrimary.getConfigurator().apply(leftPrimary);
        TalonFXConfiguration leftSecondary = new TalonFXConfiguration();
        this.leftSecondary.getConfigurator().apply(leftSecondary);
        this.leftSecondary.setControl(new Follower(this.leftPrimary.getDeviceID(), false));

        TalonFXConfiguration rightPrimary = new TalonFXConfiguration();
        rightPrimary.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        this.rightPrimary.getConfigurator().apply(rightPrimary);
        TalonFXConfiguration rightSecondary = new TalonFXConfiguration();
        this.rightSecondary.getConfigurator().apply(rightSecondary);
        this.rightSecondary.setControl(new Follower(this.rightPrimary.getDeviceID(), false));
    }

    public final TalonFX leftPrimary = new TalonFX(Constants.CAN.Drivetrain.leftPrimary);
    public final TalonFX leftSecondary = new TalonFX(Constants.CAN.Drivetrain.leftSecondary);
    
    public final TalonFX rightPrimary = new TalonFX(Constants.CAN.Drivetrain.rightPrimary);
    public final TalonFX rightSecondary = new TalonFX(Constants.CAN.Drivetrain.rightSecondary);

    @Override
    public void drive(Measure<Velocity<Distance>> left, Measure<Velocity<Distance>> right) {
        this.leftPrimary.set(left.in(Units.MetersPerSecond) / Constants.Drivetrain.maxSpeed.in(Units.MetersPerSecond));
        this.rightPrimary.set(right.in(Units.MetersPerSecond) / Constants.Drivetrain.maxSpeed.in(Units.MetersPerSecond));
    }
}
