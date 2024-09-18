package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Bot extends Component {
//    private final LiftOneMotor lift;

//    private final Servo grabber;
//    private final double grabberClosePosition = 0.0;
//    private final double grabberOpenPosition = 1.0;

    public Bot(HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
//        grabber = hardwareMap.get(Servo.class, "grabber");
        grabberClose();

        //       lift = new LiftOneMotor(hardwareMap, telemetry);
    }

    public void grabberClose() {
//        grabber.setPosition(grabberClosePosition);
    }

    public void grabberOpen() {
//        grabber.setPosition(grabberOpenPosition);
    }

    /*
    public void liftUp(double speed) {
        lift.liftUp(speed);
    }

    public void liftDown(double speed) {
        lift.liftDown(speed);
    }

    public void liftStop() {
        lift.stop();
    }

    public void liftStopAtPosition(int position) {
        lift.stopAtPosition(position);
    }

    public void liftZero() {
        lift.liftZero();
    }
    */
}