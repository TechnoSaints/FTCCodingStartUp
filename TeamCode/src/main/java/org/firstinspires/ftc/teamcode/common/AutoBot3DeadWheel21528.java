package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.AutoDrivetrain;

import com.acmerobotics.roadrunner.Pose2d;

public class AutoBot3DeadWheel21528 extends Bot21528 {
    private final AutoDrivetrain drivetrain;
    private Pose2d startPose;

    public AutoBot3DeadWheel21528(HardwareMap hardwareMap, Telemetry telemetry, Pose2d startPose) {
        super(hardwareMap, telemetry);
        drivetrain = new AutoDrivetrain(hardwareMap, startPose);
    }

    public AutoDrivetrain drivetrain() {
        return (drivetrain);
    }
}