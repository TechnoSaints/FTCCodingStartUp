package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Bot9800;

@Config
@Autonomous(name = "AutoTest9800", group = "Auto")
@Disabled
public class AutoTest9800 extends LinearOpMode {
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
//            bot.moveForwardForDistance(12);
//            bot.strafeRightForDistance(12);
            bot.turnToHeading(-90);
            sleep(1000);
//            bot.moveForwardForDistance(12);
            bot.turnToHeading(180);
            sleep(1000);
//            bot.moveForwardForDistance(24);
//            bot.strafeRightForDistance(-12);
            bot.turnToHeading(90);
            sleep(1000);
            bot.turnToHeading(0);


            telemetry.addLine("Complete");
            telemetry.update();
        }

    }
}
