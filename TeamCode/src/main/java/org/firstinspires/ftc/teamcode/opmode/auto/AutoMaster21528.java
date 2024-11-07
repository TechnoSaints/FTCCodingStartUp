package org.firstinspires.ftc.teamcode.opmode.auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import org.firstinspires.ftc.teamcode.common.Bot21528_B;

public abstract class AutoMaster21528 extends LinearOpMode {
    protected Bot21528_B bot;

    public AutoMaster21528()
    {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        bot = new Bot21528_B(this, telemetry);
        telemetry.addLine("Bot initialized...");
        telemetry.update();
    }
}
