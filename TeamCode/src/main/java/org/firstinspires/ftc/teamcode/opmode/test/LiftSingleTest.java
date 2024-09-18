package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.LiftSingle;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.Viper117Long2StageLiftData;

@Config
@TeleOp(name = "LiftSingleTest", group = "TeleOp")

public class LiftSingleTest extends LinearOpMode {

    private LiftSingle lift;

    LiftSingleTest() {
        lift = new LiftSingle(hardwareMap, telemetry, "lift", new GoBilda312DcMotorData(), new Viper117Long2StageLiftData());
    }

    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_trigger > 0.2) {
                lift.up(gamepad1.right_trigger);
            } else if (gamepad1.left_trigger > 0.2) {
                lift.down(gamepad1.left_trigger);
            } else {
                lift.stop();
            }
        }
    }

}
