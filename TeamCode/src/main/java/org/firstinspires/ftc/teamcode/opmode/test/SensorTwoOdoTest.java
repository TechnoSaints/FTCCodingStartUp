/*
    SPDX-License-Identifier: MIT

    Copyright (c) 2024 SparkFun Electronics
*/
package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.common.TeleopBotSimple;
import org.firstinspires.ftc.teamcode.drive.GoBildaPinpointDriver;

import java.util.Locale;

@TeleOp(name = "TEST TwoOdoTest", group = "Test")

public class SensorTwoOdoTest extends LinearOpMode {
    GoBildaPinpointDriver gobildaOdo; // Declare OpMode member for the Odometry Computer
    SparkFunOTOS sparkOdo;

    double oldTime = 0;

    TeleopBotSimple bot;

    @Override
    public void runOpMode() throws InterruptedException {
        // Get a reference to the sensor

        double driveAxial = 0.0;
        double driveStrafe = 0.0;
        double driveYaw = 0.0;
        int motorDirection = 1;

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        bot = new TeleopBotSimple(hardwareMap, telemetry);

        sparkOdo = hardwareMap.get(SparkFunOTOS.class, "sensor_otos");

        // All the configuration for the OTOS is done in this helper method, check it out!
        configureOtos();

        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.

        gobildaOdo = hardwareMap.get(GoBildaPinpointDriver.class, "odo");

        /*
        Set the odometry pod positions relative to the point that the odometry computer tracks around.
        The X pod offset refers to how far sideways from the tracking point the
        X (forward) odometry pod is. Left of the center is a positive number,
        right of center is a negative number. the Y pod offset refers to how far forwards from
        the tracking point the Y (strafe) odometry pod is. forward of center is a positive number,
        backwards is a negative number.
         */
        gobildaOdo.setOffsets(106, -85); //these are tuned for 3110-0002-0001 Product Insight #1

        /*
        Set the kind of pods used by your robot. If you're using goBILDA odometry pods, select either
        the goBILDA_SWINGARM_POD, or the goBILDA_4_BAR_POD.
        If you're using another kind of odometry pod, uncomment setEncoderResolution and input the
        number of ticks per mm of your odometry pod.
         */
        gobildaOdo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        //odo.setEncoderResolution(13.26291192);

        /*
        Set the direction that each of the two odometry pods count. The X (forward) pod should
        increase when you move the robot forward. And the Y (strafe) pod should increase when
        you move the robot to the left.
         */
        gobildaOdo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.REVERSED, GoBildaPinpointDriver.EncoderDirection.FORWARD);

        /*
        Before running the robot, recalibrate the IMU. This needs to happen when the robot is stationary
        The IMU will automatically calibrate when first powered on, but recalibrating before running
        the robot is a good idea to ensure that the calibration is "good".
        resetPosAndIMU will reset the position to 0,0,0 and also recalibrate the IMU.
        This is recommended before you run your autonomous, as a bad initial calibration can cause
        an incorrect starting value for x, y, and heading.
         */
        //odo.recalibrateIMU();
        gobildaOdo.resetPosAndIMU();

        telemetry.addData("GoBilda Status", "Initialized");
        telemetry.addData("GoBilda offset", gobildaOdo.getXOffset());
        telemetry.addData("GoBilda offset", gobildaOdo.getYOffset());
        telemetry.addData("GoBilda Device Version Number:", gobildaOdo.getDeviceVersion());
        telemetry.addData("GoBilda Device Scalar", gobildaOdo.getYawScalar());
        telemetry.update();

        waitForStart();
        resetRuntime();

        // Loop until the OpMode ends
        while (opModeIsActive()) {

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
                driveYaw = -gamepad1.right_stick_x;
                if ((Math.abs(driveAxial) < 0.2) && (Math.abs(driveStrafe) < 0.2) && (Math.abs(driveYaw) < 0.2)) {
                    bot.stopDrive();
                } else
                    bot.moveDirection(driveAxial, driveStrafe, -driveYaw);
            }

            /*
            Request a bulk update from the Pinpoint odometry computer. This checks almost all outputs
            from the device in a single I2C read.
             */
            gobildaOdo.bulkUpdate();

