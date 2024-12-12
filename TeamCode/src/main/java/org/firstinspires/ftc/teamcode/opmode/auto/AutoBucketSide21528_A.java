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
//        bot.liftMoveDownToSwitch();
        bot.liftResetEncoder();

        if (opModeIsActive() && !isStopRequested()) {
            telemetry.addLine("Starting auto actions...");
            telemetry.update();

            // Move to bucket and drop first brick
            bot.grabberClose();
            bot.armMiddle();
            bot.wristClose();
            bot.liftHighPosition();
            bot.strafeRightForDistance(3.5);
            bot.moveForwardForDistance(38);
            sleep(250);
            bot.grabberOpen();
            sleep(250);

            // Move to second brick and grab it
            bot.moveForwardForDistance(-10);
            bot.turnToHeading(-90);
            bot.moveForwardForDistance(16);
            bot.armClose();
            bot.liftLowPosition();
            sleep(500);
            bot.liftMinPosition();
            sleep(500);
            bot.grabberClose();
            sleep(250);

            // Move to basket and drop second brick
            bot.armMiddle();
            bot.liftHighPosition();
            bot.moveForwardForDistance(-17.5);
            bot.turnToHeading(0);
            bot.moveForwardForDistance(6.5);
            sleep(250);
            bot.grabberOpen();
            sleep(250);

            // Move to third brick and grab it
            bot.moveForwardForDistance(-9);
            bot.turnToHeading(-90);
            bot.moveForwardForDistance(17);
            bot.strafeRightForDistance(-11.5);
            bot.armClose();
            bot.liftLowPosition();
            sleep(500);
            bot.liftMinPosition();
            sleep(650);
            bot.grabberClose();
            sleep(250);

            // Move to basket and drop third brick
            bot.armMiddle();
            bot.liftHighPosition();
            bot.strafeRightForDistance(12.5);
            bot.moveForwardForDistance(-17);
            bot.turnToHeading(0);
            bot.moveForwardForDistance(9);
            sleep(250);
            bot.grabberOpen();
            sleep(250);

            //Move to fourth brick and pick up
            bot.moveForwardForDistance(-16);
            bot.strafeRightForDistance(40);
            bot.armClose();
            bot.moveForwardForDistance(5.5);
            bot.wristSwivel90();
            bot.liftMediumPosition();
            sleep(500);
            bot.moveForwardForDistance(2.75);
            bot.liftMinPosition();
            sleep(500);
            bot.grabberClose();
            sleep(250);


            //Drop 4th Brick
            bot.moveForwardForDistance(-4);
            bot.armMiddle();
            bot.liftHighPosition();
            bot.strafeRightForDistance(-43);
            bot.wristSwivel180();
            bot.moveForwardForDistance(8.5);
            bot.grabberOpen();
            sleep(500);
            bot.moveForwardForDistance(-2);

            //move to end position
            bot.liftMinPosition();
            sleep(3000);
        }
    }
}