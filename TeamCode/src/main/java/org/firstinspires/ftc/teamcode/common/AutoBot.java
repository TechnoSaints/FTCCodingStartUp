package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.AutoDrivetrain3DeadWheel;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import androidx.annotation.NonNull;

public class AutoBot extends Bot {
    private final AutoDrivetrain3DeadWheel drivetrain;
    private Pose2d startPose;

    public AutoBot(HardwareMap hardwareMap, Telemetry telemetry, Pose2d startPose) {
        super(hardwareMap, telemetry);
        drivetrain = new AutoDrivetrain3DeadWheel(hardwareMap, startPose);
    }

    public AutoDrivetrain3DeadWheel drivetrain() {
        return (drivetrain);
    }

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

    public class LiftToCruisingPosition implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            telemetry.addData("Lift Moving to Cruising Position...",1);
            telemetry.update();
            return false;
        }
    }
    public Action liftToCruisingPosition() {
        return new LiftToCruisingPosition();
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