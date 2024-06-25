package frc.robot.commands.drivetrain;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.units.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.oi.OI;
import frc.robot.subsystems.drivetrain.Drivetrain;

public final class JoystickDrive extends Command {
    public JoystickDrive(Drivetrain drivetrain, OI oi) {
        this.drivetrain = drivetrain;
        this.oi = oi;

        this.addRequirements(drivetrain);
    }

    public final Drivetrain drivetrain;
    public final OI oi;
    
    @Override
    public void execute() {
        Robot.cont.drivetrain.demand(new ChassisSpeeds(
            Units.MetersPerSecond.of(this.oi.driveAxial() * Constants.Drivetrain.maxSpeed.in(Units.MetersPerSecond)),
            Units.MetersPerSecond.zero(),
            Units.RotationsPerSecond.of(this.oi.driveTheta() * 3)
        ));
    }
}
