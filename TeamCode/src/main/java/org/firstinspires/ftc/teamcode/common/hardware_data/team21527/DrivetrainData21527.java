package org.firstinspires.ftc.teamcode.common.hardware_data.team21527;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.common.hardware_data.DrivetrainData;

public class DrivetrainData21527 extends DrivetrainData {
    public DrivetrainData21527() {
        maxNormalPower = 0.75;
        maxCreepPower = 0.25;
        leftFrontDirection = DcMotorSimple.Direction.REVERSE;
        leftBackDirection = DcMotorSimple.Direction.FORWARD;
        rightFrontDirection = DcMotorSimple.Direction.REVERSE;
        rightBackDirection = DcMotorSimple.Direction.FORWARD;
    }
}
