package org.firstinspires.ftc.teamcode.common;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.common.hardware_data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardware_data.MotorData;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.GoBildaPinpointDriver;

import java.util.concurrent.TimeUnit;

public class DrivetrainCoord extends Drivetrain {
    private Pose2D currentPose;
    private PIDFController axialPidf, strafePidf, yawPidf;
    private double targetAxial, targetStrafe, targetYaw, axialDistance, strafeDistance, yawDistance;
    private double startYaw;

    public DrivetrainCoord(LinearOpMode opMode, Telemetry telemetry, DrivetrainData drivetrainData, MotorData motorData) {
        super(opMode, telemetry, drivetrainData, motorData);
        axialPidf = new PIDFController(0.75, 0.1, 0.65, 0);
        axialPidf.setTolerance(0.25);
        strafePidf = new PIDFController(0.85, 0.1, 0.65, 0);
        strafePidf.setTolerance(0.25);
        yawPidf = new PIDFController(0.25, 0.1, 0.01, 0);
        yawPidf.setTolerance(0.5);
        setRunWithoutEncoders();
        setToMediumPower();
        setBrakingOn();
    }

    protected void moveToPose(Pose2D targetPose) {
        ElapsedTime timer = new ElapsedTime();
        axialPidf.setSetPoint(targetPose.getX(DistanceUnit.INCH));
        strafePidf.setSetPoint(targetPose.getY(DistanceUnit.INCH));
        yawPidf.setSetPoint(targetPose.getHeading(AngleUnit.DEGREES));
        odo.update();
        currentPose = odo.getPosition();
        axialDistance = targetPose.getX(DistanceUnit.INCH) - currentPose.getX(DistanceUnit.INCH);
        strafeDistance = targetPose.getY(DistanceUnit.INCH) - currentPose.getY(DistanceUnit.INCH);
        yawDistance = targetPose.getHeading(AngleUnit.DEGREES) - getCurrentHeadingDeg();
        startYaw = getCurrentHeadingDeg();

        timer.reset();
        //       while (true) {
        while (!axialPidf.atSetPoint() || !strafePidf.atSetPoint() || !yawPidf.atSetPoint()) {
            odo.update();
            currentPose = odo.getPosition();
            /*
            targetAxial = axialPid.calculate(currentPose.getX(DistanceUnit.INCH), profileTargetPosition(2, 5, axialDistance, timer.time(TimeUnit.SECONDS)));
            targetStrafe = strafePid.calculate(currentPose.getY(DistanceUnit.INCH), profileTargetPosition(2, 5, strafeDistance, timer.time(TimeUnit.SECONDS)));
            targetYaw = yawPid.calculate(normalizeHeading(getCurrentHeading()));
*/
            targetAxial = axialPidf.calculate(currentPose.getX(DistanceUnit.INCH));
            targetStrafe = strafePidf.calculate(currentPose.getY(DistanceUnit.INCH));
            targetYaw = yawPidf.calculate(getCurrentHeadingDeg());

//            Vector2d vec = new Vector2d(targetAxial, targetStrafe);
//            vec = vec.rotateBy(currentPose.getHeading(AngleUnit.DEGREES) - startYaw);
//            targetAxial = vec.getX();
//            targetStrafe = vec.getY();
            double angleDeg = startYaw - getCurrentHeadingDeg();
            double angleRad = Math.toRadians(angleDeg);
            double cosAngle = Math.cos(angleRad);
            double sinAngle = Math.sin(angleRad);
            targetAxial = targetAxial * cosAngle - targetStrafe * sinAngle;
            targetStrafe = targetAxial * sinAngle + targetStrafe * cosAngle;
            //setMotorPowers(0, 0, targetYaw);
            setMotorPowers(targetAxial, targetStrafe, targetYaw);
            logScreen();
        }
        stopPower();
    }

    protected void setPose(Pose2D pose) {
        odo.setPosition(pose);
    }

    private void setMotorPowers(double axial, double strafe, double yaw) {
        // Calculate wheel powers.
        double leftFrontPower = -axial + strafe - yaw;
        double rightFrontPower = -axial - strafe + yaw;
        double leftBackPower = -axial - strafe - yaw;
        double rightBackPower = -axial + strafe + yaw;

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
        telemetry.addData("leftFrontPower*cur: ", leftFrontPower * currentPower);
        telemetry.addData("rightFrontPower*cur: ", rightFrontPower * currentPower);
        telemetry.addData("leftBackPower*cur: ", leftBackPower * currentPower);
        telemetry.addData("rightBackPower*cur: ", rightBackPower * currentPower);

        leftFrontDrive.setPower(leftFrontPower * currentPower);
        rightFrontDrive.setPower(rightFrontPower * currentPower);
        leftBackDrive.setPower(leftBackPower * currentPower);
        rightBackDrive.setPower(rightBackPower * currentPower);
    }

