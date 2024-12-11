package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Bot21528_A;

@Config
@Autonomous(name = "AutoSpecimenSide21528_A", group = "Auto")
@Disabled
public class AutoSpecimenSide21528_A extends LinearOpMode {
    Bot21528_A bot;
    @Override
    public void runOpMode() {
        bot = new Bot21528_A(this, telemetry);

        waitForStart();
//        bot.liftMoveDownToSwitch();
        bot.liftResetEncoder();

        if (opModeIsActive() && !isStopRequested()) {
            telemetry.addLine("Starting auto actions...");
            telemetry.update();

            //moving and hanging first specimen via pulling back with wheels
            bot.armSpecimenHang();
            bot.moveForwardForDistance(-30);

            //move to red preset pickup

            //move to clip area flipping arm

            //drop and pick up specimen

            //move and hang second specimen

            //move back and pick up third specimen

            //move and hang third specimen

            // Prepare to shut down
//            bot.armMiddle();
            bot.liftMinPosition();
            sleep(2500);
            telemetry.addLine("Complete");
            telemetry.update();
        }
    }
}