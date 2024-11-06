package org.firstinspires.ftc.teamcode.common;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.Arm1ServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.Arm2ServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.WristServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda223DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.GrabberServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.LiftData21528;

public abstract class Bot21528_A extends Component {
    private final LiftSingle lift;
    private final ServoSimple grabber, armLeft, wrist, armRight;

    public Bot21528_A(HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
        lift = new LiftSingle(hardwareMap, telemetry, "lift", false, new GoBilda223DcMotorData(), new LiftData21528());
        grabber = new ServoSimple(hardwareMap, telemetry, "grabber", new GrabberServoData21528());
        armLeft = new ServoSimple(hardwareMap, telemetry, "armLeft", new Arm1ServoData21528());
        armRight = new ServoSimple(hardwareMap, telemetry, "armRight", new Arm2ServoData21528());
        wrist = new ServoSimple(hardwareMap, telemetry, "wrist", new WristServoData21528());
        grabberClose();
        armLeftClose();
        //armRightClose();
        wristClose();
    }

    public void grabberClose() {
        grabber.close();
    }

    public void grabberOpen() {
        grabber.open();
    }

    public void armLeftClose() {
        armLeft.close();
    }

    public void armLeftOpen() { armLeft.open(); }

    public void armLeftMiddle() { armLeft.middle(); }

    public void armRightClose() {armRight.close();}

    public void armRightOpen() { armRight.open(); }

    public void armRightMiddle() { armRight.middle(); }

    public void wristOpen() {
        wrist.open();
    }

    public void wristClose() {
        wrist.close();
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
