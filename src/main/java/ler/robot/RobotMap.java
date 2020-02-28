/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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
  public static final class CANConstants {
    public static final int LEFT_DRIVE_SPARK_1 = 2;
    public static final int LEFT_DRIVE_SPARK_2 = 3;
    public static final int LEFT_DRIVE_SPARK_3 = 4;
    
    public static final int RIGHT_DRIVE_SPARK_1 = 5;
    public static final int RIGHT_DRIVE_SPARK_2 = 6;
    public static final int RIGHT_DRIVE_SPARK_3 = 7;


    public static final int INTAKE_TALON = 8;


    public static final int SHOOTER_BOTTOM_SPARK = 9;
    public static final int SHOOTER_TOP_SPARK = 10;
    public static final int TURRET_FEEDER_TALON = 11;
    public static final int TURRET_ROTATE_TALON = 12;


    public static final int WINCH_FALCON = 13;


    public static final int CHUTE_TALON = 14;
    public static final int HOPPER_TALON = 15;
  }

  public static final class SOLENOIDConstants {
    
    public static final int CLIMBER_DOWN_POSITION = 0;
    public static final int CLIMBER_UP_POSITION = 1;

    public static final int INTAKE_DOWN_POSITION = 2;
    public static final int INTAKE_UP_POSITION = 3;

  }

  public static final class OIConstants {
    public static final int DRIVER_CONTROLLER_PORT = 1;
    public static final int OPERATOR_CONTROLLER_PORT = 2;
    
    public static final int HALF_SPEED_BUTTON = Button.kBumperRight.value;
    public static final int LEFT_JOYSTICK_X = Axis.kLeftX.value;

    public static final int INTAKE_BUTTON = Axis.kRightTrigger.value;
    public static final int INTAKE_HEIGHT_BUTTON = Button.kStickRight.value;

    public static final int AIMBOT_BUTTON = Button.kBumperRight.value;

    public static final int CLIMBER_TOGGLE_BUTTON = Button.kStart.value;
    public static final int WINCH_CONTROL_BUTTON = Button.kBack.value;

  }

  // The motors on the left side of the drive.
  public static final CANSparkMax leftDriveSpark1 = new CANSparkMax(CANConstants.LEFT_DRIVE_SPARK_1, MotorType.kBrushless);
  public static final CANSparkMax leftDriveSpark2 = new CANSparkMax(CANConstants.LEFT_DRIVE_SPARK_2, MotorType.kBrushless);
  public static final CANSparkMax leftDriveSpark3 = new CANSparkMax(CANConstants.LEFT_DRIVE_SPARK_3, MotorType.kBrushless);
  

  // The motors on the right side of the drive.
  public static final CANSparkMax rightDriveSpark1 = new CANSparkMax(CANConstants.RIGHT_DRIVE_SPARK_1, MotorType.kBrushless);
  public static final CANSparkMax rightDriveSpark2 = new CANSparkMax(CANConstants.RIGHT_DRIVE_SPARK_2, MotorType.kBrushless);
  public static final CANSparkMax rightDriveSpark3 = new CANSparkMax(CANConstants.RIGHT_DRIVE_SPARK_3, MotorType.kBrushless);

  //The intake motor
  public static final TalonSRX intakeTalon = new TalonSRX(CANConstants.INTAKE_TALON);

  // Turret motors (shooter, feeder, pivoting)
  public static final CANSparkMax shooterBottomSpark = new CANSparkMax(CANConstants.SHOOTER_BOTTOM_SPARK, MotorType.kBrushless);
  public static final CANSparkMax shooterTopSpark = new CANSparkMax(CANConstants.SHOOTER_TOP_SPARK, MotorType.kBrushless);
  public static final TalonSRX shooterFeederTalon = new TalonSRX(CANConstants.TURRET_FEEDER_TALON);
  public static final TalonSRX turretRotateTalon = new TalonSRX(CANConstants.TURRET_ROTATE_TALON);

  // Ball control mechanisms
  public static final TalonSRX chuteTalon = new TalonSRX(CANConstants.CHUTE_TALON);
  public static final TalonSRX hopperTalon = new TalonSRX(CANConstants.HOPPER_TALON);

  // Winch control
  public static final TalonFX winchFalcon = new TalonFX(CANConstants.WINCH_FALCON);



  // Solenoids (Climber, Intake)
  public static DoubleSolenoid climber_position_solenoid = new DoubleSolenoid(SOLENOIDConstants.CLIMBER_DOWN_POSITION, SOLENOIDConstants.CLIMBER_UP_POSITION);

  public static DoubleSolenoid intake_position_solenoid = new DoubleSolenoid(SOLENOIDConstants.INTAKE_DOWN_POSITION, SOLENOIDConstants.INTAKE_UP_POSITION);
  

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
    turretRotateTalon.config_kF(0, Turret.kF);
    turretRotateTalon.config_kP(0, Turret.kP);
    turretRotateTalon.config_kI(0, Turret.kI);
    turretRotateTalon.config_kD(0, Turret.kD);

    shooterTopSpark.getPIDController().setFF(Shooter.kF);
    shooterTopSpark.getPIDController().setP(Shooter.kP);
    shooterTopSpark.getPIDController().setI(Shooter.kI);
    shooterTopSpark.getPIDController().setD(Shooter.kD);

    shooterBottomSpark.getPIDController().setFF(Shooter.kF);
    shooterBottomSpark.getPIDController().setP(Shooter.kP);
    shooterBottomSpark.getPIDController().setI(Shooter.kI);
    shooterBottomSpark.getPIDController().setD(Shooter.kD);
  }
}
