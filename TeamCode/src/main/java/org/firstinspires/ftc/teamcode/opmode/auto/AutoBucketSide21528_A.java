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

            // Move to bucket and drop first brick
            bot.grabberClose();
            bot.armMiddle();
            bot.wristClose();
            bot.liftHighPosition();
            bot.strafeRightForDistance(3.5);
            bot.moveForwardForDistance(38);
            sleep(500);
            bot.grabberOpen();
            sleep(500);

            // Move to second brick and grab it
            bot.moveForwardForDistance(-9.6);
            bot.turnToHeading(-90);
            bot.moveForwardForDistance(12.5);
            bot.armClose();
            bot.liftLowPosition();
            sleep(500);
            bot.liftMinPosition();
            sleep(1000);
            bot.grabberClose();
            sleep(500);

            // Move to basket and drop second brick
            bot.armMiddle();
            bot.turnToHeading(90);
            bot.liftHighPosition();
            bot.strafeRightForDistance(12);
            bot.moveForwardForDistance(17);
            sleep(500);
            bot.grabberOpen();
            sleep(500);

            // Move to third brick and grab it
            bot.moveForwardForDistance(-17);
            bot.turnToHeading(-90);
            bot.armClose();
            bot.liftLowPosition();
            sleep(500);
            bot.liftMinPosition();
            sleep(1000);
            bot.grabberClose();
            sleep(500);

            // Move to basket and drop third brick
            bot.armMiddle();
            bot.turnToHeading(90);
            bot.liftHighPosition();
            bot.moveForwardForDistance(17);
            sleep(500);
            bot.grabberOpen();
            sleep(500);

            // Go to climb zone and touch bar
            bot.moveForwardForDistance(-48);
            bot.turnToHeading(180);
            bot.moveForwardForDistance(32);

            // Prepare to shut down
            bot.armMiddle();
            bot.liftMinPosition();
            sleep(2500);
            telemetry.addLine("Complete");
            telemetry.update();
        }
    }
}