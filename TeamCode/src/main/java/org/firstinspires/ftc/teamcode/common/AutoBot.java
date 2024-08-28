package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.AutoDrivetrain3Wheel;
import org.firstinspires.ftc.teamcode.opmode.auto.AutoTemplate;

import com.acmerobotics.roadrunner.Pose2d;

public class AutoBot extends Bot {
    private final AutoDrivetrain3Wheel drivetrain;
    private Pose2d startPose;
    public AutoBot(HardwareMap hardwareMap, Telemetry telemetry, Pose2d startPose)
    {
        super(hardwareMap, telemetry);
        drivetrain = new AutoDrivetrain3Wheel(hardwareMap,telemetry,startPose);
    }
}