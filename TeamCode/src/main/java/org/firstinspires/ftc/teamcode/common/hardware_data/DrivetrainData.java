package org.firstinspires.ftc.teamcode.common.hardware_data;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public abstract class DrivetrainData {
    public DcMotorSimple.Direction leftFrontDirection = DcMotorSimple.Direction.FORWARD;
    public DcMotorSimple.Direction leftBackDirection = DcMotorSimple.Direction.REVERSE;
    public DcMotorSimple.Direction rightFrontDirection = DcMotorSimple.Direction.FORWARD;
    public DcMotorSimple.Direction rightBackDirection = DcMotorSimple.Direction.REVERSE;
}

