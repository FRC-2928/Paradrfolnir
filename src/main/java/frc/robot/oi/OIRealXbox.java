package frc.robot.oi;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class OIRealXbox extends OI {
    public OIRealXbox(final CommandXboxController hid) {
        this.hid = hid;
    }

    public final CommandXboxController hid;

    @Override
    public double driveAxial() {
        return -MathUtil.applyDeadband(this.hid.getLeftY(), 0.1);
    }

    @Override
    public double driveTheta() {
        return -MathUtil.applyDeadband(this.hid.getRightX(), 0.1);
    }

    @Override
    public Trigger shift() {
        return this.hid.leftStick();
    }

    @Override
    public Trigger intake() {
        return this.hid.leftTrigger();
    }
}
