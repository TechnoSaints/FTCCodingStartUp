package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Bot21528_A;

@Config
@Autonomous(name = "AutoTest21528", group = "Auto")

public class AutoTest21528 extends LinearOpMode {
    Bot21528_A bot;
    @Override
    public void runOpMode() {
        bot = new Bot21528_A(this, telemetry);

        waitForStart();
        bot.liftResetEncoder();

        if (opModeIsActive() && !isStopRequested()) {
            telemetry.addLine("Starting auto actions...");
            telemetry.update();

            bot.grabberClose();
            sleep(250);
            bot.armMiddle();
            bot.wristClose();
            sleep(1000);
            bot.liftHighPosition();
            bot.strafeRightForDistance(2);
            bot.moveForwardForDistance(31.5);
            bot.grabberOpen();
            sleep(1000);
            bot.moveForwardForDistance(-4);
//            bot.strafeRightForDistance(21.5);
            bot.turnToHeading(-90);
            bot.armMiddle();
            bot.liftMoveDownToSwitch();
            sleep(2500);
            /*
            bot.armOpen();
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
            bot.strafeRightForDistance(-23);
            //sleep(1000);
            sleep(250);
            bot.grabberOpen();
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
            telemetry.addLine("Complete");
            telemetry.update();
        }
    }
}