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
import ler.robot.subsystems.Shooter;
import ler.robot.subsystems.Chute;


public class ShootCommand extends CommandBase {
  
  Shooter shooter;
  Chute chute;

  public int closeness = 250;
  /**
   * Creates a new ShooterCommand.
   */
  public ShootCommand(Shooter s, Chute c) {
    // Use addRequirements() here to declare subsystem dependencies.
    shooter = s;
    chute = c;
    //Add requiremnts - chute
    addRequirements(shooter);
    addRequirements(chute);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Math.abs(shooter.getTopSparkSpeed() - Shooter.SHOOTER_TOP_TARGET_SPEED)< closeness && 
       Math.abs(shooter.getBottomSparkSpeed()- Shooter.SHOOTER_BOTTOM_TARGET_SPEED)< closeness) {
        
        chute.driveChute(0.2);
       }
       else {
         chute.driveChute(0.0);
       }
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