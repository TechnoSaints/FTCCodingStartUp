package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.TeleopBotTemplate;

@Config
@Autonomous(name = "AutoTimers21528", group = "Auto")
public class AutoTimers21528 extends LinearOpMode {
    private ElapsedTime timer = new ElapsedTime();

    protected TeleopBotTemplate bot;

    protected MultipleTelemetry multipleTelemetry;

    public AutoTimers21528() {
    }

    @Override
    public void runOpMode() {

        multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.addLine("Initializing.");
        telemetry.update();

        bot = new TeleopBotTemplate(hardwareMap, multipleTelemetry);
        telemetry.addLine("Bot created.");
        telemetry.update();

        bot.moveDirection(0.0, -0.25, 0);
        timer.reset();
        while (timer.milliseconds() < 1000)
        {}

        bot.stopDrive();
        // Do other stuff
    }
}