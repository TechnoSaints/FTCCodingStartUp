package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.Bot26290;

@Config
@TeleOp(name = "Teleop26290", group = "26290")
public class Teleop26290 extends LinearOpMode {

    private Bot26290 bot;
    public static boolean loggingOn = false;

    @Override
    public void runOpMode() {

        double driveAxial = 0.0;
        double driveStrafe = 0.0;
        double driveYaw = 0.0;

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        bot = new Bot26290(this, hardwareMap, telemetry);
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.dpad_up) {
                bot.creepDirection(-1.0, 0.0, 0.0);
            } else if (gamepad1.dpad_down) {
                bot.creepDirection(1.0, 0.0, 0.0);
            } else if (gamepad1.dpad_left) {
                bot.creepDirection(0.0, -1.0, 0.0);
            } else if (gamepad1.dpad_right) {
                bot.creepDirection(0.0, 1.0, 0.0);
            } else {
                driveAxial = gamepad1.left_stick_y;
                driveStrafe = gamepad1.left_stick_x;
                driveYaw = -gamepad1.right_stick_x;
                if ((Math.abs(driveAxial) < 0.2) && (Math.abs(driveStrafe) < 0.2) && (Math.abs(driveYaw) < 0.2)) {
                    bot.stopDrive();
                } else
                    bot.moveDirection(driveAxial, driveStrafe, -driveYaw);
            }

            if (gamepad2.a){
                bot.grabberClose();
            }
            else if (gamepad2.b){
                bot.grabberOpen();
            }

            if (gamepad2.x){
                bot.down();
            }

            else if (gamepad2.y){
                bot.up();
            }

            if (gamepad2.left_bumper){
                bot.left();
            }

            else if (gamepad2.right_bumper){
                bot.right();
            }

            if (gamepad2.right_stick_y > 0.2 || gamepad2.right_stick_y < -0.2) {
                bot.liftUp(gamepad2.right_stick_y);
            }
            else {
                bot.liftStop();
            }

            if (gamepad2.left_stick_y > 0.2 || gamepad2.left_stick_y < -0.2){
                bot.armUp(gamepad2.left_stick_y);
            }

            else {
                bot.armStop();
            }
        }
    }
}