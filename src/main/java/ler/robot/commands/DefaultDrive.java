/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

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
    drivetrain.tankDrive(left.getAsDouble(), right.getAsDouble());
  }
}
