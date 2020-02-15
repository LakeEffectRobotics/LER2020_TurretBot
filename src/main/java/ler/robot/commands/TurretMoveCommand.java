/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import ler.robot.RobotMap;
import ler.robot.subsystems.Turret;
import ler.robot.OI;

public class TurretMoveCommand extends CommandBase {
  private final Turret turret;
  private Double rotation;
  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   */
  public TurretMoveCommand(Turret subsystem, DoubleSupplier rotation) {
    turret = subsystem;
    this.rotation = rotation.getAsDouble();

    addRequirements(turret);
  }

  @Override
  public void execute() {
    turret.move(rotation);
  }
  
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
