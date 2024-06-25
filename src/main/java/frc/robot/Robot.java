// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.littletonrobotics.junction.LogFileUtil;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGReader;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends LoggedRobot {
	public static Robot instance;
	public static RobotContainer cont;

	public Robot() {
		super();
		
		switch(Constants.impl) {
			case Unknown -> throw new Error("unknown robot impl");

			case Real -> {
				//Logger.addDataReceiver(new WPILOGWriter());
				Logger.addDataReceiver(new NT4Publisher());
			}

			case Simulation -> Logger.addDataReceiver(new NT4Publisher());

			case Replay -> {
				this.setUseTiming(false);

				final String logPath = LogFileUtil.findReplayLog();
				Logger.setReplaySource(new WPILOGReader(logPath));
				Logger.addDataReceiver(new WPILOGWriter(LogFileUtil.addPathSuffix(logPath, "_sim")));
			}
		}

		Logger.start();
		
		Robot.instance = this;
	}

	public RobotContainer container;

	@Override
	public void robotInit() {
		this.container = new RobotContainer();
		Robot.cont = this.container;

		this.container.config(this);
	}

	@Override
	public void robotPeriodic() {
		CommandScheduler.getInstance().run();
	}

	@Override
	public void disabledInit() {
		CommandScheduler.getInstance().cancelAll();
	}

	@Override
	public void autonomousInit() {
		CommandScheduler.getInstance().cancelAll();
	}

	@Override
	public void teleopInit() {
		CommandScheduler.getInstance().cancelAll();
	}

	@Override
	public void testInit() {
		CommandScheduler.getInstance().cancelAll();
	}
}
