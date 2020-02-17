/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import ler.robot.RobotMap;

public class Shooter extends SubsystemBase {
  public static final double kP = 0.25;
  public static final double kI = 0.001;
  public static final double kD = 20;
  public static final double kF = 1;
  
  
  public Shooter() {

  }


  public void setSpeed(double speed){
    //speed = speed*1;
    RobotMap.shooterBottomTalon.set(ControlMode.PercentOutput, speed*0.65);
    RobotMap.shooterTopTalon.set(ControlMode.PercentOutput, -speed*0.80);
  }
}
