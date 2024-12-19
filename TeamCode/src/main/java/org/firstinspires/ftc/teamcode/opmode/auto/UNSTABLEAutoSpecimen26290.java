package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Bot26290;

@Config
@Autonomous(name = "UNSTABLE Specimen and Park", group = "UNSTABLE")
public class UNSTABLEAutoSpecimen26290 extends LinearOpMode {
    protected Bot26290 bot;
    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        bot = new Bot26290(this, hardwareMap, telemetry);
        telemetry.addLine("Bot initialized...");
        telemetry.update();
        bot.armStop();
        bot.back();

        waitForStart();
        bot.up();
        bot.armZero();

        if (opModeIsActive() && !isStopRequested()) {
            telemetry.addLine("Starting auto actions...");
            telemetry.update();
            bot.moveForwardForDistance(16);
            bot.armHighPosition();
            sleep(1000);
            bot.liftMediumPosition();
            sleep(500);
            bot.moveForwardForDistance(4);
            sleep(500);
            bot.armMediumHighPosition();
            sleep(500);
            bot.moveForwardForDistance(-8);
            bot.grabberOpen();
            sleep(500);
            bot.armHighPosition();
            bot.liftlowPosition();
            sleep(500);
            bot.armlowPosition();
            bot.back();
            sleep(500);
            for (int i=0; i < 4; i++){
                GrabSpecimen();
                ClipSpecimen();
            }
            bot.moveForwardForDistance(-18);
            bot.strafeRightForDistance(55);
            bot.armMediumHighPosition();
            telemetry.addLine("Complete");
            telemetry.update();
        }
    }

    void GrabSpecimen(){
        bot.turnToHeading(179.9);
        bot.strafeRightForDistance(-32);
        bot.armMediumPosition();
        sleep(500);
        bot.liftMediumPosition();
        bot.grabberClose();
        sleep(500);
        bot.liftlowPosition();
        sleep(500);
        bot.armHighPosition();
        bot.strafeRightForDistance(32);
        bot.turnToHeading(0.1);
    }

    void ClipSpecimen(){
        bot.liftMediumPosition();
        sleep(500);
        bot.back();
        bot.liftMediumHighPosition();
        bot.grabberOpen();
        sleep(500);
        bot.armHighPosition();
        bot.liftlowPosition();
        sleep(500);
    }
}
