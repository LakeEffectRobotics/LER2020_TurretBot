/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ler.robot;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

import edu.wpi.first.hal.sim.DriverStationSim;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import ler.robot.commands.HalveDriveSpeed;
import ler.robot.commands.TurretMoveCommand;
import ler.robot.commands.TurretAimCommand;
import ler.robot.subsystems.Drivetrain;

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
        //moveTurretJoystick.whenPressed(new TurretMoveCommand(container.turret));

    }

}
