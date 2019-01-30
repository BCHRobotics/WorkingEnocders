package frc.robot;

public class RobotMap {

    //RobotMap holds all the global variables for where things are plugged in


    //MOTORS

    //DriveTrain
    public static int TALONLEFT1 = 11;
    public static int TALONLEFT2 = 12;
    public static int TALONLEFT3 = 13;

    public static int TALONRIGHT1 = 15;
    public static int TALONRIGHT2 = 16;
    public static int TALONRIGHT3 = 17;

    //Lift
    public static int TALONLIFT = 31;

    /********************************* */

    //CONTROLLERS
    public static int OI_DRIVESTICK = 0;
    public static int OI_FUNSTICK = 1;

    //DriveStick AXIS
    public static int OI_DRIVESTICK_MOVE = 1;
    public static int OI_DRIVESTICK_ROTATE = 4;
    public static int OI_FUNSTICK_LIFT = 1; 

    //DirveStick BUTTONS
    public static int OI_FUNSTICK_GrabOpen = 1; 
    public static int OI_FUNSTICK_GrabClose = 2; 
    public static int OI_DRIVESTICK_Snail = 5; 
    public static int OI_DRIVESTICK_Turbo = 6; 
    public static int OI_FUNSTICK_LiftEnable = 5;

    /********************************* */

    //PHENUMATICS
    public static int GRABBER_SOLENOID_OPEN = 0;
    public static int GRABBER_SOLENOID_CLOSE = 1;


}