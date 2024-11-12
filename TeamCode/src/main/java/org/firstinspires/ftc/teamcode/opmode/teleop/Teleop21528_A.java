package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.Bot21528_A;

@Config
@TeleOp(name = "Teleop21528_A", group = "Linear OpMode")

public class Teleop21528_A extends LinearOpMode {

    private Bot21528_A bot;
    public static boolean loggingOn = false;

    @Override
    public void runOpMode() {

        double driveAxial = 0.0;
        double driveStrafe = 0.0;
        double driveYaw = 0.0;
        double motorDirection = 1;
        boolean leftBumperPressed = false;

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        bot = new Bot21528_A(this, telemetry);
        waitForStart();
        bot.liftMoveDownToSwitch();
        bot.liftResetEncoder();
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
                driveAxial = gamepad1.left_stick_y * motorDirection;
                driveStrafe = gamepad1.left_stick_x * motorDirection;
                driveYaw = gamepad1.right_stick_x;
                if ((Math.abs(driveAxial) < 0.2) && (Math.abs(driveStrafe) < 0.2) && (Math.abs(driveYaw) < 0.2)) {
                    bot.stopDrive();
                } else
                    bot.moveDirection(driveAxial, driveStrafe, -driveYaw);
            }

            if (gamepad1.right_trigger > 0.2) {
                bot.liftUp(gamepad1.right_trigger);
            } else if (gamepad1.left_trigger > 0.2) {
                bot.liftDown(gamepad1.left_trigger);
            } else {
                bot.liftStop();
            }

            if (gamepad1.x) {
                bot.grabberClose();
            } else if (gamepad1.a) {
                bot.grabberOpen();
            }

            if (gamepad1.left_bumper) {
                bot.armClose();
                bot.wristClose();
                bot.liftLowPosition();
                motorDirection = 1;
                leftBumperPressed = true;
            } else if (gamepad1.right_bumper) {
                bot.armMiddle();
                bot.wristOpen();
                bot.liftHighPosition();
                motorDirection = 1;
            } else if (leftBumperPressed)
            {
                bot.liftMinPosition();
                leftBumperPressed = false;
            }

            if (gamepad1.b) {
                bot.armClose();
                bot.wristClose();
                motorDirection = 1;
            } else if (gamepad1.y) {
                bot.wristSpecimenHang();
                bot.armSpecimenHang();
                motorDirection = -1;
            }

            if (gamepad1.right_bumper) {
                bot.grabberClose();
            } else if (gamepad1.left_bumper) {
                bot.grabberOpen();
            }
        }
    }
}

