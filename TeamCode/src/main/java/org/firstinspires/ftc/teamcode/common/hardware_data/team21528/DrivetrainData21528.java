package org.firstinspires.ftc.teamcode.common.hardware_data.team21528;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.common.hardware_data.DrivetrainData;

public class DrivetrainData21528 extends DrivetrainData {
    public DrivetrainData21528() {
        maxNormalPower = 0.65;
        maxCreepPower = 0.25;
        leftFrontDirection = DcMotorSimple.Direction.FORWARD;
        leftBackDirection = DcMotorSimple.Direction.REVERSE;
        rightFrontDirection = DcMotorSimple.Direction.FORWARD;
        rightBackDirection = DcMotorSimple.Direction.REVERSE;
    }
}
