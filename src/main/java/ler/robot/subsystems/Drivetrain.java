
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import ler.robot.RobotMap;

public class Drivetrain extends SubsystemBase {
  
  double maxOutput=1;
  private boolean isInverted = false;

  /**
   * Creates a new DriveSubsystem.
   */
  public Drivetrain() {
    // Sets the distance per pulse for the encoders
    /*
    m_leftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    m_rightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    */
  }

  
 /**
   * Drives the robot using arcade controls.
   *
   * @param left the commanded forward movement
   * @param right the commanded rotation
   */
  public void tankDrive(double left, double right) {

    //Slow it down
    left *= 0.9;
    right *= 0.9;
    
    if(isInverted){
      double tempRight = left * -1;
      left = right * -1;
      right = tempRight;
    }
    RobotMap.leftDriveSpark1.set(left);
    RobotMap.rightDriveSpark1.set(right);
    //System.out.println("Actual Left" + left+"\t"+ "Actual Right" + right);
  }

  public void tankStop() {
    RobotMap.leftDriveSpark1.set(0);
    RobotMap.rightDriveSpark1.set(0);
  }

  //@todo currently not mapped to anything
  public void invertControls(){
    isInverted = !isInverted;
  }

  /**
   * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    this.maxOutput = maxOutput;
  }

  public double getLeftEncoder() {
    return(((RobotMap.leftDriveSpark1.getEncoder().getPosition())));// + (RobotMap.leftDriveSpark2.getEncoder().getPosition()) + (RobotMap.leftDriveSpark3.getEncoder().getPosition())/3.00));
  }
  public double getRightEncoder() {
    return(((RobotMap.rightDriveSpark1.getEncoder().getPosition())));// + (RobotMap.rightDriveSpark2.getEncoder().getPosition()) + (RobotMap.rightDriveSpark3.getEncoder().getPosition())/3.00));
  }
  public void resetPosition(){
    RobotMap.leftDriveSpark1.getEncoder().setPosition(0);
    RobotMap.leftDriveSpark2.getEncoder().setPosition(0);
    RobotMap.leftDriveSpark3.getEncoder().setPosition(0);
    RobotMap.rightDriveSpark1.getEncoder().setPosition(0);
    RobotMap.rightDriveSpark2.getEncoder().setPosition(0);
    RobotMap.rightDriveSpark3.getEncoder().setPosition(0);
  }
}
