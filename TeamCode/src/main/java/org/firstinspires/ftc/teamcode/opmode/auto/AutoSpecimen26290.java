package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Bot26290;

@Config
@Autonomous(name = "AutoSpecimen26290", group = "Auto")
public class AutoSpecimen26290 extends LinearOpMode {
    protected Bot26290 bot;
    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        bot = new Bot26290(this, hardwareMap, telemetry);
        telemetry.addLine("Bot initialized...");
        telemetry.update();

        waitForStart();

        if (opModeIsActive() && !isStopRequested()) {
            telemetry.addLine("Starting auto actions...");
            telemetry.update();
            bot.moveForwardForDistance(32);
            bot.armMediumPosition();
            bot.liftMediumPosition();
            bot.up();
            bot.moveForwardForDistance(-5);
            bot.grabberOpen();
            bot.liftlowPosition();
            bot.turnToHeading(180);
            bot.strafeRightForDistance(-32);
            bot.armlowPosition();
            bot.liftMediumPosition();
            bot.grabberClose();
            bot.armMediumPosition();
            bot.strafeRightForDistance(32);
            bot.turnToHeading(180);
            bot.moveForwardForDistance(2);
            bot.down();
            bot.moveForwardForDistance(-2);
            bot.grabberOpen();
            bot.moveForwardForDistance(-18);
            bot.strafeRightForDistance(55);
            telemetry.addLine("Complete");
            telemetry.update();
        }
    }
}
