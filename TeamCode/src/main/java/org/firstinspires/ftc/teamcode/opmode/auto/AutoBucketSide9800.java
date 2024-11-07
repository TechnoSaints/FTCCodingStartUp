package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Config
@Autonomous(name = "AutoBucketSide9800", group = "Auto")

public class AutoBucketSide9800 extends AutoMaster21528 {

    @Override
    public void runOpMode() {

        waitForStart();

        if (opModeIsActive() && !isStopRequested()) {
            telemetry.addLine("Starting auto actions...");
            telemetry.update();

            bot.grabberClose();
            sleep(1000);
            bot.armMiddle();
            bot.wristClose();
            bot.liftHighPosition();
            bot.strafeRightForDistance(2);
            bot.moveForwardForDistance(32);
            bot.grabberOpen();
            sleep(1000);
            bot.moveForwardForDistance(-3.6);
            bot.strafeRightForDistance(21.5);
            bot.turnToHeading(-90);
            bot.armOpen();
            sleep(1500);
            bot.liftlowPosition();
            sleep(1000);
            bot.liftMinPosition();
            sleep(1500);
            bot.grabberClose();
            sleep(1000);
            bot.armMiddle();
            sleep(1000);
            bot.turnToHeading(0);
            sleep(1000);
            bot.liftHighPosition();
            sleep(1000);
            bot.strafeRightForDistance(-23);
            sleep(1000);
            bot.grabberOpen();
            sleep(1000);
            bot.moveForwardForDistance(-3.6);
            bot.strafeRightForDistance(23);
            bot.turnToHeading(-90);
            bot.strafeRightForDistance(-16);
            bot.armOpen();
            sleep(1500);
            bot.liftlowPosition();
            sleep(1000);
            bot.liftMinPosition();
            sleep(1500);
            bot.grabberClose();
            sleep(3000);
            bot.strafeRightForDistance(16);
            bot.turnToHeading(0);
            bot.armMiddle();
            sleep(1000);
            bot.liftHighPosition();
            sleep(1000);
            bot.strafeRightForDistance(-23);
            sleep(3000);
            bot.grabberOpen();
            sleep(3000);

            telemetry.addLine("Complete");
            telemetry.update();
        }
    }
}
