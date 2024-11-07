package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.common.Bot21528_B;
import org.firstinspires.ftc.teamcode.common.Bot9800;

public abstract class AutoMaster9800 extends LinearOpMode {
    protected Bot9800 bot;

    public AutoMaster9800()
    {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        bot = new Bot9800(hardwareMap, telemetry);
        telemetry.addLine("Bot initialized...");
        telemetry.update();
    }
}
