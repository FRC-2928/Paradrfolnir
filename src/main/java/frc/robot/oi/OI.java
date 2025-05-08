package frc.robot.oi;

import java.util.function.Supplier;
import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.Tuning;
import frc.robot.subsystems.intake.IntakeIO.FeederDemand;

public class OI {
    protected OI() {}

    public Supplier<Double> driveAxial;
    public Supplier<Double> driveTheta;
    public Trigger shift;

    public Trigger intake; // lower the intake
    public Trigger eject; // eject out the back
    public Trigger rectify; // run interior wheels forward until a ball is seen to rectify the issue where sometimes a ball just spins on top of the ramps

    public Trigger turretLeft;
    public Trigger turretRight;
    public Trigger homeTurret;

    public Trigger toggleFlywheel;
    public Trigger shoot;

    public Trigger raiseClimbers;
    public Trigger lowerClimbers;
    public Trigger climberPosition;

    public final void config(final Robot robot) {
        robot.drivetrain.setDefaultCommand(new RunCommand(() -> robot.drivetrain.demand(
            new ChassisSpeeds(
                Units.MetersPerSecond.of(this.driveAxial.get() * Constants.Drivetrain.maxSpeed.in(Units.MetersPerSecond)),
                Units.MetersPerSecond.zero(),
                Units.RotationsPerSecond.of(this.driveTheta.get() * 3)
            )
        ), robot.drivetrain));
        this.shift.toggleOnTrue(new StartEndCommand(
            () -> robot.transmission.shift(true),
            () -> robot.transmission.shift(false),
            robot.transmission
        ));

        // todo: wait before ejecting
        this.eject.whileTrue(new StartEndCommand(
            () -> {
                robot.intake.ejectors(true);
                robot.intake.io.feeder(FeederDemand.Shoot);
            }, () -> {
                robot.intake.ejectors(false);
                robot.intake.io.feeder(FeederDemand.Intake);
            }, robot.intake
        ));

        this.rectify.whileTrue(new StartEndCommand(
            () -> robot.intake.io.feeder(FeederDemand.Rectify),
            () -> robot.intake.io.feeder(FeederDemand.Idle),
            robot.intake
        ));

        this.turretLeft.whileTrue(new StartEndCommand(
            () -> robot.turret.io.turn(Units.RotationsPerSecond.of(-0.25)),
            () -> robot.turret.io.turn(Units.RotationsPerSecond.of(0)),
            robot.turret
        ));
        this.turretRight.whileTrue(new StartEndCommand(
            () -> robot.turret.io.turn(Units.RotationsPerSecond.of(0.25)),
            () -> robot.turret.io.turn(Units.RotationsPerSecond.of(0)),
            robot.turret
        ));
        // todo: turret home

        this.toggleFlywheel.toggleOnTrue(new StartEndCommand(
            () -> {robot.flywheel.io.run(Units.Percent.of(Tuning.shootSpeed.get()), Units.Percent.of(Tuning.shootSpeed.get()).times(Tuning.shootRatio.get()));
                    Logger.recordOutput("CommandShoot/shootSpeed", Tuning.shootSpeed.get());
                    Logger.recordOutput("CommandShoot/shootRatio", Tuning.shootRatio.get());},
            () -> robot.flywheel.io.run(Units.Percent.of(0), Units.Percent.of(0)),
            robot.flywheel
        ));
        this.shoot.onTrue(new Command() {
            { this.addRequirements(robot.intake); }

            public double start;
            
            @Override
            public void initialize() {
                // todo: cancel self if velocity too low, but have a dashboard exception switch
                if(!robot.intake.inputs.innerHeld) {
                    this.cancel();
                    return;
                }

                robot.intake.io.feeder(FeederDemand.Shoot);
                this.start = Timer.getFPGATimestamp();
            }

            @Override
            public void end(boolean interrupted) { robot.intake.io.feeder(FeederDemand.Intake); }

            @Override
            public boolean isFinished() { return Timer.getFPGATimestamp() - this.start > 1.5; }

            @Override
            public InterruptionBehavior getInterruptionBehavior() { return InterruptionBehavior.kCancelIncoming; }
        });

        // this.raiseClimbers.whileTrue(new StartEndCommand(
        //     () -> robot.climber.io.move(Units.InchesPerSecond.of(40)),
        //     () -> robot.climber.io.move(Units.InchesPerSecond.zero())
        // ));
        // this.lowerClimbers.whileTrue(new StartEndCommand(
        //     () -> robot.climber.io.move(Units.InchesPerSecond.of(-40)),
        //     () -> robot.climber.io.move(Units.InchesPerSecond.zero())
        // ));
        // this.climberPosition.toggleOnTrue(new StartEndCommand(
        //     () -> robot.climber.forward(true),
        //     () -> robot.climber.forward(false)
        // ));
    }
}
