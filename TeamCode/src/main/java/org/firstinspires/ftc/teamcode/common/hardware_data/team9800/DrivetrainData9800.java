package org.firstinspires.ftc.teamcode.common.hardware_data.team9800;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.common.hardware_data.DrivetrainData;

public class DrivetrainData9800 extends DrivetrainData {
    public DrivetrainData9800() {
        maxFastPower = 0.65;
        maxSlowPower = 0.25;
        leftFrontDirection = DcMotorSimple.Direction.REVERSE;
        leftBackDirection = DcMotorSimple.Direction.REVERSE;
        rightFrontDirection = DcMotorSimple.Direction.REVERSE;
        rightBackDirection = DcMotorSimple.Direction.REVERSE;
    }
}
