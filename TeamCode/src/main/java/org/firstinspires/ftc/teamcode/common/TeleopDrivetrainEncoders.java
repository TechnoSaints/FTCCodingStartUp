package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardware_data.MotorData;

public class TeleopDrivetrainEncoders extends TeleopDrivetrain {
    private double maxVelocity;

    public TeleopDrivetrainEncoders(HardwareMap hardwareMap, Telemetry telemetry, DrivetrainData drivetrainData, MotorData motorData) {
        super(hardwareMap, telemetry, drivetrainData);
        maxVelocity = motorData.maxTicksPerSec;

        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        stop();
    }

    public void creepDirection(double axial, double strafe, double yaw) {
        setToCreepPower();
        moveDirection(axial, strafe, yaw);
        setToNormalPower();
    }

    public void moveDirection(double axial, double strafe, double yaw) {
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

        leftFrontDrive.setVelocity(leftFrontPower * maxVelocity * currentPower);
        rightFrontDrive.setPower(rightFrontPower * maxVelocity * currentPower);
        leftBackDrive.setPower(leftBackPower * maxVelocity * currentPower);
        rightBackDrive.setPower(rightBackPower * maxVelocity * currentPower);

        telemetry.addData("maxVelocity * currentPower: ", currentPower * maxVelocity);
        telemetry.addData("leftFrontPower: ", leftFrontPower);
        telemetry.addData("leftFrontVelocity: ", leftFrontDrive.getVelocity());
        telemetry.addData("leftBackPower: ", leftBackPower);
        telemetry.addData("leftBackVelocity: ", rightBackDrive.getVelocity());
        telemetry.addData("rightFrontPower: ", leftFrontPower);
        telemetry.addData("rightFrontVelocity: ", rightFrontDrive.getVelocity());
        telemetry.addData("rightBackPower: ", rightBackPower);
        telemetry.addData("rightBackVelocity: ", rightBackDrive.getVelocity());
        telemetry.update();

    }

    public void stop() {
        leftFrontDrive.setVelocity(0.0);
        leftBackDrive.setVelocity(0.0);
        rightFrontDrive.setVelocity(0.0);
        rightBackDrive.setVelocity(0.0);
    }
}