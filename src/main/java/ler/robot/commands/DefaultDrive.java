/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import ler.robot.Robot;
import ler.robot.Tools;
import ler.robot.subsystems.Drivetrain;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes - actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class DefaultDrive extends CommandBase {
  private final Drivetrain drivetrain;
  private final DoubleSupplier left;
  private final DoubleSupplier right;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */
  public DefaultDrive(Drivetrain subsystem, DoubleSupplier left, DoubleSupplier right) {
    drivetrain = subsystem;
    this.left = left;
    this.right = right;
    addRequirements(drivetrain);
  }

  @Override
  public void execute() {
    //System.out.println("Driving");
    
    //Using the logistic function for adapted speed
    double leftSpeed = Tools.getAdaptedSpeed(-Robot.oi.getLeftInput());
    double rightSpeed = Tools.getAdaptedSpeed(-Robot.oi.getRightInput());

    //double leftSpeed = Robot.oi.leftDriverJoystick.getY();
    //double rightSpeed = Robot.oi.rightDriverJoystick.getY();
    
    // System.out.println("Speeds: " + leftSpeed + "  :  " + rightSpeed);
    double average = (leftSpeed + rightSpeed)/2;

    // if sticks are close and speed reasonable, go straight
    if (Math.abs(leftSpeed - rightSpeed) < 0.025 && Math.abs(average) > 0.25) {
      leftSpeed = average;
      rightSpeed = average;
    }

    //drivetrain.tankDrive(left.getAsDouble(), rightSpeed.getAsDouble());
    drivetrain.tankDrive(leftSpeed, rightSpeed);
    }
}
