/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import ler.robot.subsystems.Intake;

public class IntakeArmCommand extends InstantCommand {
    Intake intake;

    

  /**
   * Creates a new TurretCommand.
   */
  public IntakeArmCommand(Intake i) {
      intake = i;
    // Use addRequirements() here to declare subsystem dependencies.

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      if(!intake.isDeployed) {
          intake.retractIntake();
          System.out.println("EXTENDO PISTONO");
          intake.isDeployed = true;
      }
      else {
        intake.extendIntake();
        System.out.println("DEXTENDO PISTONO");

        intake.isDeployed = false;
      }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

}
