package frc.robot.oi;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Robot;
import frc.robot.commands.drivetrain.JoystickDrive;
import frc.robot.commands.drivetrain.Shift;
import frc.robot.commands.intake.IntakeBall;

public abstract class OI {
    public abstract double driveAxial();

    public abstract double driveTheta();

    public abstract Trigger shift();
    
    public abstract Trigger intake();

    public final void config(final Robot robot) {
        robot.container.drivetrain.setDefaultCommand(new JoystickDrive(robot.container.drivetrain, this));
        this.shift().toggleOnTrue(new Shift(robot.container.transmission));

        this.intake().whileTrue(new IntakeBall(robot.container.intake));
    }
}
