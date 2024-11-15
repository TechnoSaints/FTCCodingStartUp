package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Bot21528_A;

@Config
@Autonomous(name = "AutoTest21528_A", group = "Auto")
@Disabled
class AutoTest21528_A extends LinearOpMode {
    Bot21528_A bot;

    @Override
    public void runOpMode() {
        bot = new Bot21528_A(this, telemetry);

        waitForStart();
        bot.liftResetEncoder();

        if (opModeIsActive() && !isStopRequested()) {
            telemetry.addLine("Starting auto actions...");
            telemetry.update();

//            bot.grabberClose();
//            sleep(250);
            bot.armMiddle();
//            bot.wristClose();
            bot.liftHighPosition();
            bot.strafeRightForDistance(2);
            bot.moveForwardForDistance(31.5);
//            bot.grabberOpen();
            sleep(1000);
            bot.moveForwardForDistance(-4);
            bot.strafeRightForDistance(18);
            bot.turnToHeading(-90);
            bot.moveForwardForDistance(12);
            bot.armMiddle();
            bot.liftMinPosition();
            sleep(2500);

            telemetry.addLine("Complete");
            telemetry.update();
            while (opModeIsActive() && !isStopRequested()) {
            }

        }
    }
}