package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.LiftData;
import org.firstinspires.ftc.teamcode.common.hardware_data.MotorData;

public class LiftSingleNoSensor extends Component {
    private final DcMotorEx motor;
    //private TouchSensor liftSwitch;
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
    private int direction = 1;
    private String liftName;

    public LiftSingleNoSensor(HardwareMap hardwareMap, Telemetry telemetry, String motorName, boolean reverseMotor, MotorData motorData, LiftData liftData) {
        super(telemetry);
        liftName = motorName;
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
        long prevTime;
        int prevPosition;
        //liftSwitch = hardwareMap.get(TouchSensor.class, "armSwitch");
        motor = hardwareMap.get(DcMotorEx.class, motorName);

        if (reverseMotor) {
            direction = -1;
//            motor.setDirection(DcMotor.Direction.REVERSE);
        } else {
//            motor.setDirection(DcMotorSimple.Direction.FORWARD);
            direction = 1;
        }

        zero();
    }

    public void stop(boolean down) {
        stopAtPosition(motor.getCurrentPosition(), down);
    }

    public void up(double targetPower, boolean down) {
        if (!stoppedAtTop(down)) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            targetVelocity = direction * targetPower * maxMovePower * maxVelocity;
//            motor.setPower(targetPower);
            motor.setVelocity(targetVelocity);
            telemetry.addData("Stopped at Top: ", "false");
        } else {
            telemetry.addData("Stopped at Top: ", "true");
        }
        log();
    }

    public void down(double targetPower) {
        if (!stoppedAtBottom()) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            targetVelocity = direction * -targetPower * maxMovePower * maxVelocity;
//            motor.setPower(targetPower);
            motor.setVelocity(targetVelocity);
            telemetry.addData("Stopped at Bottom: ", " false");
        } else {
            telemetry.addData("Stopped at Bottom: ", " true");
        }
        log();
    }

    public void highPosition() {
        stopAtPosition(highPosition, false);
        log();
    }

    public void mediumPosition() {
        stopAtPosition(mediumPosition, false);
        log();
    }

    public void lowPosition() {
        stopAtPosition(lowPosition, false);
        log();
    }

    public void minPosition() {
        stopAtPosition(minPosition, false);
        log();
    }

    private boolean stoppedAtTop(boolean down) {
        boolean stop = false;
        int currentPosition = motor.getCurrentPosition();
        int upPosition = maxPosition;
        if (down){
            upPosition = highPosition;
        }
        if (currentPosition > (upPosition - maxTolerance)) {
            stop = true;
            stopAtPosition(upPosition, false);
        }
        return stop;
    }

    private boolean stoppedAtBottom() {
        boolean stop = false;
        int currentPosition = motor.getCurrentPosition();
        if (currentPosition < (minPosition - minTolerance)) {
            stop = true;
            stopAtPosition(minPosition, false);
        }
        return stop;
    }

    private void stopAtPosition(int targetPosition, boolean checkForStop) {
        if (checkForStop && motor.getCurrentPosition() < highPosition){
            targetPosition = highPosition;
        }
        motor.setTargetPosition(targetPosition);
        motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        motor.setPower(stopPower);
        log();
    }

    public void zero() {
        /*down(0.2);
        while (!liftSwitch.isPressed()){
        }
        stop();*/
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        log();
    }

    public void log() {
//        prevTime = timer.milliseconds();
//        prevPosition = motor.getCurrentPosition();
        telemetry.addData(liftName + "Position:  ", motor.getCurrentPosition());
//        telemetry.addData("targetVelocity: ", targetVelocity);
//        opMode.sleep(1000);
//        telemetry.addData("Velocity: ", ((motor.getCurrentPosition() - prevPosition) * 1000) / (timer.milliseconds() - prevTime));
        telemetry.update();
    }
}