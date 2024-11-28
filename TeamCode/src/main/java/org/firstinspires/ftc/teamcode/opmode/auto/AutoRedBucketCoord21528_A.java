package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.common.Bot21528_A;
import org.firstinspires.ftc.teamcode.common.Bot21528_B;
import org.firstinspires.ftc.teamcode.common.BotCoord21528_A;

@Config
@Autonomous(name = "AutoRedBucketCoord21528_A", group = "Auto")

public class AutoRedBucketCoord21528_A extends LinearOpMode {
    BotCoord21528_A bot;
    @Override
    public void runOpMode() {
        bot = new BotCoord21528_A(this, telemetry);

        waitForStart();
        bot.liftResetEncoder();
        bot.setPose(FieldLocations.redBucketStart);
        if (opModeIsActive() && !isStopRequested()) {
            telemetry.addLine("Starting auto actions...");
            telemetry.update();
            bot.moveToPose(FieldLocations.redBucketSpike2);

            sleep(2500);
            telemetry.addLine("Complete");
            telemetry.update();
        }
    }
}