package org.firstinspires.ftc.teamcode.common;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.LiftData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.ArmServoData9800;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.GrabberServoData9800;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.LiftDataIntake9800;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.LiftDataOuttake9800;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.OuttakeGrabberServoData9800;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.OuttakeArmServoData9800;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.WristServoData9800;


public abstract class Bot9800 extends Component {
    private final LiftSingle intakeLift, outtakeLift;
    private final ServoSimple grabber, arm, outtakeGrabber, outtakeArm, wrist;

    public Bot9800(HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
        // lift = new LiftSingle(hardwareMap, telemetry, "lift", false, new GoBilda312DcMotorData(), new LiftData21528());
        intakeLift = new LiftSingle(hardwareMap, telemetry, "intakeLift", true, new GoBilda312DcMotorData(), new LiftDataIntake9800());
        outtakeLift = new LiftSingle(hardwareMap, telemetry, "outtakeLift", false, new GoBilda312DcMotorData(), new LiftDataOuttake9800());
        grabber = new ServoSimple(hardwareMap, telemetry, "grabber", new GrabberServoData9800());
        arm = new ServoSimple(hardwareMap, telemetry, "arm", new ArmServoData9800());
        wrist = new ServoSimple(hardwareMap, telemetry, "wrist", new WristServoData9800());
        outtakeGrabber = new ServoSimple(hardwareMap, telemetry, "outtakeGrabber", new OuttakeGrabberServoData9800());
        outtakeArm = new ServoSimple(hardwareMap, telemetry, "outtakeArm", new OuttakeArmServoData9800());
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

    public void wristClose() {
        wrist.close();
    }

    public void wristOpen() {
        wrist.open();
    }

    // public void liftUp(double speed){
    //      lift.up(speed);
    //   }

//    public void liftDown(double speed) {
    //      lift.down(speed);
    //   }

    //   public void liftStop(){
    //     lift.stop();
    //  }

    public void intakeLiftUp(double speed) {
        intakeLift.up(speed);
    }

    public void intakeLiftDown(double speed) {
        intakeLift.down(speed);
    }

    public void intakeLiftStop() {
        intakeLift.stop();
    }

    public void intakeLiftZero() {
        intakeLift.zero();
    }

    public void outtakeLiftUp(double speed) {
        outtakeLift.up(speed);
    }

    public void outtakeLiftDown(double speed) {
        outtakeLift.down(speed);
    }

    public void outtakeLiftZero() {
        outtakeLift.zero();
    }

    public void outtakeLiftStop() {
        outtakeLift.stop();
    }

    public void outtakeGrabberClose() {
        outtakeGrabber.close();
    }

    public void outtakeGrabberOpen() {
        outtakeGrabber.open();
    }

    public void outtakeArmClose() {
        outtakeArm.close();
    }

    public void outtakeArmOpen() {
        outtakeArm.open();
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
