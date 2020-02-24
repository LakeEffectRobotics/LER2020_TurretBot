/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import ler.robot.subsystems.Turret;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class TurretAimCommand extends CommandBase {
  private Turret turret;
  /**
   * Creates a new TurretStopCommand.
   */
  public TurretAimCommand(Turret turret) {
    this.turret = turret;
    addRequirements(turret);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    turret.limeligntAim();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //dont copy and paste
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    double x = table.getEntry("tx").getDouble(0.0);

    if(Math.abs(x) < 0.05){
      //return true;
    }
    return false;
  }
}
