package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
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
    private double targetVelocity;
    private int highPosition;
    private int mediumPosition;
    private int lowPosition;
    private int chamberPosition;
    private int releasePosition;
    private int highBucketPosition;
    private int grabPosition;

    public LiftSingle(HardwareMap hardwareMap, Telemetry telemetry, String motorName, boolean reverseMotor, MotorData motorData, LiftData liftData) {
        super(telemetry);
        maxVelocity = motorData.maxTicksPerSec;
        maxMovePower = liftData.maxMovePower;
        stopPower = liftData.stopPower;
        maxPosition = liftData.maxPosition;
        maxTolerance = liftData.maxTolerance;
        minPosition = liftData.minPosition;
        minTolerance = liftData.minTolerance;
        highPosition = liftData.highPosition;
        mediumPosition = liftData.mediumPosition;
        lowPosition = liftData.lowPosition;
        chamberPosition = liftData.chamberPosition;
        releasePosition = liftData.releasePosition;
        highBucketPosition = liftData.highBucketPosition;
        grabPosition = liftData.grabPosition;
        long prevTime;
        int prevPosition;
//        lift_sensor = hardwareMap.get(TouchSensor.class, "liftSensor");
        motor = hardwareMap.get(DcMotorEx.class, motorName);
//        encoder = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, motorName)));


        if (reverseMotor) {
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
            targetVelocity = targetPower * maxMovePower * maxVelocity;
            motor.setVelocity(targetVelocity);
            telemetry.addData("Stopped at Top: ", "true");
        } else {
            telemetry.addData("Stopped at Top: ", "false");
        }
        log();
    }

    public void down(double targetPower) {
        if (!stoppedAtBottom()) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            targetVelocity = -targetPower * maxMovePower * maxVelocity;
            motor.setVelocity(targetVelocity);
            telemetry.addData("Stopped at Bottom: ", " true");
        } else {
            telemetry.addData("Stopped at Bottom: ", " false");
        }
        log();
    }

    public void highPosition() {
        stopAtPosition(highPosition);
        log();
    }

    public void mediumPosition() {
        stopAtPosition(mediumPosition);
        log();
    }

    public void lowPosition() {
        stopAtPosition(lowPosition);
        log();
    }

    public void minPosition() {
        stopAtPosition(minPosition);
        log();
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

    private void stopAtPosition(int targetPosition) {
        motor.setTargetPosition(targetPosition);
        motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        motor.setPower(stopPower);
        log();
    }

    public void zero() {
//        liftDown(0.2);
//        while (!lift_sensor.isPressed()){
//        }
//        stop();
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        log();
    }

    public void log() {
//        prevTime = timer.milliseconds();
//        prevPosition = motor.getCurrentPosition();
        telemetry.addData("Position:  ", motor.getCurrentPosition());
//        telemetry.addData("targetVelocity: ", targetVelocity);
//        opMode.sleep(1000);
//        telemetry.addData("Velocity: ", ((motor.getCurrentPosition() - prevPosition) * 1000) / (timer.milliseconds() - prevTime));
        telemetry.update();
    }

    public void chamberPosition() {
        stopAtPosition(chamberPosition);
        log();
    }

    public void releasePosition() {
        stopAtPosition(releasePosition);
        log();
    }

    public void highBucketPosition() {
        stopAtPosition(highBucketPosition);
        log();
    }

    public void grabPosition() {
        stopAtPosition(grabPosition);
        log();
    }

}




