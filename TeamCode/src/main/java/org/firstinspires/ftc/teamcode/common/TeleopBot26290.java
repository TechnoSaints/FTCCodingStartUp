package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.DrivetrainData21528_B;

public class TeleopBot26290 extends Bot26290 {
    private Drivetrain drivetrain = null;

    public TeleopBot26290(LinearOpMode opMode, Telemetry telemetry) {
        super(opMode.hardwareMap, telemetry);
        drivetrain = new Drivetrain(opMode, telemetry, new DrivetrainData21528_B(), new GoBilda312DcMotorData());
    }

    public void creepDirection(double axial, double strafe, double yaw) {
        drivetrain.creepDirection(axial, strafe, yaw);
    }

    public void moveDirection(double axial, double strafe, double yaw) {
        drivetrain.moveDirection(axial, strafe, yaw);
    }

    public void stopDrive() {
        drivetrain.stop();
    }
}