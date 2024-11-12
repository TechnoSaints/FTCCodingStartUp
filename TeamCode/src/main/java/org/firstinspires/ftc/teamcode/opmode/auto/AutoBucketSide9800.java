package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Config
@Autonomous(name = "AutoBucketSide9800", group = "Auto")

public class AutoBucketSide9800 extends AutoMaster9800 {

    @Override
    public void runOpMode() {

        waitForStart();

        if (opModeIsActive() && !isStopRequested()) {
            telemetry.addLine("Starting auto actions...");
            telemetry.update();

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

            telemetry.addLine("Complete");
            telemetry.update();
        }





    }
}
