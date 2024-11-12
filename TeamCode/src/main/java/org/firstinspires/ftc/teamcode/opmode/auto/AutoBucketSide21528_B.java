package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.common.Bot21528_B;

@Config
@Autonomous(name = "AutoBucketSide21528_B", group = "Auto")
@Disabled
public class AutoBucketSide21528_B extends LinearOpMode {
    Bot21528_B bot;

    @Override
    public void runOpMode() {
        bot = new Bot21528_B(this, telemetry);

        waitForStart();

        telemetry.addLine("Starting auto actions...");
        telemetry.update();

        bot.grabberClose();
        sleep(1000);
        bot.armMiddle();
        bot.wristClose();
        bot.liftHighPosition();
        bot.strafeRightForDistance(2);
        bot.moveForwardForDistance(31.5);
        bot.grabberOpen();
        sleep(1000);
        bot.moveForwardForDistance(-2.1);
        bot.strafeRightForDistance(21.5);
        bot.turnToHeading(-90);
        bot.armClose();
        //sleep(1500);
        bot.liftlowPosition();
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
        bot.armClose();
        sleep(1000);
        bot.liftlowPosition();
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

        telemetry.addLine("Complete");
        telemetry.update();

    }
}