    double profileTargetPosition(double maxAccel, double maxVel, double distance, double elapsedTime) {

        // Calculate the time it takes to accelerate to max velocity
        double accelDuration = maxVel / maxAccel;

        // If we can't accelerate to max velocity in the given distance, we'll accelerate as much as possible
        double halfwayDistance = distance / 2;
        double accelDistance = 0.5 * maxAccel * Math.pow(accelDuration, 2.0);

        if (accelDistance > halfwayDistance) {
            accelDuration = Math.sqrt(halfwayDistance / (0.5 * maxAccel));
        }

        accelDistance = 0.5 * maxAccel * Math.pow(accelDuration, 2.0);

        // recalculate max velocity based on the time we have to accelerate and decelerate
        maxVel = maxAccel * accelDuration;

        // we decelerate at the same rate as we accelerate
        double decelDuration = accelDuration;

        // calculate the time that we're at max velocity
        double cruiseDistance = distance - 2.0 * accelDistance;
        double cruiseDuration = cruiseDistance / maxVel;
        double beginDecelTime = accelDuration + cruiseDuration;

        // check if we're no longer in the motion profile
        double entireMoveTime = accelDuration + cruiseDuration + decelDuration;
        if (elapsedTime > entireMoveTime) {
            return distance;
        }
        // if we're accelerating
        else if (elapsedTime < accelDuration) {
            // use the kinematic equation for acceleration
            return (0.5 * maxAccel * Math.pow(elapsedTime, 2.0));
        }
        // if we're cruising
        else if (elapsedTime < beginDecelTime) {
            accelDistance = 0.5 * maxAccel * Math.pow(accelDuration, 2.0);
            double cruiseCurrentDuration = elapsedTime - accelDuration;
            // use the kinematic equation for constant velocity
            return accelDistance + maxVel * cruiseCurrentDuration;
        }
        // if we're decelerating
        else {
            accelDistance = 0.5 * maxAccel * Math.pow(accelDuration, 2.0);
            cruiseDistance = maxVel * cruiseDuration;
            decelDuration = elapsedTime - beginDecelTime;

            // use the kinematic equations to calculate the instantaneous desired position
            return (accelDistance + cruiseDistance + maxVel * decelDuration - 0.5 * maxAccel * Math.pow(decelDuration, 2.0));
        }
    }

    protected double getCurrentHeadingDeg() {
        double heading = Math.toDegrees(odo.getHeading());
        while (heading > 180) heading -= 360;
        while (heading <= -180) heading += 360;
        return (heading);
    }
    protected void stopPower() {
        leftFrontDrive.setPower(0.0);
        rightFrontDrive.setPower(0.0);
        leftBackDrive.setPower(0.0);
        rightBackDrive.setPower(0.0);
    }

    void logScreen() {
        telemetry.addData("axialTarget: ", axialPidf.getSetPoint());
        telemetry.addData("axialCurrent: ", currentPose.getX(DistanceUnit.INCH));
        telemetry.addData("axialTargetPower: ", targetAxial);

        telemetry.addData("strafeTarget: ", strafePidf.getSetPoint());
        telemetry.addData("strafeCurrent: ", currentPose.getY(DistanceUnit.INCH));
        telemetry.addData("strafeTargetPower: ", targetStrafe);

        telemetry.addData("yawTarget: ", yawPidf.getSetPoint());
        telemetry.addData("yawCurrent: ", getCurrentHeadingDeg());
        telemetry.addData("yawTargetPower: ", targetYaw);

        telemetry.update();
    }

    void logFile() {
        RobotLog.d("-----------------------------");
        RobotLog.d("BB:axialTarget: " + Double.toString(axialPidf.getSetPoint()));
        RobotLog.d("BB:axialCurrent: " + Double.toString(currentPose.getX(DistanceUnit.INCH)));
        RobotLog.d("BB:axialTargetPower: " + Double.toString(targetAxial));

        RobotLog.d("BB:strafeTarget: " + Double.toString(strafePidf.getSetPoint()));
        RobotLog.d("BB:strafeCurrent: " + Double.toString(currentPose.getY(DistanceUnit.INCH)));
        RobotLog.d("BB:strafeTargetPower: " + Double.toString(targetStrafe));

        RobotLog.d("BB:yawTarget: " + Double.toString(yawPidf.getSetPoint()));
        RobotLog.d("BB:yawCurrent: " + Double.toString(getCurrentHeadingDeg()));
        RobotLog.d("BB:yawTargetPower: " + Double.toString(targetYaw));
    }
}