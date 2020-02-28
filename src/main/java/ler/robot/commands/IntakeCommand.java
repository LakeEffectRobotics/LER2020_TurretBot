/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import ler.robot.Robot;
import ler.robot.subsystems.Intake;
import ler.robot.subsystems.Hopper;
import ler.robot.subsystems.Chute;

public class IntakeCommand extends CommandBase {
  
  Intake intake;
  Hopper hopper;
  Chute chute;

  /**
   * Creates a new ShooterCommand.
   */
  public IntakeCommand(Intake i, Hopper h, Chute c) {
    intake = i;
    hopper = h;
    chute = c;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake, hopper, chute);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.driveIntake(0.6);
    hopper.driveHopper(0.6);
    chute.driveChute(0.2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}