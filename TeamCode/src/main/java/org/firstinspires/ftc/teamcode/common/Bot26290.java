package org.firstinspires.ftc.teamcode.common;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda117DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.ServoData;
import org.firstinspires.ftc.teamcode.common.hardware_data.team26290.ArmData26290;
import org.firstinspires.ftc.teamcode.common.hardware_data.team26290.GrabberServoData26290;
import org.firstinspires.ftc.teamcode.common.hardware_data.team26290.LiftData26290;

public abstract class Bot26290 extends Component {
    private LiftSingle lift, arm;
    private ServoSimple grabber;

    public Bot26290(HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
        grabber = new ServoSimple(hardwareMap, telemetry, "grabber", new GrabberServoData26290());
        lift = new LiftSingle(hardwareMap, telemetry, "lift", false, new GoBilda117DcMotorData(), new LiftData26290());
        arm = new LiftSingle(hardwareMap, telemetry, "arm", false, new GoBilda117DcMotorData(), new ArmData26290());
        grabberClose();
    }

    public void grabberClose(){
        grabber.close();
    }

    public void grabberOpen(){
        grabber.open();
    }

    public void liftUp(double speed){lift.up(speed);}

    public void liftDown(double speed){lift.down(speed);}

    public void liftZero(){lift.zero();}

    public void liftStop(){lift.stop();}

    public void armUp(double speed){arm.up(speed);}

    public void armDown(double speed){arm.down(speed);}

    public void armZero(){arm.zero();}

    public void armStop(){arm.stop();}
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
