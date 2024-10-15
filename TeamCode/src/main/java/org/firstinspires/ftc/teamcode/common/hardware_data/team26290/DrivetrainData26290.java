package org.firstinspires.ftc.teamcode.common.hardware_data.team26290;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.common.hardware_data.DrivetrainData;

public class DrivetrainData26290 extends DrivetrainData {
    public DrivetrainData26290() {
        maxNormalPower = 0.7;
        maxCreepPower = 0.25;
        leftFrontDirection = DcMotorSimple.Direction.REVERSE;
        leftBackDirection = DcMotorSimple.Direction.REVERSE;
        rightFrontDirection = DcMotorSimple.Direction.FORWARD;
        rightBackDirection = DcMotorSimple.Direction.FORWARD;
    }
}
