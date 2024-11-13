package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Bot9800;

@Config
@Autonomous(name = "AutoBucketSide9800", group = "Auto")

public class AutoBucketSide9800 extends LinearOpMode {
    protected Bot9800 bot;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        bot = new Bot9800(this, telemetry);
        telemetry.addLine("Bot initialized...");
        telemetry.update();
        waitForStart();

        if (opModeIsActive() && !isStopRequested()) {
            telemetry.addLine("Starting auto actions...");
            telemetry.update();
            bot.moveForwardForDistance(12);
            bot.strafeRightForDistance(12);
            bot.turnToHeading(-90);
            bot.moveForwardForDistance(12);
            bot.turnToHeading(90);
            bot.moveForwardForDistance(24);
            bot.strafeRightForDistance(-12);
            bot.turnToHeading(0);

            /*
            bot.outtakeGrabberClose();
            bot.moveForwardForDistance(-12);
            bot.chamberPosition();
            bot.moveForwardForDistance(-78);
            bot.releasePosition();
            bot.outtakeGrabberOpen();
            bot.moveForwardForDistance(98);
            bot.strafeRightForDistance(98);
            bot.turnToHeading(180);
            bot.grabPosition();
            bot.armClose();
            bot.grabberClose();
            bot.armOpen();
            bot.intakeLiftZero();
            bot.outtakeGrabberClose();
            bot.grabberOpen();
            bot.outtakeArmOpen();
            bot.moveForwardForDistance(-78);
            bot.highBucketPosition();
            bot.outtakeGrabberOpen();
            bot.outtakeLiftZero();
            bot.strafeRightForDistance(78);
            bot.moveForwardForDistance(78);
            bot.turnToHeading(-90);
            bot.moveForwardForDistance(78);
*/
            telemetry.addLine("Complete");
            telemetry.update();
        }

    }
}