            if (gamepad1.a) {
                gobildaOdo.resetPosAndIMU(); //resets the position to 0 and recalibrates the IMU
            }

            if (gamepad1.b) {
                gobildaOdo.recalibrateIMU(); //recalibrates the IMU without resetting position
            }
            // Get the latest position, which includes the x and y coordinates, plus the
            // heading angle
            SparkFunOTOS.Pose2D sparkPos = sparkOdo.getPosition();

            // Reset the tracking if the user requests it
            if (gamepad1.y) {
                sparkOdo.resetTracking();
            }

            // Re-calibrate the IMU if the user requests it
            if (gamepad1.x) {
                sparkOdo.calibrateImu();
            }

            // Inform user of available controls
            telemetry.addLine("Spark Press Y (triangle) on Gamepad to reset tracking");
            telemetry.addLine("Spark Press X (square) on Gamepad to calibrate the IMU");
            telemetry.addLine();

            // Log the position to the telemetry
            telemetry.addData("Spark X coordinate", sparkPos.x);
            telemetry.addData("Spark Y coordinate", sparkPos.y);
            telemetry.addData("Spark Heading angle", sparkPos.h);

            /*
            This code prints the loop frequency of the REV Control Hub. This frequency is affected
            by I2C reads/writes. So it's good to keep an eye on. This code calculates the amount
            of time each cycle takes and finds the frequency (number of updates per second) from
            that cycle time.
             */
            double newTime = getRuntime();
            double loopTime = newTime - oldTime;
            double frequency = 1 / loopTime;
            oldTime = newTime;

            Pose2D gobildaPos = gobildaOdo.getPosition();
            String data = String.format(Locale.US, "{X: %.3f, Y: %.3f, H: %.3f}", gobildaPos.getX(DistanceUnit.INCH), gobildaPos.getY(DistanceUnit.INCH), gobildaPos.getHeading(AngleUnit.DEGREES));
            telemetry.addData("Gobilda Position", data);

            Pose2D vel = gobildaOdo.getVelocity();
            String velocity = String.format(Locale.US, "{XVel: %.3f, YVel: %.3f, HVel: %.3f}", vel.getX(DistanceUnit.INCH), vel.getY(DistanceUnit.INCH), vel.getHeading(AngleUnit.DEGREES));
            telemetry.addData("GoBilda Velocity", velocity);

            telemetry.addData("GoBilda X Encoder:", gobildaOdo.getEncoderX()); //gets the raw data from the X encoder
            telemetry.addData("GoBilda Y Encoder:", gobildaOdo.getEncoderY()); //gets the raw data from the Y encoder
            telemetry.addData("GoBilda Pinpoint Frequency", gobildaOdo.getFrequency()); //prints/gets the current refresh rate of the Pinpoint

            /*
            Gets the Pinpoint device status. Pinpoint can reflect a few states. But we'll primarily see
            READY: the device is working as normal
            CALIBRATING: the device is calibrating and outputs are put on hold
            NOT_READY: the device is resetting from scratch. This should only happen after a power-cycle
            FAULT_NO_PODS_DETECTED - the device does not detect any pods plugged in
            FAULT_X_POD_NOT_DETECTED - The device does not detect an X pod plugged in
            FAULT_Y_POD_NOT_DETECTED - The device does not detect a Y pod plugged in
            */
            telemetry.addData("GoBilda Status", gobildaOdo.getDeviceStatus());

