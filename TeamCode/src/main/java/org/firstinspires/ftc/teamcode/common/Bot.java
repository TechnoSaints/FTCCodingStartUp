package org.firstinspires.ftc.teamcode.common;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.ArmServoData9800;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.GrabberServoData9800;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.LiftData9800;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.OuttakeGrabberServoData9800;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.OuttakeWristServoData9800;

public abstract class Bot extends Component {
    private final LiftSingle lift;
    private final ServoSimple grabber, arm, outtakeGrabber, outtakeWrist;

    public Bot(HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
        lift = new LiftSingle(hardwareMap, telemetry, "lift", false, new GoBilda312DcMotorData(), new LiftData9800());
        grabber = new ServoSimple(hardwareMap, telemetry, "grabber", new GrabberServoData9800());
        arm = new ServoSimple(hardwareMap, telemetry, "arm", new ArmServoData9800());
        outtakeGrabber = new ServoSimple(hardwareMap, telemetry, "outtakeGrabber", new OuttakeGrabberServoData9800());
        outtakeWrist = new ServoSimple(hardwareMap, telemetry, "outtakeWrist", new OuttakeWristServoData9800());
        grabberClose();
        armOpen();
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

    public void liftZero() {
        lift.zero();
    }

    public void outtakeGrabberClose(){
        outtakeGrabber.close();
    }

    public void outtakeGrabberOpen(){
        outtakeGrabber.open();
    }

    public void outtakeWristClose(){
        outtakeWrist.close();
    }

    public void outtakeWristOpen(){
        outtakeWrist.open();
    }

    // Action classes and methods required to use scheduler
    // Intended for use in auto opmodes, but could be used in teleop
    public class OpenGrabber implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            telemetry.addData("Grabber Opening...",1);
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
            telemetry.addData("Grabber Closing...",1);
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
            telemetry.addData("Lift Moving to Bottom Position...",1);
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
            telemetry.addData("Shutting Bot Down...",1);
            telemetry.update();
            return false;
        }
    }
    public Action shutdown() {
        return new Shutdown();
    }
}
