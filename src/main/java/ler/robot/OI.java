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
import edu.wpi.first.wpilibj.XboxController.Axis;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
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
    public static boolean XBOX_DRIVE = true;
    public static final int DRIVER_LEFT_JOYSTICK_PORT = 0;
    public static final int DRIVER_RIGHT_JOYSTICK_PORT = 1;
    public static final int DRIVER_CONTROLLER_PORT = 1;
    public static final int OPERATOR_CONTROLLER_PORT = 2;

    public static final class Mappings {
        
        public static final int HALF_SPEED_BUTTON = Button.kBumperRight.value;
        public static final int GYRO_BUTTON = Axis.kRightTrigger.value;
        public static final int INVERT_BUTTON = Button.kY.value;

        public static final int XBOX_HALF_SPEED_BUTTON = Button.kBumperRight.value;
        public static final int XBOX_GYRO_BUTTON = Axis.kRightTrigger.value;
        public static final int XBOX_INVERT_BUTTON = Button.kY.value;

        public static final int LEFT_JOYSTICK_X = Axis.kLeftX.value;
    
        public static final int INTAKE_BUTTON = Button.kB.value;
        public static final int INTAKE_HEIGHT_BUTTON = Button.kStickRight.value;
    
        public static final int AIMBOT_BUTTON = Button.kBumperRight.value;
    
        public static final int CLIMBER_TOGGLE_BUTTON = Button.kStart.value;
        public static final int WINCH_CONTROL_BUTTON = Button.kBack.value;
    
      }

    // The driver's controller
    public XboxController driverController = new XboxController(DRIVER_CONTROLLER_PORT);
    public Joystick driverLeftJoystick = new Joystick(DRIVER_LEFT_JOYSTICK_PORT);
    public Joystick driverRightJoystick = new Joystick(DRIVER_RIGHT_JOYSTICK_PORT);
    public XboxController operatorController = new XboxController(OPERATOR_CONTROLLER_PORT);

    //configure which joysticks these are on as the driveteam wants
    public JoystickButton gyroButton = new JoystickButton(driverLeftJoystick, Mappings.GYRO_BUTTON);
    public JoystickButton halfSpeedButton = new JoystickButton(driverLeftJoystick, Mappings.HALF_SPEED_BUTTON);
    public JoystickButton invertButton = new JoystickButton(driverRightJoystick, Mappings.INVERT_BUTTON);

    public JoystickButton gyroXboxButton = new JoystickButton(driverController, Mappings.XBOX_GYRO_BUTTON);
    public JoystickButton halfSpeedXboxButton = new JoystickButton(driverController, Mappings.XBOX_HALF_SPEED_BUTTON);
    public JoystickButton invertXboxButton = new JoystickButton(driverController, Mappings.XBOX_INVERT_BUTTON);

    public JoystickButton moveTurretJoystick = new JoystickButton(operatorController, Mappings.LEFT_JOYSTICK_X);
    public JoystickButton aimbotButton = new JoystickButton(operatorController, Mappings.AIMBOT_BUTTON);
    public JoystickButton intakeHeightButton = new JoystickButton(operatorController, Mappings.INTAKE_HEIGHT_BUTTON);
    //Create intake trigger here 
    public JoystickButton intakeButton = new JoystickButton(operatorController, Mappings.INTAKE_BUTTON);


    public JoystickButton climberToggleButton = new JoystickButton(operatorController, Mappings.CLIMBER_TOGGLE_BUTTON);
    public JoystickButton winchControlButton = new JoystickButton(operatorController, Mappings.WINCH_CONTROL_BUTTON);

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
        if(XBOX_DRIVE){
            halfSpeedButton = halfSpeedXboxButton;
            gyroButton = gyroXboxButton;
            invertButton = invertXboxButton;
        }

        halfSpeedButton.whenHeld(new HalveDriveSpeed(container.drivetrain));
        gyroButton.whenHeld(new GyroDriveCommand(container.drivetrain, container.gyro));
        invertButton.whenPressed(new InvertControlsCommand(container.drivetrain));


        aimbotButton.whenHeld(new TurretAimCommand(container.turret));

        climberToggleButton.whenPressed(new ClimberCommand(container.climber));
        winchControlButton.whenHeld(new ClimberWinchCommand(container.climber));  
        
        intakeHeightButton.whenReleased(new IntakeArmCommand(container.intake));
        intakeButton.whenHeld(new IntakeCommand(container.intake, container.hopper, container.chute));

        //moveTurretJoystick.whenPressed(new TurretMoveCommand(container.turret));

    }

    public double getLeftInput(){
        if(XBOX_DRIVE){
            return driverLeftJoystick.getY();
        }else{
            return driverController.getY(Hand.kLeft);
        }
        
    }

    public double getRightInput(){
        if(XBOX_DRIVE){
            return driverRightJoystick.getY();
        }else{
            return driverController.getY(Hand.kRight);
        }

    }

}
