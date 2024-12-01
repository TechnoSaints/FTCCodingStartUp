package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21527.DrivetrainData21527;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda312DcMotorData;


public class Bot21527 extends Component {
    private LinearOpMode opMode;
    private Drivetrain drivetrain;

    public Bot21527(HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
        drivetrain = new Drivetrain(opMode, telemetry, new DrivetrainData21527(), new GoBilda312DcMotorData());
    }
    
    public void creepDirection(double axial, double strafe, double yaw) {
        drivetrain.creepDirection(axial, strafe, yaw);
    }

    public void moveDirection(double axial, double strafe, double yaw) {
        drivetrain.moveDirection(axial, strafe, yaw);
    }

    public void stopDriveVelocity() {
        drivetrain.stopVelocity();
    }
}
