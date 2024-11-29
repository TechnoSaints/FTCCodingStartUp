package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.common.Bot21528_A;
import org.firstinspires.ftc.teamcode.common.Bot21528_B;
import org.firstinspires.ftc.teamcode.common.BotCoord21528_A;

@Config
@Autonomous(name = "AutoRedBucketCoord21528_A", group = "Auto")

public class AutoRedBucketCoord21528_A extends LinearOpMode {
    BotCoord21528_A botCoord;
    @Override
    public void runOpMode() {
        botCoord = new BotCoord21528_A(this, telemetry);
        botCoord.armMiddle();
        sleep(500);

        waitForStart();
        botCoord.liftResetEncoder();
        botCoord.setPose(new Pose2D(DistanceUnit.INCH,0,0, AngleUnit.DEGREES,0));
        if (opModeIsActive() && !isStopRequested()) {
            telemetry.addLine("Starting auto actions...");
            telemetry.update();
            botCoord.moveToPose(new Pose2D(DistanceUnit.INCH,0,0, AngleUnit.DEGREES,90));

            telemetry.addLine("Complete");
            telemetry.update();
        }
    }
}