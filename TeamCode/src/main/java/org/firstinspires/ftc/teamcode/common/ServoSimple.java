package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.ServoData;

public class ServoSimple extends Component {
    private final double openPosition;
    private final double closePosition;
    private final Servo servo;

    public ServoSimple(HardwareMap hardwareMap, Telemetry telemetry, String servoName, ServoData servoData)
    {
        super(telemetry);
        openPosition = servoData.openPosition;
        closePosition = servoData.closePosition;
        servo = hardwareMap.get(Servo.class, servoName);
    }

    public void open()
    {
        servo.setPosition(openPosition);
    }
    public void close()
    {
        servo.setPosition(closePosition);
    }
}