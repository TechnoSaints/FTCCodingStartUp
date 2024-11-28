package org.firstinspires.ftc.teamcode.common;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.common.hardware_data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardware_data.MotorData;
import org.firstinspires.ftc.teamcode.drive.ourveryown.GoBildaPinpointDriver;

public class DrivetrainCoord extends Drivetrain {
    private PIDController axialPid, strafePid, yawPid;
    private double targetAxial, targetStrafe, targetYaw;

    public DrivetrainCoord(LinearOpMode opMode, Telemetry telemetry, DrivetrainData drivetrainData, MotorData motorData) {
        super(opMode, telemetry, drivetrainData, motorData);
        axialPid = new PIDController(0.5, 0.0, 0.0);
        axialPid.setTolerance(0.1);
        strafePid = new PIDController(0.5, 0.0, 0.0);
        strafePid.setTolerance(0.1);
        yawPid = new PIDController(0.5, 0.0, 0.2);
        yawPid.setTolerance(0.25);
        setRunWithoutEncoders();
    }

    protected void moveToPose(Pose2D targetPose) {
        currentPower = 0.5;
        Pose2D currentPose;
        axialPid.setSetPoint(targetPose.getX(DistanceUnit.INCH));
        strafePid.setSetPoint(targetPose.getY(DistanceUnit.INCH));
        yawPid.setSetPoint(targetPose.getHeading(AngleUnit.DEGREES));
        while (!axialPid.atSetPoint() || !strafePid.atSetPoint() || !yawPid.atSetPoint()) {
            odo.update();
            currentPose = odo.getPosition();
            targetAxial = axialPid.calculate(currentPose.getX(DistanceUnit.INCH));
            targetStrafe = strafePid.calculate(currentPose.getY(DistanceUnit.INCH));
            targetYaw = yawPid.calculate(normalizeHeading((currentPose.getHeading(AngleUnit.DEGREES))));

            targetAxial = targetAxial * Math.cos(currentPose.getHeading(AngleUnit.RADIANS)) - targetStrafe * Math.sin(currentPose.getHeading(AngleUnit.RADIANS));
            targetStrafe = targetAxial * Math.sin(currentPose.getHeading(AngleUnit.RADIANS)) + targetStrafe * Math.cos(currentPose.getHeading(AngleUnit.RADIANS));
            setMotorPowers(targetAxial, targetStrafe, targetYaw);

            telemetry.addData("axialTarget: ", targetPose.getX(DistanceUnit.INCH));
            telemetry.addData("axialCurrent: ", currentPose.getX(DistanceUnit.INCH));
            telemetry.addData("axialTargetPower: ", targetAxial);

            telemetry.addData("strafeTarget: ", targetPose.getY(DistanceUnit.INCH));
            telemetry.addData("strafeCurrent: ", currentPose.getY(DistanceUnit.INCH));
            telemetry.addData("strafeTargetPower: ", targetStrafe);

            telemetry.addData("yawTarget: ", targetPose.getHeading(AngleUnit.DEGREES));
            telemetry.addData("yawCurrent: ", currentPose.getHeading(AngleUnit.DEGREES));
            telemetry.addData("yawTargetPower: ", targetYaw);

            telemetry.update();
        }
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
        leftFrontDrive.setPower(leftFrontPower * currentPower);
        rightFrontDrive.setPower(rightFrontPower * currentPower);
        leftBackDrive.setPower(leftBackPower * currentPower);
        rightBackDrive.setPower(rightBackPower * currentPower);
    }

    protected double normalizeHeading(double heading) {
        while (heading > 180) heading -= 360;
        while (heading <= -180) heading += 360;
        return (heading);
    }

}