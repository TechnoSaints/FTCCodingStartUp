package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.common.Bot26290;

@Config
@Autonomous(name = "AutoBucket26290", group = "Auto")
@Disabled
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
            bot.armStop();
            bot.liftStop();
            bot.grabberClose();
            bot.strafeRightForDistance(1);
            bot.moveForwardForDistance(29.5);
            bot.liftMediumPosition();
            bot.grabberOpen();
            bot.liftlowPosition();
            bot.moveForwardForDistance(-7);
            bot.strafeRightForDistance(51);
            bot.turnToHeading(180);
            bot.moveForwardForDistance(-24);
            bot.liftMediumPosition();
            telemetry.addLine("Complete");
            telemetry.update();
        }
    }
}
