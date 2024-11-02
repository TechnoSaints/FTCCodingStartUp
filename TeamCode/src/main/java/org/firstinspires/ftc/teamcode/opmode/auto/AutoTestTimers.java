package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.TeleopBotTest;

@Config
@Autonomous(name = "AutoTestSimple", group = "Linear OpMode")
public class AutoTestTimers extends LinearOpMode {
    private ElapsedTime timer = new ElapsedTime();

    protected TeleopBotTest bot;

    protected MultipleTelemetry multipleTelemetry;

    public AutoTestTimers() {
    }

    @Override
    public void runOpMode() {

        multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.addLine("Initializing.");
        telemetry.update();

        bot = new TeleopBotTest(hardwareMap, multipleTelemetry);
        telemetry.addLine("Bot created.");
        telemetry.update();

        bot.moveDirection(0.5, 0.5, 0);
        timer.reset();
        while (timer.milliseconds() < 1000)
        {}
        bot.stopDrive();
        // Do other stuff
    }
}