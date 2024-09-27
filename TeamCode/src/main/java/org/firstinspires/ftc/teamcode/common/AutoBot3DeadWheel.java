package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.AutoDrivetrain3DeadWheel;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import androidx.annotation.NonNull;

public class AutoBot3DeadWheel extends Bot {
    private final AutoDrivetrain3DeadWheel drivetrain;
    private Pose2d startPose;

    public AutoBot3DeadWheel(HardwareMap hardwareMap, Telemetry telemetry, Pose2d startPose) {
        super(hardwareMap, telemetry);
        drivetrain = new AutoDrivetrain3DeadWheel(hardwareMap, startPose);
    }

    public AutoDrivetrain3DeadWheel drivetrain() {
        return (drivetrain);
    }

}