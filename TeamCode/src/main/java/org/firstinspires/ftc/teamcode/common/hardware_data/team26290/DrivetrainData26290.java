package org.firstinspires.ftc.teamcode.common.hardware_data.team26290;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.common.hardware_data.DrivetrainData;

public class DrivetrainData26290 extends DrivetrainData {
    public DrivetrainData26290() {
        maxMediumPower = 1;
        maxFastPower = 0.7;
        maxSlowPower = 0.25;
        leftFrontDirection = DcMotorSimple.Direction.FORWARD;
        leftBackDirection = DcMotorSimple.Direction.FORWARD;
        rightFrontDirection = DcMotorSimple.Direction.REVERSE;
        rightBackDirection = DcMotorSimple.Direction.REVERSE;
    }
}
