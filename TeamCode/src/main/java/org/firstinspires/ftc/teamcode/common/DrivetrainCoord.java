package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardware_data.MotorData;

public class DrivetrainCoord extends Drivetrain {

    public DrivetrainCoord(LinearOpMode opMode, Telemetry telemetry, DrivetrainData drivetrainData, MotorData motorData)
    {
        super(opMode, telemetry, drivetrainData, motorData);
    }

}
