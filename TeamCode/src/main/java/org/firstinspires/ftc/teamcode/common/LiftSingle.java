package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.MotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.LiftData;

public class LiftSingle extends Component {
    private final DcMotorEx motor;
//    protected TouchSensor lift_sensor = null;

    private final double maxVelocity;
    private final double maxMovePower;
    private final double stopPower;
    private final int maxPosition;
    private final int maxTolerance;
    private final int minPosition;
    private final int minTolerance;

    public LiftSingle(HardwareMap hardwareMap, Telemetry telemetry, String motorName, boolean reverseMotor, MotorData motorData, LiftData liftData) {
        super(telemetry);
        maxVelocity = motorData.maxTicksPerSec;
        maxMovePower = liftData.maxMovePower;
        stopPower = liftData.stopPower;
        maxPosition = liftData.maxPosition;
        maxTolerance = liftData.maxTolerance;
        minPosition = liftData.minPosition;
        minTolerance = liftData.minTolerance;

//        lift_sensor = hardwareMap.get(TouchSensor.class, "liftSensor");
        motor = hardwareMap.get(DcMotorEx.class, motorName);
        if (reverseMotor)
        {
            motor.setDirection(DcMotor.Direction.REVERSE);
        }
        zero();
    }

    public void stop() {
        stopAtPosition(motor.getCurrentPosition());
    }

    public void up(double targetPower) {
        if (!stoppedAtTop()) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setVelocity(targetPower * maxMovePower * maxVelocity);
            telemetry.addData("Stopped at Top: ", "true");
        } else {
            telemetry.addData("Stopped at Top: ", "false");
        }
        telemetry.addData("Position:  ", motor.getCurrentPosition());
        telemetry.update();

    }

    public void down(double targetPower) {
        if (!stoppedAtBottom()) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setVelocity(-targetPower * maxMovePower * maxVelocity);
            telemetry.addData("Stopped at Bottom: ", " true");
        } else {
            telemetry.addData("Stopped at Bottom: ", " false");
        }
        telemetry.addData("Position:  ", motor.getCurrentPosition());
        telemetry.update();
    }

    private boolean stoppedAtTop() {
        boolean stop = false;
        int currentPosition = motor.getCurrentPosition();
        if (currentPosition > (maxPosition - maxTolerance)) {
            stop = true;
            stopAtPosition(maxPosition);
        }
        return stop;
    }

    private boolean stoppedAtBottom() {
        boolean stop = false;
        int currentPosition = motor.getCurrentPosition();
        if (currentPosition < (minPosition - minTolerance)) {
            stop = true;
            stopAtPosition(minPosition);
        }
        return stop;
    }

    public void stopAtPosition(int targetPosition) {
        motor.setTargetPosition(targetPosition);
        motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        motor.setPower(stopPower);
    }

    public void zero() {
//        liftDown(0.2);
//        while (!lift_sensor.isPressed()){
//        }
//        stop();
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
