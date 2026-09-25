// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.ctre.phoenix6.swerve.SwerveRequest;
import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.DriveTrain.CommandSwerveDrivetrain;
import frc.robot.DriveTrain.SwerveConstants;

public class RobotContainer {

  

  public CommandXboxController joystick = new CommandXboxController(0);
  public CommandSwerveDrivetrain drivetrain = new CommandSwerveDrivetrain(
        SwerveConstants.DrivetrainConstants,
        SwerveConstants.FrontLeft,
        SwerveConstants.FrontRight,
        SwerveConstants.BackLeft,
        SwerveConstants.BackRight
    );
  

  private final Outake outake;
  private final Flywheels flywheels;
  private final Intake intake;

  private final SendableChooser<Command> autoChooser;


  private final SwerveRequest.FieldCentricFacingAngle driveFacingAngle = new SwerveRequest.FieldCentricFacingAngle();

  private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
        .withDeadband(0.1)
        .withRotationalDeadband(0.1);

  private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();

  public RobotContainer() {
    configureBindings();

    autoChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Chooser", autoChooser);
  }

  private void configureBindings() {

      drivetrain.setDefaultCommand(
        drivetrain.applyRequest(() -> 
          drive
            .withVelocityX(-joystick.getLeftY() * SwerveConstants.kVelLinearMax)
            .withVelocityY(-joystick.getLeftX() * SwerveConstants.kVelLinearMax)
            .withRotationalRate(-joystick.getRightX() * SwerveConstants.kVelAngularMax)
        )
      );

        
        joystick.y().onTrue(
            drivetrain.runOnce(() -> drivetrain.seedFieldCentric())
        );

        
        joystick.x().whileTrue(
            drivetrain.applyRequest(() -> brake)
        );

        
        joystick.b().whileTrue(
        drivetrain.applyRequest(() ->
        driveFacingAngle
            .withVelocityX(-joystick.getLeftY())
            .withVelocityY(-joystick.getLeftX())
            .withTargetDirection(Rotation2d.fromDegrees(0))
    )
);
    }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
