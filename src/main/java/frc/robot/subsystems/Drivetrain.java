package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Drivetrain extends Subsystem{

    //How much we have to divde our encoders to get inches
    double encoderCal = 0.54;

    //Init talons
    TalonSRX TALONLEFT1 = new TalonSRX(RobotMap.TALONLEFT1);
    TalonSRX TALONLEFT2 = new TalonSRX(RobotMap.TALONLEFT2);
    TalonSRX TALONLEFT3 = new TalonSRX(RobotMap.TALONLEFT3);

    TalonSRX TALONRIGHT1 = new TalonSRX(RobotMap.TALONRIGHT1);
    TalonSRX TALONRIGHT2 = new TalonSRX(RobotMap.TALONRIGHT2);
    TalonSRX TALONRIGHT3 = new TalonSRX(RobotMap.TALONRIGHT3);

    final int kTimeoutMs = 30;
    final boolean kDiscontinuityPresent = true;
	final int kBookEnd_0 = 910;		/* 80 deg */
	final int kBookEnd_1 = 1137;	/* 100 deg */

    public Drivetrain(){

        //Set ramp rates
        double rampRate = 0.5;

        TALONRIGHT2.configFactoryDefault();

        initQuadrature();

        TALONLEFT1.configOpenloopRamp(rampRate);
        TALONLEFT2.configOpenloopRamp(rampRate);
        TALONLEFT3.configOpenloopRamp(rampRate);

        TALONRIGHT1.configOpenloopRamp(rampRate);
        TALONRIGHT2.configOpenloopRamp(rampRate);
        TALONRIGHT3.configOpenloopRamp(rampRate);

        //Follow other motors
        TALONLEFT2.follow(TALONLEFT1);
        TALONLEFT3.follow(TALONLEFT1);

        TALONRIGHT2.follow(TALONRIGHT1);
        TALONRIGHT3.follow(TALONRIGHT1);

        TALONRIGHT2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);

    }

    public void arcadeDrive(double moveSpeed, double rotateSpeed) {
        

		TALONLEFT1.set(ControlMode.PercentOutput, (rotateSpeed + moveSpeed));
        TALONRIGHT1.set(ControlMode.PercentOutput, ((rotateSpeed) - moveSpeed));
        
        /*
        TALONLEFT2.set(ControlMode.PercentOutput, (rotateSpeed + moveSpeed));
        TALONRIGHT2.set(ControlMode.PercentOutput, ((rotateSpeed) - moveSpeed));
        
        TALONLEFT3.set(ControlMode.PercentOutput, (rotateSpeed + moveSpeed));
		TALONRIGHT3.set(ControlMode.PercentOutput, ((rotateSpeed) - moveSpeed));
		*/
    }

    public void resetEncoder(){
        TALONRIGHT2.setSelectedSensorPosition(0, 0, this.kTimeoutMs);
    }
    
    public double getEncoder(){
    
        double encoderMove = TALONRIGHT2.getSelectedSensorPosition(0);
        encoderMove = encoderMove * encoderCal;

       return encoderMove;
    }

    /*
    public double getEncoderLeft(){
    
        double encoderMove = this.TALONRIGHT2.getSelectedSensorVelocity();
        encoderMove = encoderMove * encoderCal;

       return encoderMove;
    }*/

    public void initQuadrature() {
		/* get the absolute pulse width position */
		int pulseWidth = TALONRIGHT2.getSensorCollection().getPulseWidthPosition();

		/**
		 * If there is a discontinuity in our measured range, subtract one half
		 * rotation to remove it
		 */
		if (kDiscontinuityPresent) {

			/* Calculate the center */
			int newCenter;
			newCenter = (kBookEnd_0 + kBookEnd_1) / 2;
			newCenter &= 0xFFF;

			/**
			 * Apply the offset so the discontinuity is in the unused portion of
			 * the sensor
			 */
			pulseWidth -= newCenter;
		}

		/**
		 * Mask out the bottom 12 bits to normalize to [0,4095],
		 * or in other words, to stay within [0,360) degrees 
		 */
		pulseWidth = pulseWidth & 0xFFF;

		/* Update Quadrature position */
		TALONRIGHT2.getSensorCollection().setQuadraturePosition(pulseWidth, kTimeoutMs);
	}

    @Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//setDefaultCommand(new DriveArcade());
	}

}