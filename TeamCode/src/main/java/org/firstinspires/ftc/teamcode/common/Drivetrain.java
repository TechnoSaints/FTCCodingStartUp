package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.DrivetrainData;

public class Drivetrain extends Component {
    public final DcMotorEx leftFrontDrive;
    public final DcMotorEx rightFrontDrive;
    public final DcMotorEx leftBackDrive;
    public final DcMotorEx rightBackDrive;
    protected final double maxNormalPower;
    protected final double maxCreepPower;
    protected double maxPower;

    protected Drivetrain(HardwareMap hardwareMap, Telemetry telemetry, DrivetrainData drivetrainData)
    {
        super(telemetry);
        maxNormalPower = drivetrainData.maxNormalPower;
        maxCreepPower = drivetrainData.maxCreepPower;
        maxPower = maxNormalPower;

        leftFrontDrive = hardwareMap.get(DcMotorEx.class, "leftFrontDrive");
        leftBackDrive = hardwareMap.get(DcMotorEx.class, "leftBackDrive");
        rightFrontDrive = hardwareMap.get(DcMotorEx.class, "rightFrontDrive");
        rightBackDrive = hardwareMap.get(DcMotorEx.class, "rightBackDrive");

        leftFrontDrive.setDirection(drivetrainData.leftFrontDirection);
        leftBackDrive.setDirection(drivetrainData.leftBackDirection);
        rightFrontDrive.setDirection(drivetrainData.rightFrontDirection);
        rightBackDrive.setDirection(drivetrainData.rightBackDirection);
        setBrakingOn();
        setToNormalPower();
    }
    protected void setToNormalPower()
    {
        maxPower = maxNormalPower;
    }

    protected void setToCreepPower(){
        maxPower = maxCreepPower;
    }

    protected void setBrakingOn() {
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    protected void setBrakingOff() {
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }
}