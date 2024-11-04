package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.common.Bot21528_B;

@Config
@Autonomous(name = "AutoBucketSide21528_B", group = "Auto")

public class AutoBucketSide21528_B extends LinearOpMode {
    private Bot21528_B bot;

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

//            telemetry.addLine("Moving straight");
//            telemetry.update();
            bot.grabberClose();
            sleep(1000);
            bot.armMiddle();
            bot.wristClose();
            bot.liftHighPosition();
            bot.strafeForDistance(-2);
            bot.moveStraightForDistance(-32);
            bot.grabberOpen();
            sleep(1000);
            bot.strafeForDistance(-12);
            /*
//            telemetry.addLine("Turning");
//            telemetry.update();
            bot.grabberOpen();
            bot.turnToHeading(-90);
//            telemetry.addLine("Moving straight");
//            telemetry.update();
            bot.armClose();
            bot.grabberClose();
            bot.moveStraightForDistance(18);
            bot.grabberOpen();
            bot.turnToHeading(90);
            bot.moveStraightForDistance(18);
            bot.turnToHeading(0);
            bot.moveStraightForDistance(-18);
            bot.armOpen();
//            telemetry.addLine("Turning");
//            telemetry.update();
//            bot.turnToHeading(-180);
//            telemetry.addLine("Moving straight");
//            telemetry.update();
//            bot.moveStraightForDistance(18);
//            telemetry.addLine("Turning");
//            telemetry.update();
//            bot.turnToHeading(-270);
//            telemetry.addLine("Moving straight");
//            telemetry.update();
//            bot.moveStraightForDistance(18);
//            telemetry.addLine("Turning");
//            telemetry.update();
/            bot.turnToHeading(0);
*/

            telemetry.addLine("Complete");
            telemetry.update();
        }
    }
}
