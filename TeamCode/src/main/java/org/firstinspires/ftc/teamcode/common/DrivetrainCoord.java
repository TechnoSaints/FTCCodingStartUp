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
        axialPid = new PIDController(0.1,0.1,0.1);
        strafePid = new PIDController(0.1,0.1,0.1);
        yawPid = new PIDController(0.1,0.1,0.1);
    }

    protected void moveToPose(Pose2D targetPose) {
        Pose2D currentPose;
        while (true)
        {
            odo.update();
            currentPose = odo.getPosition();
            targetAxial = axialPid.calculate(targetPose.getX(DistanceUnit.INCH),currentPose.getX(DistanceUnit.INCH));
            targetStrafe = strafePid.calculate(targetPose.getY(DistanceUnit.INCH), currentPose.getY(DistanceUnit.INCH));
            targetYaw = yawPid.calculate(targetPose.getHeading(AngleUnit.DEGREES),currentPose.getHeading(AngleUnit.DEGREES));

            moveDirection(targetAxial, targetStrafe, targetYaw);
        }
    }

    protected void setPose(Pose2D pose)
    {
        odo.setPosition(pose);
    }
}