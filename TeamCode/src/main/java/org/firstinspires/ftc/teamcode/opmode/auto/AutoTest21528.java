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

            bot.armMiddle();
            bot.liftMediumPosition();
            bot.turnToHeading(-90);
            sleep(3000);
            bot.turnToHeading(180);
            sleep(3000);
            bot.turnToHeading(90);
            sleep(3000);
            bot.turnToHeading(0);
            sleep(3000);

            telemetry.addLine("Complete");
            telemetry.update();
        }
    }
}