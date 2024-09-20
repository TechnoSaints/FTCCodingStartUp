package org.firstinspires.ftc.teamcode.common;

import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
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
//    private final OverflowEncoder encoder;
//    protected TouchSensor lift_sensor = null;

    private final double maxVelocity;
    private final double maxMovePower;
    private final double stopPower;
    private final int maxPosition;
    private final int maxTolerance;
    private final int minPosition;
    private final int minTolerance;
    private double targetVelocity;
    private ElapsedTime timer = new ElapsedTime();
    private double prevTime;
    private double prevPosition;
    private final LinearOpMode opMode;

    public LiftSingle(LinearOpMode opMode, HardwareMap hardwareMap, Telemetry telemetry, String motorName, boolean reverseMotor, boolean reverseEncoder, MotorData motorData, LiftData liftData)
    {
        super(telemetry);
        this.opMode = opMode;
        maxVelocity = motorData.maxTicksPerSec;
        maxMovePower = liftData.maxMovePower;
        stopPower = liftData.stopPower;
        maxPosition = liftData.maxPosition;
        maxTolerance = liftData.maxTolerance;
        minPosition = liftData.minPosition;
        minTolerance = liftData.minTolerance;
        long prevTime;
        int prevPosition;
//        lift_sensor = hardwareMap.get(TouchSensor.class, "liftSensor");
        motor = hardwareMap.get(DcMotorEx.class, motorName);
//        encoder = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, motorName)));


        if (reverseMotor) {
            motor.setDirection(DcMotor.Direction.REVERSE);
        }
        if (reverseEncoder)
        {
//            encoder.setDirection(DcMotorSimple.Direction.REVERSE);
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

    public void log()
    {
//        prevTime = timer.milliseconds();
//        prevPosition = motor.getCurrentPosition();
        telemetry.addData("Position:  ", motor.getCurrentPosition());
//        telemetry.addData("targetVelocity: ", targetVelocity);
//        opMode.sleep(1000);
//        telemetry.addData("Velocity: ", ((motor.getCurrentPosition() - prevPosition) * 1000) / (timer.milliseconds() - prevTime));
        telemetry.update();
    }
}
