package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.AutoBot3DeadWheelTemplate;
import org.firstinspires.ftc.teamcode.common.TeleopBotTemplate;

@Config
@Autonomous(name = "AutoTemplateSimple", group = "Linear OpMode")
public class AutoTemplateSimple extends LinearOpMode {
    private ElapsedTime timer = new ElapsedTime();

    protected TeleopBotTemplate bot;

    protected MultipleTelemetry multipleTelemetry;

    public AutoTemplateSimple() {
    }

    @Override
    public void runOpMode() {

        multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.addLine("Initializing.");
        telemetry.update();

        bot = new TeleopBotTemplate(hardwareMap, multipleTelemetry);
        telemetry.addLine("Bot created.");
        telemetry.update();

        bot.moveDirection(0.5, 0.5, 0);
        timer.reset();
        while (timer.milliseconds() < 1000)
        {}
        // Do other stuff
    }
}