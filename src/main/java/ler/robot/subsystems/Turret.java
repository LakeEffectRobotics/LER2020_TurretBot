/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import ler.robot.RobotMap;
import ler.robot.OI;

public class Turret extends SubsystemBase {
  
  public static final double kP = 0.25;
  public static final double kI = 0.001;
  public static final double kD = 20;
  public static final double kF = 1;

  public static final double SPEED = 0.2;

  public Turret() {

  }

  public void move(double speed){
    speed *= 0.2;
    RobotMap.turretMotor.set(ControlMode.PercentOutput, speed);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
