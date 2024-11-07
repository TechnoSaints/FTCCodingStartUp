package org.firstinspires.ftc.teamcode.common.hardware_data.team21527;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.common.hardware_data.DrivetrainData;

public class DrivetrainData21527 extends DrivetrainData {
    public DrivetrainData21527() {
        maxFastPower = 0.5;
        maxSlowPower = 0.25;
        leftFrontDirection = DcMotorSimple.Direction.REVERSE;
        leftBackDirection = DcMotorSimple.Direction.REVERSE;
        rightFrontDirection = DcMotorSimple.Direction.FORWARD;
        rightBackDirection = DcMotorSimple.Direction.FORWARD;
    }
}
