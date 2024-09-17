/*package org.firstinspires.ftc.teamcode.common;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.Linear
import com.qualcomm.robotcore.eventloop.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda312DcMotorData;


@Config
@TeleOp(name = "liftTest", group = "Linear )

public class LiftTest extends Linear{

    private final DcMotorEx liftMotor;
    private final double liftMaxMoveSpeed = 0.25;
    private final double liftMaxMoveVelocity = 
    private final double liftStopPowerFactor = 0.25;
    private final int liftMinPosition = 250;
    private final int liftMaxPosition = 1250;


    public static final double liftMaxVelocity = GoBilda312DcMotorData.maxCountsPerSec;
    public static final double liftMaxMoveSpeed = 1.0;
    public static final double liftStopPowerFactor = 1.0;
    public static final int liftMaxPosition = 2250;
    public static final int liftMaxTolerance = 25;
    public static final int liftMinPosition = 0;
    public static final int liftMinTolerance = 25;
    public static final int liftAutoBoardProbePosition = 1750;
    public static final int liftAutoBoardPlacementPosition = 800;
    public static final int liftAutoHighCruisingPosition = 550;
    public static final int liftAutoLowCruisingPosition = 100;

    LiftTest(){
        liftMotor = hardwareMap.get(DcMotorEx.class, "lift");
        liftMotor.setDirection(DcMotor.Direction.REVERSE);
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void stop() {
        stopAtPosition(liftMotor.getCurrentPosition());
    }

    public void liftUp(double targetSpeed) {
        if (!stoppedAtTop()) {
            liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            liftMotor.setVelocity(targetSpeed * liftMaxMoveSpeed * liftMaxVelocity);
            telemetry.addData("Stopped at Top: ", "true");
        } else {
            telemetry.addData("Stopped at Top: ", "false");
        }
        telemetry.addData("Position:  ", liftMotor.getCurrentPosition());
        telemetry.update();
    }

    public void liftDown(double targetSpeed) {
        if (!stoppedAtBottom()) {
            liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            liftMotor.setVelocity(-targetSpeed * liftMaxMoveSpeed * liftMaxVelocity);
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
        if (currentPosition > (Constants.liftMaxPosition - Constants.liftMaxTolerance)) {
            stop = true;
            stopAtPosition(Constants.liftMaxPosition);
        }
        return stop;
    }

    private boolean stoppedAtBottom() {
        boolean stop = false;
        int currentPosition = liftMotor.getCurrentPosition();
        if (currentPosition < (liftMinPosition - CliftMinTolerance)) {
            stop = true;
            stopAtPosition(liftMinPosition);
        }
        return stop;
    }

    public void stopAtPosition(int targetPosition) {
        liftMotor.setTargetPosition(targetPosition);
        liftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        liftMotor.setPower(liftStopPowerFactor);
    }
    public void liftZero(){
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
*/