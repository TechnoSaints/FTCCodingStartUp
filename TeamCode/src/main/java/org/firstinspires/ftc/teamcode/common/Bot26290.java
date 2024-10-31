package org.firstinspires.ftc.teamcode.common;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda117DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda312DcMotorData;

public abstract class Bot26290 extends Component {
    public Bot26290(HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
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
