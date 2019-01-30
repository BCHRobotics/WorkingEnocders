# WorkingEnocders

New Funtions you can call

This resets the ADXRS450 gyro, called in disabledInit() and teleopInit():   
  m_gyro.reset()

This resets the NavX gyro, called in disabledInit() and teleopInit():   
  ahrs.reset()

This resets the Encoder:    
  Robot.m_drivetrain.resetEncoder()

This gets the value of the encoder in inches:   
  Robot.m_drivetrain.getEncoder()

This gets the ADXRS450 gyro angle in degrees:   
  m_gyro.getAngle()

This gets the NavX gyro angle in degrees:   
  ahrs.getAngle()
