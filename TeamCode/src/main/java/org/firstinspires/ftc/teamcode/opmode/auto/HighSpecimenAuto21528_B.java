package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import org.firstinspires.ftc.teamcode.common.Bot21528_B;

@Config
@Autonomous(name = "HighSpecimanAuto21528_B", group = "Auto")

public class HighSpecimenAuto21528_B extends AutoMaster21528 {
    @Override
    public void runOpMode() {

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
