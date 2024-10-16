package org.firstinspires.ftc.teamcode.common;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda117DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.team26290.ArmServoData26290;
import org.firstinspires.ftc.teamcode.common.hardware_data.team26290.GrabberServoData26290;
import org.firstinspires.ftc.teamcode.common.hardware_data.team26290.LiftData26290;
import org.firstinspires.ftc.teamcode.common.hardware_data.team26290.HookData26290;

public abstract class Bot26290 extends Component {
    private final LiftSingle lift;
    private final ServoSimple grabber;
    private final ServoSimple arm;
    private final LiftSingle hook;

    public Bot26290(HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
        lift = new LiftSingle(hardwareMap, telemetry, "lift", true, new GoBilda312DcMotorData(), new LiftData26290());
        hook = new LiftSingle(hardwareMap, telemetry, "hook", true, new GoBilda117DcMotorData(), new HookData26290());
        grabber = new ServoSimple(hardwareMap, telemetry, "grabber", new GrabberServoData26290());
        arm = new ServoSimple(hardwareMap, telemetry, "arm", new ArmServoData26290());
        grabberClose();
    }

    public void grabberClose() {
        grabber.close();
    }

    public void grabberOpen() {
        grabber.open();
    }

    public void armClose() {
        arm.close();
    }

    public void armOpen() {
        arm.open();
    }

    public void liftUp(double speed) {
        lift.up(speed);
    }

    public void liftDown(double speed) {
        lift.down(speed);
    }

    public void liftStop() {
        lift.stop();
    }

    public void hookUp(double speed){
        hook.up(speed);
    }

    public void hookDown(double speed){
        hook.down(speed);
    }

    public void hookStop() {
        hook.stop();
    }

    public void liftZero() {
        lift.zero();
    }
    public void hookZero() {
        hook.zero();
    }

    // Action classes and methods required to use scheduler
    // Intended for use in auto opmodes, but could be used in teleop
    public class OpenGrabber implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            telemetry.addData("Grabber Opening...", 1);
            telemetry.update();
            return false;
        }
    }

    public Action openGrabber() {
        return new OpenGrabber();
    }

    public class CloseGrabber implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            telemetry.addData("Grabber Closing...", 1);
            telemetry.update();
            return false;
        }
    }

    public Action closeGrabber() {
        return new CloseGrabber();
    }

    public class LiftToBottomPosition implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            telemetry.addData("Lift Moving to Bottom Position...", 1);
            telemetry.update();
            return false;
        }
    }

    public Action liftToBottomPosition() {
        return new LiftToBottomPosition();
    }

    public class Shutdown implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            telemetry.addData("Shutting Bot Down...", 1);
            telemetry.update();
            return false;
        }
    }

    public Action shutdown() {
        return new Shutdown();
    }
}
