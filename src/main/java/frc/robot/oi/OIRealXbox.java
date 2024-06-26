package frc.robot.oi;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public final class OIRealXbox extends OI {
    public OIRealXbox(final CommandXboxController hid) {
        this.hid = hid;
        
        this.driveAxial = () -> -MathUtil.applyDeadband(this.hid.getLeftY(), 0.1);
        this.driveTheta = () -> -MathUtil.applyDeadband(this.hid.getLeftX(), 0.1);
        this.shift = this.hid.leftStick();

        this.intake = this.hid.leftTrigger();
        this.eject = this.hid.b();
        this.rectify = this.hid.x();

        this.turretLeft = this.hid.leftBumper();
        this.turretRight = this.hid.rightBumper();

        this.toggleFlywheel = this.hid.y();
        this.shoot = this.hid.rightTrigger();

        this.raiseClimbers = this.hid.povUp();
        this.lowerClimbers = this.hid.povDown();
        this.climberPosition = this.hid.a();
    }

    public final CommandXboxController hid;
}
