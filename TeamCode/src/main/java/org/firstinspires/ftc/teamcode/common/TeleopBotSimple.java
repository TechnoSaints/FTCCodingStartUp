package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.DrivetrainData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.DrivetrainData21528SparkFun;

public class TeleopBotSimple extends BotSimple {
    private TeleopDrivetrain drivetrain = null;

    public TeleopBotSimple(HardwareMap hardwareMap, Telemetry telemetry) {
        super(hardwareMap, telemetry);
        drivetrain = new TeleopDrivetrain(hardwareMap, telemetry, new DrivetrainData21528SparkFun());
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