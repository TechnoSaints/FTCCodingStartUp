package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Bot21528_A;
import org.firstinspires.ftc.teamcode.common.Bot21528_B;

@Config
@Autonomous(name = "AutoBucketSide21528_A", group = "Auto")

public class AutoBucketSide21528_A extends LinearOpMode {
    Bot21528_A bot;
    @Override
    public void runOpMode() {
        bot = new Bot21528_A(this, telemetry);

        waitForStart();
        bot.liftMoveDownToSwitch();
        bot.liftResetEncoder();

        if (opModeIsActive() && !isStopRequested()) {
            telemetry.addLine("Starting auto actions...");
            telemetry.update();

            bot.grabberClose();
            bot.armMiddle();
            bot.wristClose();
            bot.liftHighPosition();
            bot.strafeRightForDistance(3.5);
            bot.moveForwardForDistance(36);
            sleep(500);
            bot.grabberOpen();
            sleep(1000);
            bot.moveForwardForDistance(-7.6);
            bot.turnToHeading(-90);
            bot.moveForwardForDistance(12.5);
            bot.armClose();
            //sleep(1500);
            bot.liftLowPosition();
            sleep(1000);
            bot.liftMinPosition();
            sleep(1500);
            //sleep(1000);
            bot.grabberClose();
            sleep(1000);
            bot.armMiddle();
            //sleep(1000);
            sleep(250);
            bot.turnToHeading(0);
            sleep(1000);
            bot.liftHighPosition();
            //sleep(1000);
            sleep(250);
            bot.strafeRightForDistance(-17);
            bot.moveForwardForDistance(8);
            //sleep(1000);
            sleep(250);
            bot.grabberOpen();
            bot.strafeRightForDistance(5);
            /*
            sleep(1000);
            bot.moveForwardForDistance(-3.6);
            bot.strafeRightForDistance(23);
            bot.turnToHeading(-90);
            bot.strafeRightForDistance(-15);
            bot.armOpen();
            sleep(1000);
            bot.liftLowPosition();
            sleep(500);
            bot.liftMinPosition();
            sleep(1000);
            bot.grabberClose();
            //sleep(3000);
            sleep(1000);
            bot.strafeRightForDistance(16);
            bot.turnToHeading(0);
            bot.armMiddle();
            sleep(500);
            bot.liftHighPosition();
            sleep(1000);
            bot.strafeRightForDistance(-23);
            sleep(1000);
            bot.grabberOpen();
            sleep(1000);
            //sleep(1000);
*/


            bot.armMiddle();
            bot.liftMinPosition();
            sleep(2500);
            telemetry.addLine("Complete");
            telemetry.update();
        }
    }
}