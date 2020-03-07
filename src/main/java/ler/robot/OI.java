/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

import ler.robot.RobotMap;
import edu.wpi.first.hal.sim.DriverStationSim;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import ler.robot.commands.*;
import ler.robot.commands.instant.*;

import ler.robot.subsystems.Drivetrain;
import ler.robot.subsystems.Climber;
import ler.robot.subsystems.Intake;
import ler.robot.subsystems.Chute;
import ler.robot.subsystems.Hopper;


/**
 * Operator Interface, used to map buttons with the controllers
 */
public class OI {

    // The driver's controller
    public XboxController driverController = new XboxController(RobotMap.OIConstants.DRIVER_CONTROLLER_PORT);
    public XboxController operatorController = new XboxController(RobotMap.OIConstants.OPERATOR_CONTROLLER_PORT);

    public JoystickButton moveTurretJoystick = new JoystickButton(operatorController, RobotMap.OIConstants.LEFT_JOYSTICK_X);
    public JoystickButton halfSpeedButton = new JoystickButton(driverController, RobotMap.OIConstants.HALF_SPEED_BUTTON);
    public JoystickButton aimbotButton = new JoystickButton(operatorController, RobotMap.OIConstants.AIMBOT_BUTTON);
    public JoystickButton intakeHeightButton = new JoystickButton(operatorController, RobotMap.OIConstants.INTAKE_HEIGHT_BUTTON);
    public JoystickButton intakeButton = new JoystickButton(operatorController, RobotMap.OIConstants.INTAKE_BUTTON);

    //Create intake trigger here 

    public JoystickButton climberToggleButton = new JoystickButton(operatorController, RobotMap.OIConstants.CLIMBER_TOGGLE_BUTTON);
    public JoystickButton winchControlButton = new JoystickButton(operatorController, RobotMap.OIConstants.WINCH_CONTROL_BUTTON);

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    public void init(RobotContainer container) {
        /*
         * // Grab the hatch when the 'A' button is pressed. new
         * JoystickButton(m_driverController, Button.kA.value) .whenPressed(new
         * GrabHatch(m_hatchSubsystem)); // Release the hatch when the 'B' button is
         * pressed. new JoystickButton(m_driverController, Button.kB.value)
         * .whenPressed(new ReleaseHatch(m_hatchSubsystem));
         */


        // While holding the shoulder button, drive at half speed
        
        halfSpeedButton.whenHeld(new HalveDriveSpeed(container.drivetrain));
        aimbotButton.whenHeld(new TurretAimCommand(container.turret));

        climberToggleButton.whenPressed(new ClimberCommand(container.climber));
        winchControlButton.whenHeld(new ClimberWinchCommand(container.climber));  
        
        intakeHeightButton.whenReleased(new IntakeArmCommand(container.intake));
        intakeButton.whileHeld(new IntakeCommand(container.intake, container.hopper, container.chute));

        //moveTurretJoystick.whenPressed(new TurretMoveCommand(container.turret));

    }

    public double getLeftInput(){
        return driverController.getY(Hand.kLeft);
    }

    public double getRightInput(){
        return driverController.getY(Hand.kRight);

    }

}
