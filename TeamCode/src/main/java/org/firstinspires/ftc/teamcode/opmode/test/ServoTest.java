package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.ServoSimple;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.Arm2ServoData21528;

@Config
@TeleOp(name = "TEST ServoTest", group = "Test")

public class ServoTest extends LinearOpMode {

    private ServoSimple servo;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        servo = new ServoSimple(hardwareMap, telemetry, "arm", new Arm2ServoData21528());

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                servo.close();
            } else if (gamepad1.left_bumper) {
                servo.open();
            }
        }
    }
}
