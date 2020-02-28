/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import ler.robot.RobotMap;



/**
 * Add your docs here.
 */
public class Intake extends SubsystemBase {

    public void driveIntake(double speed) {
        RobotMap.intakeTalon.set(ControlMode.PercentOutput, speed);
    }

    public void raiseIntake() {
        RobotMap.intake_position_solenoid.set(Value.kForward);

    }

    public void lowerIntake() {
        RobotMap.intake_position_solenoid.set(Value.kForward);
        
    }

}