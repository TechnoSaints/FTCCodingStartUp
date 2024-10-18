package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.DrivetrainData;

public class TeleopDrivetrain extends Drivetrain {
    private double leftFrontFactor = 1.0;
    private double rightFrontFactor = 1.0;
    private double leftBackFactor = 1.0;
    private double rightBackFactor = 1.0;

    public TeleopDrivetrain(HardwareMap hardwareMap, Telemetry telemetry, DrivetrainData drivetrainData) {
        super(hardwareMap,telemetry, drivetrainData);
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

        leftFrontDrive.setPower(leftFrontPower * maxPower * leftFrontFactor);
        rightFrontDrive.setPower(rightFrontPower * maxPower * rightFrontFactor);
        leftBackDrive.setPower(leftBackPower * maxPower * leftBackFactor);
        rightBackDrive.setPower(rightBackPower * maxPower * rightBackFactor);
    }

    public void stop() {
        leftFrontDrive.setPower(0.0);
        leftBackDrive.setPower(0.0);
        rightFrontDrive.setPower(0.0);
        rightBackDrive.setPower(0.0);
    }
}