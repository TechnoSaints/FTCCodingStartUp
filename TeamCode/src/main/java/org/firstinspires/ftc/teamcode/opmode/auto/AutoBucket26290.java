package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Bot21528_B;
import org.firstinspires.ftc.teamcode.common.Bot26290;

@Config
@Autonomous(name = "AutoBucket26290", group = "Auto")
public class AutoBucket26290 extends LinearOpMode {
    protected Bot26290 bot;
    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        bot = new Bot26290(this, hardwareMap, telemetry);
        telemetry.addLine("Bot initialized...");
        telemetry.update();

        waitForStart();

        if (opModeIsActive() && !isStopRequested()) {
            telemetry.addLine("Starting auto actions...");
            telemetry.update();
            bot.grabberClose();
            sleep(1000);
            bot.moveForwardForDistance(12);
            bot.armUp(1);
            bot.liftUp(1);
            sleep(1000);
            bot.grabberOpen();
            bot.moveForwardForDistance(-1);
            bot.strafeRightForDistance(36);
            bot.turnToHeading(180);
            bot.moveForwardForDistance(6);
            telemetry.addLine("Complete");
            telemetry.update();
        }
    }
}
