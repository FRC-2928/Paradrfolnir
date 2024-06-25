// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.drivetrain.JoystickDrive;
import frc.robot.oi.OI;
import frc.robot.oi.OIRealXbox;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.Transmission;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.pneumatics.Pneumatics;
import frc.robot.subsystems.shooter.Flywheel;

public class RobotContainer {
    public final OI oi = Constants.RealHardware.oi.get();

    public final Pneumatics pneumatics = new Pneumatics();
    
    public final Drivetrain drivetrain = new Drivetrain();
    public final Transmission transmission = new Transmission();
    public final Intake intake = new Intake();
    public final Flywheel flywheel = new Flywheel();

    public void config(final Robot robot) {
        this.oi.config(robot);
    }
}
