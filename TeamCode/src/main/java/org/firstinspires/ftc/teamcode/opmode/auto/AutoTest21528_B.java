package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Bot21528_B;

@Config
@Autonomous(name = "AutoTest21528_B", group = "Auto")

public class AutoTest21528_B extends LinearOpMode {
    Bot21528_B bot;
    @Override
    public void runOpMode() {
        bot = new Bot21528_B(this, telemetry);

        waitForStart();

        if (opModeIsActive() && !isStopRequested()) {
            telemetry.addLine("Starting auto actions...");
            telemetry.update();

            bot.turnToHeading(-90);
            sleep(2500);
            bot.turnToHeading(-180);
            sleep(2500);
            bot.turnToHeading(90);
            sleep(2500);
            bot.turnToHeading(0);
            sleep(2500);

            telemetry.addLine("Complete");
            telemetry.update();
        }
    }
}