package frc.robot.Mechanisms.Outake;

// IMPORTACIONES
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Reemplaza con las librerías de tus motores:
// import com.ctre.phoenix6.hardware.TalonFX; // Para Kraken X60 / Falcon 500
// import com.revrobotics.CANSparkMax;        // Para NEO / NEO 550

public class ArmSub extends SubsystemBase {

    // =========================================================================
    // PASO 1: DECLARACIÓN DE HARDWARE (Variables Privadas)
    // =========================================================================
    // Aquí declaras todos los objetos de motores, sensores o pistones.
    // Se definen como 'private final' para que solo este subsistema los controle.
    
    // Ejemplo:
    // private final TalonFX motor;
    // private final DigitalInput sensorLimite;


    // =========================================================================
    // PASO 2: CONSTRUCTOR
    // =========================================================================
    // Se ejecuta UNA SOLA VEZ cuando el robot se enciende.
    // Aquí inicializas los componentes y aplicas configuraciones de fábrica/seguridad.
    
    public ArmSub() {
        // 1. Instanciar el hardware con su ID CAN / Puerto PWM
        // motorPrincipal = new TalonFX(CAN_ID);

        // 2. Configurar el hardware (Límites de corriente, modo freno, inversión)
        // configureMotors();
    }

    // Método auxiliar para mantener limpia la configuración (opcional pero recomendado)
    private void configureMotors() {
        // Aquí aplicas configuraciones de límites de corriente, rampa, etc.
    }


    // =========================================================================
    // PASO 3: MÉTODOS DE ACCIÓN / CONTROL (Interfaz Pública)
    // =========================================================================
    // Estos métodos le dicen a los motores qué hacer.
    // Serán llamados por los COMANDOS o por los botones del control remoto.

    /**
     * Hace girar el motor a un porcentaje de potencia de -1.0 a 1.0
     */
    public void setPower(double speed) {
        // motorPrincipal.set(speed);
    }

    /**
     * Aplica voltaje directo (recomendado para control preciso)
     */
    public void setVoltage(double volts) {
        // motorPrincipal.setVoltage(volts);
    }

    /**
     * Detiene por completo los motores de este subsistema.
     * ESENCIAL para la seguridad del robot.
     */
    public void stop() {
        // motorPrincipal.stopMotor();
    }


    // =========================================================================
    // PASO 4: MÉTODOS DE LECTURA DE SENSORES
    // =========================================================================
    // Devuelven datos útiles de encoders, giróscopos o sensores de distancia.
    // Los comandos usan estos datos para saber cuándo detenerse.

    /**
     * Devuelve la posición actual del motor en rotaciones o metros.
     */
    public double getPosition() {
        // return motorPrincipal.getPosition().getValueAsDouble();
        return 0.0; 
    }

    /**
     * Devuelve si se activó un límite de seguridad o sensor de presencia.
     */
    public boolean isLimitReached() {
        // return !sensorLimite.get();
        return false;
    }


    // =========================================================================
    // PASO 5: MÉTODOS DE FÁBRICA DE COMANDOS (Command Factories - Estilo Moderno)
    // =========================================================================
    // WPILib moderno permite crear comandos simples directamente desde el subsistema.
    
    /**
     * Crea un comando para encender el subsistema a una velocidad fija y detenerlo al soltar.
     */
    public edu.wpi.first.wpilibj2.command.Command runIntakeCommand(double speed) {
        return this.runEnd(
            () -> setPower(speed), // Lo que hace mientras el comando está activo
            () -> stop()           // Lo que hace cuando el comando termina
        );
    }


    // =========================================================================
    // PASO 6: EL MÉTODO PERIÓDICO (Telemetry & Diagnostics)
    // =========================================================================
    // Se ejecuta automáticamente en bucle cada 20ms (~50 veces por segundo).
    // NUNCA pongas delays ni bucles mientras/for largos aquí.
    
    @Override
    public void periodic() {
        // Enviar datos útiles al SmartDashboard para la Driver Station
        SmartDashboard.putNumber("NombreSubsistema/Posición", getPosition());
        SmartDashboard.putBoolean("NombreSubsistema/Limite", isLimitReached());
    }
}