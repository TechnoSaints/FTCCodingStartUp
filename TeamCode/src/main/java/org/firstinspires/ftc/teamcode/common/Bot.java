package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Bot extends Component {
//    private final LiftOneMotor lift;

    public Bot(HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
 //       lift = new LiftOneMotor(hardwareMap, telemetry);
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