            telemetry.addData("GoBilda REV Hub Frequency: ", frequency); //prints the control system refresh rate
            telemetry.update();
        }
    }

    private void configureOtos() {
        telemetry.addLine("Spark Configuring OTOS...");
        telemetry.update();

        // Set the desired units for linear and angular measurements. Can be either
        // meters or inches for linear, and radians or degrees for angular. If not
        // set, the default is inches and degrees. Note that this setting is not
        // persisted in the sensor, so you need to set at the start of all your
        // OpModes if using the non-default value.
        // sparkOdo.setLinearUnit(DistanceUnit.METER);
        sparkOdo.setLinearUnit(DistanceUnit.INCH);
        // sparkOdo.setAngularUnit(AnguleUnit.RADIANS);
        sparkOdo.setAngularUnit(AngleUnit.DEGREES);

        // Assuming you've mounted your sensor to a robot and it's not centered,
        // you can specify the offset for the sensor relative to the center of the
        // robot. The units default to inches and degrees, but if you want to use
        // different units, specify them before setting the offset! Note that as of
        // firmware version 1.0, these values will be lost after a power cycle, so
        // you will need to set them each time you power up the sensor. For example, if
        // the sensor is mounted 5 inches to the left (negative X) and 10 inches
        // forward (positive Y) of the center of the robot, and mounted 90 degrees
        // clockwise (negative rotation) from the robot's orientation, the offset
        // would be {-5, 10, -90}. These can be any value, even the angle can be
        // tweaked slightly to compensate for imperfect mounting (eg. 1.3 degrees).
        SparkFunOTOS.Pose2D offset = new SparkFunOTOS.Pose2D(-.25, -5.2, 0);
        sparkOdo.setOffset(offset);

        // Here we can set the linear and angular scalars, which can compensate for
        // scaling issues with the sensor measurements. Note that as of firmware
        // version 1.0, these values will be lost after a power cycle, so you will
        // need to set them each time you power up the sensor. They can be any value
        // from 0.872 to 1.127 in increments of 0.001 (0.1%). It is recommended to
        // first set both scalars to 1.0, then calibrate the angular scalar, then
        // the linear scalar. To calibrate the angular scalar, spin the robot by
        // multiple rotations (eg. 10) to get a precise error, then set the scalar
        // to the inverse of the error. Remember that the angle wraps from -180 to
        // 180 degrees, so for example, if after 10 rotations counterclockwise
        // (positive rotation), the sensor reports -15 degrees, the required scalar
        // would be 3600/3585 = 1.004. To calibrate the linear scalar, move the
        // robot a known distance and measure the error; do this multiple times at
        // multiple speeds to get an average, then set the linear scalar to the
        // inverse of the error. For example, if you move the robot 100 inches and
        // the sensor reports 103 inches, set the linear scalar to 100/103 = 0.971
        sparkOdo.setLinearScalar(1.09);
        sparkOdo.setAngularScalar(0.9873);

        // The IMU on the OTOS includes a gyroscope and accelerometer, which could
        // have an offset. Note that as of firmware version 1.0, the calibration
        // will be lost after a power cycle; the OTOS performs a quick calibration
        // when it powers up, but it is recommended to perform a more thorough
        // calibration at the start of all your OpModes. Note that the sensor must
        // be completely stationary and flat during calibration! When calling
        // calibrateImu(), you can specify the number of samples to take and whether
        // to wait until the calibration is complete. If no parameters are provided,
        // it will take 255 samples and wait until done; each sample takes about
        // 2.4ms, so about 612ms total
        sparkOdo.calibrateImu();

        // Reset the tracking algorithm - this resets the position to the origin,
        // but can also be used to recover from some rare tracking errors
        sparkOdo.resetTracking();

        // After resetting the tracking, the OTOS will report that the robot is at
        // the origin. If your robot does not start at the origin, or you have
        // another source of location information (eg. vision odometry), you can set
        // the OTOS location to match and it will continue to track from there.
        SparkFunOTOS.Pose2D currentPosition = new SparkFunOTOS.Pose2D(0, 0, 0);
        sparkOdo.setPosition(currentPosition);

        // Get the hardware and firmware version
        SparkFunOTOS.Version hwVersion = new SparkFunOTOS.Version();
        SparkFunOTOS.Version fwVersion = new SparkFunOTOS.Version();
        sparkOdo.getVersionInfo(hwVersion, fwVersion);

        telemetry.addLine("Spark OTOS configured! Press start to get position data!");
        telemetry.addLine();
        telemetry.addLine(String.format("Spark OTOS Hardware Version: v%d.%d", hwVersion.major, hwVersion.minor));
        telemetry.addLine(String.format("Spark OTOS Firmware Version: v%d.%d", fwVersion.major, fwVersion.minor));
        telemetry.update();
    }
}

