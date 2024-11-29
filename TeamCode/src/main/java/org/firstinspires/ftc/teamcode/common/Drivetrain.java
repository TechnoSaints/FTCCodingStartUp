package org.firstinspires.ftc.teamcode.common;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardware_data.MotorData;

import org.firstinspires.ftc.teamcode.drive.ourveryown.GoBildaPinpointDriver;


public class Drivetrain extends Component {
    protected final DcMotorEx leftFrontDrive;
    protected final DcMotorEx rightFrontDrive;
    protected final DcMotorEx leftBackDrive;
    protected final DcMotorEx rightBackDrive;
    protected final double maxFastPower;
    protected final double maxMediumPower;
    protected final double maxSlowPower;
    protected double currentPower;
    protected double maxVelocity;
    //    private IMU imu;
    protected LinearOpMode opMode;
    private final double headingThreshold = 0.5;
    private final double turnGain = 0.02;
    private final double driveGain = 0.03;
    private final double ticksPerInch;
    GoBildaPinpointDriver odo; // Declare OpMode member for the Odometry Computer

    public Drivetrain(LinearOpMode opMode, Telemetry telemetry, DrivetrainData drivetrainData, MotorData motorData) {
        super(telemetry);
        this.opMode = opMode;
        maxFastPower = drivetrainData.maxFastPower;
        maxMediumPower = drivetrainData.maxMediumPower;
        maxSlowPower = drivetrainData.maxSlowPower;
        maxVelocity = motorData.maxTicksPerSec;
        ticksPerInch = motorData.wheelTicksPerInch;

        leftFrontDrive = opMode.hardwareMap.get(DcMotorEx.class, "leftFrontDrive");
        leftBackDrive = opMode.hardwareMap.get(DcMotorEx.class, "leftBackDrive");
        rightFrontDrive = opMode.hardwareMap.get(DcMotorEx.class, "rightFrontDrive");
        rightBackDrive = opMode.hardwareMap.get(DcMotorEx.class, "rightBackDrive");

        leftFrontDrive.setDirection(drivetrainData.leftFrontDirection);
        leftBackDrive.setDirection(drivetrainData.leftBackDirection);
        rightFrontDrive.setDirection(drivetrainData.rightFrontDirection);
        rightBackDrive.setDirection(drivetrainData.rightBackDirection);

        stopAndResetEncoders();
        setRunUsingEncoder();
        setBrakingOn();
        setToFastPower();


        odo = opMode.hardwareMap.get(GoBildaPinpointDriver.class, "odo");
        /*
        Set the odometry pod positions relative to the point that the odometry computer tracks around.
        The X pod offset refers to how far sideways from the tracking point the
        X (forward) odometry pod is. Left of the center is a positive number,
        right of center is a negative number. the Y pod offset refers to how far forwards from
        the tracking point the Y (strafe) odometry pod is. forward of center is a positive number,
        backwards is a negative number.
         */
        odo.setOffsets(-108.0, 48.0); //these are tuned for 3110-0002-0001 Product Insight #1

        /*
        Set the kind of pods used by your robot. If you're using goBILDA odometry pods, select either
        the goBILDA_SWINGARM_POD, or the goBILDA_4_BAR_POD.
        If you're using another kind of odometry pod, uncomment setEncoderResolution and input the
        number of ticks per mm of your odometry pod.
         */
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        //odo.setEncoderResolution(13.26291192);


        /*
        Set the direction that each of the two odometry pods count. The X (forward) pod should
        increase when you move the robot forward. And the Y (strafe) pod should increase when
        you move the robot to the left.
         */
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.REVERSED, GoBildaPinpointDriver.EncoderDirection.FORWARD);

        odo.resetPosAndIMU();
    }

    protected void setToFastPower() {
        currentPower = maxFastPower;
    }

    protected void setToMediumPower() {
        currentPower = maxMediumPower;
    }

    protected void setToSlowPower() {
        currentPower = maxSlowPower;
    }

    public void turnToHeading(double targetHeading) {
        double turnSpeed = maxMediumPower;
        double headingError = getHeadingError(targetHeading);

        // keep looping while we are still active, and not on heading.
        while (Math.abs(headingError) > headingThreshold) {
            headingError = getHeadingError(targetHeading);

            // Determine required steering to keep on heading
            turnSpeed = getSteeringCorrection(headingError, turnGain);

            // Clip the speed to the maximum permitted value.
            turnSpeed = Range.clip(turnSpeed, -maxMediumPower, maxMediumPower);

            // Pivot in place by applying the turning correction
            moveDirection(0, 0, turnSpeed);

            telemetry.addData("Actual Heading: ", getHeading());
            telemetry.addData("Target Heading: ", targetHeading);
            telemetry.addData("headingError: ", headingError);
            telemetry.addData("turnSpeed: ", turnSpeed);
            telemetry.update();
        }
        stop();
    }

    public void turnForDistance(double distance) {
        double targetHeading = getHeading() + distance;
        turnToHeading(targetHeading);
    }

    public void creepDirection(double axial, double strafe, double yaw) {
        moveDirection(axial * maxSlowPower, strafe * maxSlowPower, yaw * maxSlowPower);
    }

    public void creepDirectionNoEnc(double axial, double strafe, double yaw) {
        moveDirection(axial * maxSlowPower, strafe * maxSlowPower, yaw * maxSlowPower);
    }

    protected void moveDirection(double axial, double strafe, double yaw) {
        // Calculate wheel powers.
        double leftFrontPower = axial - strafe - yaw;
        double rightFrontPower = axial + strafe + yaw;
        double leftBackPower = axial + strafe - yaw;
        double rightBackPower = axial - strafe + yaw;

        // Normalize wheel powers to be less than 1.0
        double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }
