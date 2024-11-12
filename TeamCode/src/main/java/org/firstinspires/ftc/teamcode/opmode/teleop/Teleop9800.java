package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Bot9800;

@Config
@TeleOp(name = "Teleop9800", group = "9800")

public class Teleop9800 extends LinearOpMode {

    private Bot9800 bot;

    @Override
    public void runOpMode() {

        double driveAxial = 0.0;
        double driveStrafe = 0.0;
        double driveYaw = 0.0;

        boolean oGrabberPressed = false, grabberPressed = false, armPressed = false, oArmPressed = false, wristPressed = false;
        ElapsedTime timer = new ElapsedTime();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        bot = new Bot9800(this, telemetry);
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

            if (((gamepad1.a) || (gamepad2.a)) && (timer.milliseconds() > 250)) {
                if (!grabberPressed) {
                    timer.reset();
                    grabberPressed = true;
                    bot.grabberClose();
                } else {
                    timer.reset();
                    grabberPressed = false;
                    bot.grabberOpen();
                }
            }

            if (((gamepad1.x) || (gamepad2.x)) && (timer.milliseconds() > 250)) {
                if (!armPressed) {
                    timer.reset();
                    armPressed = true;
                    bot.armClose();
                } else {
                    timer.reset();
                    armPressed = false;
                    bot.armOpen();
                }
            }

            if (((gamepad1.b) || (gamepad2.right_stick_button)) && (timer.milliseconds() > 250)) {
                if (!wristPressed) {
                    timer.reset();
                    wristPressed = true;
                    bot.wristClose();
                } else {
                    timer.reset();
                    wristPressed = false;
                    bot.wristOpen();
                }
            }

            if ((gamepad2.b) && (timer.milliseconds() > 250)) {
                if (!oGrabberPressed) {
                    timer.reset();
                    oGrabberPressed = true;
                    bot.outtakeGrabberClose();
                } else {
                    timer.reset();
                    oGrabberPressed = false;
                    bot.outtakeGrabberOpen();
                }
            }

            if ((gamepad2.y) && (timer.milliseconds() > 250)) {
                if (!oArmPressed) {
                    timer.reset();
                    oArmPressed = true;
                    bot.outtakeArmClose();
                } else {
                    timer.reset();
                    oArmPressed = false;
                    bot.outtakeArmOpen();
                }
            }

            if ((gamepad1.right_bumper) || (gamepad2.right_bumper)) {
                bot.intakeLiftUp(1);
            } else if ((gamepad1.left_bumper) || (gamepad2.left_bumper)) {
                bot.intakeLiftDown(1);
            } else {
                bot.intakeLiftStop();
            }

            if (gamepad2.right_trigger > 0.2) {
                bot.outtakeLiftUp(gamepad2.right_trigger);
            } else if (gamepad2.left_trigger > 0.2) {
                bot.outtakeLiftDown(gamepad2.left_trigger);
            } else {
                bot.outtakeLiftStop();
            }
        }
    }
}