package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.common.BotCoord21528_A;

@Config
@Autonomous(name = "AutoSimpleMoveTest21528_A", group = "Auto")

public class AutoSimpleMoveTest21528_A extends LinearOpMode {
    BotCoord21528_A botCoord;

    @Override
    public void runOpMode() {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        Telemetry dashboardTelemetry = dashboard.getTelemetry();
        telemetry = new MultipleTelemetry(telemetry, dashboardTelemetry);

        botCoord = new BotCoord21528_A(this, telemetry);
        botCoord.armMiddle();
        sleep(500);

        waitForStart();
        botCoord.liftResetEncoder();
        botCoord.setPose(new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0));
        if (opModeIsActive() && !isStopRequested()) {
            telemetry.addLine("Starting auto actions...");
            telemetry.update();
            botCoord.moveToPose(new Pose2D(DistanceUnit.INCH,24,0, AngleUnit.DEGREES,0));
            telemetry.addLine("First move complete...");
            telemetry.update();
            sleep(1000);
            botCoord.moveToPose(new Pose2D(DistanceUnit.INCH,24,24, AngleUnit.DEGREES,0));
            telemetry.addLine("Second move complete...");
            telemetry.update();
            sleep(1000);
            botCoord.moveToPose(new Pose2D(DistanceUnit.INCH, 0, 24, AngleUnit.DEGREES, 0));
            telemetry.addLine("Third move complete...");
            telemetry.update();
            sleep(1000);
            botCoord.moveToPose(new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0));
            telemetry.addLine("Fourth move complete...");
            telemetry.update();
            sleep(1000);
            //            while (true) {
//                botCoord.drivetrainUpdateCurrentPose();
//                botCoord.drivetrainLog();
//            }
            //telemetry.addLine("Complete");
            //telemetry.update();
        }
    }
}