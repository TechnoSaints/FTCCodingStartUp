package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LiftOneMotor extends Component{
    private final DcMotorEx liftMotor;
    protected TouchSensor lift_sensor = null;
    
    private  final double liftMaxVelocity = GoBilda312DcMotorData.maxTicksPerSec;
    private  final double liftMaxMovePower = 0.85;
    private  final double liftStopPower = 0.85;
    private  final int liftMaxPosition = 2250;
    private  final int liftMaxTolerance = 25;
    private  final int liftMinPosition = 0;
    private  final int liftMinTolerance = 25;

    public LiftOneMotor(HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);

        lift_sensor = hardwareMap.get(TouchSensor.class, "liftSensor");
        liftMotor = hardwareMap.get(DcMotorEx.class, "lift");
        liftMotor.setDirection(DcMotor.Direction.REVERSE);
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void stop() {
        stopAtPosition(liftMotor.getCurrentPosition());
    }

    public void liftUp(double targetPower) {
        if (!stoppedAtTop()) {
            liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            liftMotor.setVelocity(targetPower * liftMaxMovePower * liftMaxVelocity);
            telemetry.addData("Stopped at Top: ", "true");
        } else {
            telemetry.addData("Stopped at Top: ", "false");
        }
        telemetry.addData("Position:  ", liftMotor.getCurrentPosition());
        telemetry.update();

    }

    public void liftDown(double targetPower) {
        if (!stoppedAtBottom()) {
            liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            liftMotor.setVelocity(-targetPower * liftMaxMovePower * liftMaxVelocity);
            telemetry.addData("Stopped at Bottom: ", " true");
        } else {
            telemetry.addData("Stopped at Bottom: ", " false");
        }
        telemetry.addData("Position:  ", liftMotor.getCurrentPosition());
        telemetry.update();
    }

    private boolean stoppedAtTop() {
        boolean stop = false;
        int currentPosition = liftMotor.getCurrentPosition();
        if (currentPosition > (liftMaxPosition - liftMaxTolerance)) {
            stop = true;
            stopAtPosition(liftMaxPosition);
        }
        return stop;
    }

    private boolean stoppedAtBottom() {
        boolean stop = false;
        int currentPosition = liftMotor.getCurrentPosition();
        if (currentPosition < (liftMinPosition - liftMinTolerance)) {
            stop = true;
            stopAtPosition(liftMinPosition);
        }
        return stop;
    }

    public void stopAtPosition(int targetPosition) {
        liftMotor.setTargetPosition(targetPosition);
        liftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        liftMotor.setPower(liftStopPower);
    }
    public void liftZero(){
        liftDown(0.2);
        while (!lift_sensor.isPressed()){
        }
        stop();
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
