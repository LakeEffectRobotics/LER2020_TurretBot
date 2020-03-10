/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import ler.robot.RobotMap;



public class Chute extends SubsystemBase{

    public double startTime;
    public boolean isPulsing;

    public void driveChute(double speed) {

        RobotMap.chuteTalon.set(ControlMode.PercentOutput, speed);

    }

    public void pulseChute(double duration, double pause, double speed) {
        if(isPulsing) {
            if(System.currentTimeMillis()>=startTime + duration) {
                isPulsing = false;
                RobotMap.chuteTalon.set(ControlMode.PercentOutput, 0);
                startTime = System.currentTimeMillis();
            }
        }
        else if(!isPulsing) {
            if(System.currentTimeMillis()>=startTime + pause) {
                isPulsing = true;
                RobotMap.chuteTalon.set(ControlMode.PercentOutput, speed);
                startTime = System.currentTimeMillis();

            }
        }
    }


}

