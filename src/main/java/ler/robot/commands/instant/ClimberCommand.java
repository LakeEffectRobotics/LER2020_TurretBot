/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.commands.instant;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import ler.robot.Robot;
import ler.robot.subsystems.Climber;

public class ClimberCommand extends InstantCommand {
  
  Climber climber;


  /**
   * Creates a new ShooterCommand.
   */
  public ClimberCommand(Climber c) {
    climber = c;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(climber.isRaised) {
      climber.lowerClimber();
      climber.isRaised = false;
    } 
    else {
    climber.raiseClimber();
    climber.isRaised = true;
    }
  
  }
}