/*
        telemetry.addData("leftFrontPower: ", leftFrontPower);
        telemetry.addData("currentPower: ", currentPower);
        telemetry.addData("maxVelocity: ", maxVelocity);
        telemetry.update();
*/
        leftFrontDrive.setVelocity(leftFrontPower * currentPower * maxVelocity);
        rightFrontDrive.setVelocity(rightFrontPower * currentPower * maxVelocity);
        leftBackDrive.setVelocity(leftBackPower * currentPower * maxVelocity);
        rightBackDrive.setVelocity(rightBackPower * currentPower * maxVelocity);
//        log();
    }

    public void strafeRightForDistance(double distance) {
        int targetCounts = (int) (-distance * ticksPerInch);
        int leftFrontTarget = 0;
        int leftBackTarget = 0;
        int rightFrontTarget = 0;
        int rightBackTarget = 0;
        double strafeSpeed = maxMediumPower;

        leftFrontTarget = leftFrontDrive.getCurrentPosition() + targetCounts;
        leftBackTarget = leftBackDrive.getCurrentPosition() - targetCounts;
        rightFrontTarget = rightFrontDrive.getCurrentPosition() - targetCounts;
        rightBackTarget = rightBackDrive.getCurrentPosition() + targetCounts;

        leftFrontDrive.setTargetPosition(leftFrontTarget);
        leftBackDrive.setTargetPosition(leftBackTarget);
        rightFrontDrive.setTargetPosition(rightFrontTarget);
        rightBackDrive.setTargetPosition(rightBackTarget);

        setRunToPosition();

        while (leftFrontDrive.isBusy() && leftBackDrive.isBusy() && rightFrontDrive.isBusy() && rightBackDrive.isBusy() && !opMode.isStopRequested()) {
            moveDirection(0, strafeSpeed, 0);
        }
        stop();
        setRunUsingEncoder();
    }

    public void moveForwardForDistance(double distance) {
        moveForwardForDistance(distance, maxMediumPower);
    }

    public void creepForwardForDistance(double distance) {
        moveForwardForDistance(distance, maxSlowPower);
    }

    private void moveForwardForDistance(double distance, double driveSpeed) {
        int targetCounts = (int) (-distance * ticksPerInch);
        int leftFrontTarget = 0;
        int leftBackTarget = 0;
        int rightFrontTarget = 0;
        int rightBackTarget = 0;
        double turnSpeed = 0;
        double headingError = 0;
        double targetHeading = getHeading();

        leftFrontTarget = leftFrontDrive.getCurrentPosition() + targetCounts;
        leftBackTarget = leftBackDrive.getCurrentPosition() + targetCounts;
        rightFrontTarget = rightFrontDrive.getCurrentPosition() + targetCounts;
        rightBackTarget = rightBackDrive.getCurrentPosition() + targetCounts;

        leftFrontDrive.setTargetPosition(leftFrontTarget);
        leftBackDrive.setTargetPosition(leftBackTarget);
        rightFrontDrive.setTargetPosition(rightFrontTarget);
        rightBackDrive.setTargetPosition(rightBackTarget);

        setRunToPosition();

        while (leftFrontDrive.isBusy() && leftBackDrive.isBusy() && rightFrontDrive.isBusy() && rightBackDrive.isBusy() && !opMode.isStopRequested()) {
            //while (leftFrontDrive.isBusy()) {
            headingError = getHeadingError(targetHeading);
            // Determine required steering to keep on heading
            turnSpeed = getSteeringCorrection(headingError, driveGain);

            // if driving in reverse, the motor correction also needs to be reversed
            if (distance < 0)
                turnSpeed *= -1.0;

            // Apply the turning correction to the current driving speed.
            moveDirection(driveSpeed, 0.0, -turnSpeed);
            //           log();
        }
        stop();
        setRunUsingEncoder();
    }

    private double getHeadingError(double targetHeading) {
        return (targetHeading - getHeading());
    }

    private double getSteeringCorrection(double headingError, double gain) {
        // Determine the heading current error

        // Normalize the error to be within +/- 180 degrees
        while (headingError > 180) headingError -= 360;
        while (headingError <= -180) headingError += 360;

        // Multiply the error by the gain to determine the required steering correction/  Limit the result to +/- 1.0
        return Range.clip(headingError * gain, -1, 1);
    }


    protected double getHeading() {
        //       return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

        odo.update(GoBildaPinpointDriver.readData.ONLY_UPDATE_HEADING);
        double heading = Math.toDegrees(odo.getHeading());
        while (heading > 180) heading -= 360;
        while (heading <= -180) heading += 360;
        return (heading);
    }

    public void stop() {
        leftFrontDrive.setVelocity(0.0);
        leftBackDrive.setVelocity(0.0);
        rightFrontDrive.setVelocity(0.0);
        rightBackDrive.setVelocity(0.0);
    }

    private void setBrakingOn() {
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void setBrakingOff() {
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    private void stopAndResetEncoders() {
        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    protected void setRunWithoutEncoders() {
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    private void setRunUsingEncoder() {
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void setRunToPosition() {
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void log() {
        telemetry.addData("leftFrontDrive Position: ", leftFrontDrive.getCurrentPosition());
        telemetry.addData("leftFrontDrive Target: ", leftFrontDrive.getTargetPosition());
        telemetry.addData("leftFrontDrive Velocity: ", leftFrontDrive.getVelocity());
        telemetry.update();
    }
}