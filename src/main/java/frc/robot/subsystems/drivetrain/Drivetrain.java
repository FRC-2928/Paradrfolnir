package frc.robot.subsystems.drivetrain;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.units.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public final class Drivetrain extends SubsystemBase {
    public Drivetrain() {
        if(Robot.isReal()) this.io = Constants.RealHardware.drivetrain.get();
        else throw new Error("unimpl: DrivetrainIO non-real implementation");
    }
    
    public final DrivetrainIO io;
    public final DrivetrainIOInputsAutoLogged inputs = new DrivetrainIOInputsAutoLogged();
    
    private final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Units.Inches.of(23));

    public final void demand(final ChassisSpeeds demand) {
        DifferentialDriveWheelSpeeds speeds = this.kinematics.toWheelSpeeds(demand);
        speeds.desaturate(Constants.Drivetrain.maxSpeed);
        this.io.drive(
            Units.MetersPerSecond.of(speeds.leftMetersPerSecond),
            Units.MetersPerSecond.of(speeds.rightMetersPerSecond)
        );

        Logger.recordOutput("Drivetrain/Demand", demand);
        Logger.recordOutput("Drivetrain/DemandSpeeds", speeds);
    }

    @Override
    public final void periodic() {
        this.io.updateInputs(this.inputs);
        Logger.processInputs("Drivetrain", this.inputs);
    }
}
