package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Bot21528_A;
import org.firstinspires.ftc.teamcode.common.Bot21528_B;

@Config
@Autonomous(name = "AutoSpecimenSide21528_B", group = "Auto")
//@Disabled

public class AutoSpecimenSide21528_B extends LinearOpMode {
    protected Bot21528_A bot;
    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        bot = new Bot21528_A(this, telemetry);
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
            bot.turnToHeading(90);
            bot.liftSpecimanHangPosition();
            bot.strafeRightForDistance(2);
            bot.moveForwardForDistance(-32);
            bot.wristSwivel0();
            bot.moveForwardForDistance(-8);
            bot.grabberOpen();
            sleep(1000);
            //Going To Push all Bricks
            bot.strafeRightForDistance(-30);
            bot.moveForwardForDistance(-8);
            bot.strafeRightForDistance(10);
            bot.moveForwardForDistance(-20);

            telemetry.addLine("Complete");
            telemetry.update();
        }
    }
}
