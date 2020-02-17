/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController.Axis;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import ler.robot.subsystems.Turret;
import ler.robot.subsystems.Shooter;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class RobotMap {
  public static final class DriveConstants {
    public static final int LEFT_DRIVE_SPARK_1 = 2;
    public static final int LEFT_DRIVE_SPARK_2 = 3;
    public static final int LEFT_DRIVE_SPARK_3 = 4;
    public static final int RIGHT_DRIVE_SPARK_1 = 5;
    public static final int RIGHT_DRIVE_SPARK_2 = 6;
    public static final int RIGHT_DRIVE_SPARK_3 = 7;

    public static final int SHOOTER_TOP_TALON = 18;
    public static final int SHOOTER_BOTTOM_TALON = 11;
    public static final int TURRET_TALON = 9;
    public static final int TURRET_FEEDER_TALON = 17;
    
  }

  public static final class OIConstants {
    public static final int DRIVER_CONTROLLER_PORT = 1;
    public static final int OPERATOR_CONTROLLER_PORT = 2;
    
    public static final int HALF_SPEED_BUTTON = Button.kBumperRight.value;
    public static final int LEFT_JOYSTICK_X = Axis.kLeftX.value;

    public static final int AIMBOT_BUTTON = Button.kBumperRight.value;
  }

  // The motors on the left side of the drive.
  public static final CANSparkMax leftDriveSpark1 = new CANSparkMax(DriveConstants.LEFT_DRIVE_SPARK_1, MotorType.kBrushless);
  public static final CANSparkMax leftDriveSpark2 = new CANSparkMax(DriveConstants.LEFT_DRIVE_SPARK_2, MotorType.kBrushless);
  public static final CANSparkMax leftDriveSpark3 = new CANSparkMax(DriveConstants.LEFT_DRIVE_SPARK_3, MotorType.kBrushless);
  

  // The motors on the right side of the drive.
  public static final CANSparkMax rightDriveSpark1 = new CANSparkMax(DriveConstants.RIGHT_DRIVE_SPARK_1, MotorType.kBrushless);
  public static final CANSparkMax rightDriveSpark2 = new CANSparkMax(DriveConstants.RIGHT_DRIVE_SPARK_2, MotorType.kBrushless);
  public static final CANSparkMax rightDriveSpark3 = new CANSparkMax(DriveConstants.RIGHT_DRIVE_SPARK_3, MotorType.kBrushless);


  // Turret motors (shooter, feeder, pivoting)
  public static final TalonSRX shooterTopTalon = new TalonSRX(DriveConstants.SHOOTER_TOP_TALON);
  public static final TalonSRX shooterBottomTalon = new TalonSRX(DriveConstants.SHOOTER_BOTTOM_TALON);
  public static final TalonSRX turretMotor = new TalonSRX(DriveConstants.TURRET_TALON);
  public static final TalonSRX shooterFeederTalon = new TalonSRX(DriveConstants.TURRET_FEEDER_TALON);


  // The robot's drive
  public static final DifferentialDrive m_drive = new DifferentialDrive(leftDriveSpark1, rightDriveSpark1);

  public static void init(){
    // Driving logic
    leftDriveSpark2.follow(leftDriveSpark1);
    leftDriveSpark3.follow(leftDriveSpark1);

    leftDriveSpark1.setInverted(true);
    rightDriveSpark1.setInverted(false);

    rightDriveSpark2.follow(rightDriveSpark1);
    rightDriveSpark3.follow(rightDriveSpark1);

    // Turret motors PIDS
    turretMotor.config_kF(0, Turret.kF);
    turretMotor.config_kP(0, Turret.kP);
    turretMotor.config_kI(0, Turret.kI);
    turretMotor.config_kD(0, Turret.kD);

    shooterTopTalon.config_kF(0, Shooter.kF);
    shooterTopTalon.config_kP(0, Shooter.kP);
    shooterTopTalon.config_kI(0, Shooter.kI);
    shooterTopTalon.config_kD(0, Shooter.kD);

    shooterTopTalon.config_kF(0, Shooter.kF);
    shooterTopTalon.config_kP(0, Shooter.kP);
    shooterTopTalon.config_kI(0, Shooter.kI);
    shooterTopTalon.config_kD(0, Shooter.kD);
  }
}
