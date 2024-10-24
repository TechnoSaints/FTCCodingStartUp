package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda312DcMotorData;

public class TeleopDrivetrain extends Component {
    public final DcMotorEx leftFrontDrive;
    public final DcMotorEx rightFrontDrive;
    public final DcMotorEx leftBackDrive;
    public final DcMotorEx rightBackDrive;
    protected final double maxNormalPower;
    protected final double maxCreepPower;
    protected double currentPower;

    public TeleopDrivetrain(HardwareMap hardwareMap, Telemetry telemetry, DrivetrainData drivetrainData) {
        super(telemetry);
        maxNormalPower = drivetrainData.maxNormalPower;
        maxCreepPower = drivetrainData.maxCreepPower;

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

    protected void setToNormalPower() {
        currentPower = maxNormalPower;
    }

    protected void setToCreepPower() {
        currentPower = maxCreepPower;
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

    public void creepDirection(double axial, double strafe, double yaw) {
        setToCreepPower();
        moveDirection(axial, strafe, yaw);
        setToNormalPower();
    }

    public void moveDirection(double axial, double strafe, double yaw) {
        // Calculate wheel powers.
        double leftFrontPower = axial - strafe - yaw;
        double rightFrontPower = axial + strafe + yaw;
        double leftBackPower = axial + strafe - yaw;
        double rightBackPower = axial - strafe + yaw;

        // Normalize wheel powers to be less than 1.0
        double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }

        leftFrontDrive.setPower(leftFrontPower * currentPower);
        rightFrontDrive.setPower(rightFrontPower * currentPower);
        leftBackDrive.setPower(leftBackPower * currentPower);
        rightBackDrive.setPower(rightBackPower * currentPower);
    }

    public void stop() {
        leftFrontDrive.setPower(0.0);
        leftBackDrive.setPower(0.0);
        rightFrontDrive.setPower(0.0);
        rightBackDrive.setPower(0.0);
    }
}