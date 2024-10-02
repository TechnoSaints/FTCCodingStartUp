package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

<<<<<<<< HEAD:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/common/TeleopBot9800.java
public class TeleopBot9800 extends Bot9800 {
    private TeleopDrivetrain drivetrain = null;
    public TeleopBot9800(HardwareMap hardwareMap, Telemetry telemetry)
    {
========
public class TeleopBot21528 extends Bot21528 {
    private TeleopDrivetrain drivetrain = null;

    public TeleopBot21528(HardwareMap hardwareMap, Telemetry telemetry) {
>>>>>>>> Henderson:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/common/TeleopBot21528.java
        super(hardwareMap, telemetry);
        drivetrain = new TeleopDrivetrain(hardwareMap, telemetry);
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