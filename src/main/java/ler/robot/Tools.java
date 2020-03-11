package ler.robot;

public class Tools {

    final static double DEADZONE = 0.15;

    //v     vv Halil Drive vvv
    /**
   * This function gets the adapted speed from a joystick on an xbox controller or joystick and scales it to drive the drivetrain
   *
   * @param speed speed from the controller
   * @return speed scaled so that it's nice to drive
   */
    public static double getAdaptedSpeed(double speed) {
        // Using a logisitic  function *** FUNCTION HAS BEEN FIXED ***
        //Alot harder to control in mid range but much nicer in low and high range.
        //double out = ((1.01339285092)/(1+Math.pow(Math.E,(-10*(speed-0.5)))))-0.5067;
        //2019 function: nicer in mid range worse control in low and high.
        if (Math.abs(speed)<DEADZONE){
            speed = 0;
        }

        double out = (0.5 * (Math.sin(Math.PI * speed - Math.PI / 2)) + 0.5);
        return out * (speed > 0 ? 1 : -1);
    }

    public static double fitToRange(double value, double min, double max) {
        value = value < min ? min : value;
        value = value > max ? max : value;
        return value;
    }

    public static double setAbsoluteMinimum(double value, double min) {
		value = (value < min && value > 0) ? min : value;
		value = (value > -min && value < 0) ? -min : value;
		return value;
	}
}