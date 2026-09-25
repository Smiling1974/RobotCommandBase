package frc.robot.Mechanisms;

import java.io.ObjectInputFilter.Config;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Mechanisms.MechanismsConstants.OutakeConstants;

public class OutakeSub extends SubsystemBase {

    private final TalonFX outakeMotor = new TalonFX(OutakeConstants.OutakeId);


    //faltan las constantes
    public OutakeSub() {
        TalonFXConfiguration OutakeMotorConfig = new TalonFXConfiguration();
        OutakeMotorConfig.MotorOutput.Inverted = OutakeConstants.OutakeInversion;
        OutakeMotorConfig.Slot0.kP = OutakeConstants.kP;
        OutakeMotorConfig.Slot0.kI = OutakeConstants.kI;
        OutakeMotorConfig.Slot0.kD = OutakeConstants.kD;   
        OutakeMotorConfig.Slot0.kS = OutakeConstants.kS;
        OutakeMotorConfig.Slot0.kG = OutakeConstants.kG;
        OutakeMotorConfig.Slot0.kV = OutakeConstants.kV;
        OutakeMotorConfig.Slot0.kA = OutakeConstants.kA;
        outakeMotor.getConfigurator().apply(OutakeMotorConfig);
        //checar applyConfig, actuators?

    }

    public void setOutakeSpeed(double speed) {
        outakeMotor.set(speed);
    }
    public void applyOutakeVoltage(double voltage) {
        outakeMotor.setVoltage(voltage);
    }
    public void stopOutake() {
        outakeMotor.set(0);
    }
    @Override
    public void periodic() {
    }
}
