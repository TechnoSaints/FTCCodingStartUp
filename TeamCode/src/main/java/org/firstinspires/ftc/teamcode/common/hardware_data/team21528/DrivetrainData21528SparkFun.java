package org.firstinspires.ftc.teamcode.common.hardware_data.team21528;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.common.hardware_data.DrivetrainData;

public class DrivetrainData21528SparkFun extends DrivetrainData {
    public DrivetrainData21528SparkFun() {
        maxNormalPower = 0.65;
        maxCreepPower = 0.25;
        leftFrontDirection = DcMotorSimple.Direction.REVERSE;
        leftBackDirection = DcMotorSimple.Direction.FORWARD;
        rightFrontDirection = DcMotorSimple.Direction.FORWARD;
        rightBackDirection = DcMotorSimple.Direction.REVERSE;
    }
}
