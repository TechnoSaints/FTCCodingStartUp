package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.ServoData;

public class ServoSimple extends Component {
    private final double openPosition;
    private final double closePosition;
    private final double middlePosition;
    private final double specimenHangPosition;
    private final double specimenGrabPosition;
    private final double highPosition;
    private final Servo servo;

    public ServoSimple(HardwareMap hardwareMap, Telemetry telemetry, String servoName, ServoData servoData) {
        super(telemetry);
        openPosition = servoData.openPosition;
        closePosition = servoData.closePosition;
        middlePosition = servoData.middlePosition;
        specimenHangPosition = servoData.specimenHangPosition;
        specimenGrabPosition = servoData.specimenGrabPosition;
        highPosition = servoData.highPosition;
        servo = hardwareMap.get(Servo.class, servoName);
    }

    public void open() {
        servo.setPosition(openPosition);
//        log(openPosition);
    }

    public void close() {
        servo.setPosition(closePosition);
//        log(closePosition);
    }

    public void middle() {
        servo.setPosition(middlePosition);
//        log(middlePosition);
    }

    public void specimenHang() {
        servo.setPosition(specimenHangPosition);
//        log(specimenHangPosition);
    }
    public void specimenGrab() {
        servo.setPosition(specimenGrabPosition);
//        log(specimenGrabPosition);
    }
    public void highPosition() {
        servo.setPosition(highPosition);
    }

    private void log(double position)
    {
        telemetry.addData("Target Position: ", position);
        telemetry.update();
    }
}