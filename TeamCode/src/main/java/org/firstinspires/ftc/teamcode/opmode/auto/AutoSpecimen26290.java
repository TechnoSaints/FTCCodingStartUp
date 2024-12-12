package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Bot26290;

@Config
@Autonomous(name = "UNSTABLE Auto26290", group = "Auto")
public class AutoSpecimen26290 extends LinearOpMode {
    protected Bot26290 bot;
    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        bot = new Bot26290(this, hardwareMap, telemetry);
        telemetry.addLine("Bot initialized...");
        telemetry.update();
        bot.armStop();

        waitForStart();
        bot.up();
        bot.armZero();

        if (opModeIsActive() && !isStopRequested()) {
            telemetry.addLine("Starting auto actions...");
            telemetry.update();
            bot.moveForwardForDistance(18);
            bot.armHighPosition();
            sleep(1000);
            bot.liftMediumPosition();
            sleep(500);
            bot.moveForwardForDistance(6);
            sleep(500);
            bot.down();
            sleep(500);
            bot.moveForwardForDistance(-12);
            sleep(500);
            bot.grabberOpen();
            bot.liftlowPosition();
            bot.moveForwardForDistance(2);
            bot.back();
            sleep(500);
            /*bot.turnToHeading(180);
            bot.strafeRightForDistance(-32);
            bot.armMediumPosition();
            sleep(500);
            bot.liftMediumPosition();
            bot.grabberClose();
            sleep(500);
            bot.liftlowPosition();
            sleep(500);
            bot.armHighPosition();
            bot.strafeRightForDistance(32);
            bot.turnToHeading(180);
            bot.liftMediumPosition();
            sleep(500);
            bot.moveForwardForDistance(2);
            bot.down();
            bot.moveForwardForDistance(-2);
            sleep(500);
            bot.grabberOpen();
            sleep(500);
            bot.liftlowPosition();
            sleep(500);*/
            bot.armlowPosition();
            bot.moveForwardForDistance(-18);
            bot.strafeRightForDistance(55);
            telemetry.addLine("Complete");
            telemetry.update();
        }
    }
}
