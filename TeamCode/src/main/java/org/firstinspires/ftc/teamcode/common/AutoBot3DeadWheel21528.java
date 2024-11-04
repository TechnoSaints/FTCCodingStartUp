package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.AutoDrivetrainTest;

import com.acmerobotics.roadrunner.Pose2d;

public class AutoBot3DeadWheel21528 extends Bot21528_B {
    private final AutoDrivetrainTest drivetrain;
    private Pose2d startPose;

    public AutoBot3DeadWheel21528(LinearOpMode opMode, Telemetry telemetry, Pose2d startPose) {
        super(opMode, telemetry);
        drivetrain = new AutoDrivetrainTest(opMode.hardwareMap, startPose);
    }

    public AutoDrivetrainTest drivetrain() {
        return (drivetrain);
    }
}