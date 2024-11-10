package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Bot21528_B;

@Config
@Autonomous(name = "AutoSpecimenSide21528_B", group = "Auto")

public class AutoSpecimenSide21528_B extends LinearOpMode {
    protected Bot21528_B bot;
    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        bot = new Bot21528_B(this, telemetry);
        telemetry.addLine("Bot initialized...");
        telemetry.update();

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

            telemetry.addLine("Complete");
            telemetry.update();
        }
    }
}
