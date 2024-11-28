package org.firstinspires.ftc.teamcode.opmode.testandtune;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.ServoSimple;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.ArmServoData9800;

@Config
@TeleOp(name = "TEST ServoTest", group = "Test")
@Disabled
public class ServoTest extends LinearOpMode {

    private ServoSimple servo;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        servo = new ServoSimple(hardwareMap, telemetry, "arm", new ArmServoData9800());

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
