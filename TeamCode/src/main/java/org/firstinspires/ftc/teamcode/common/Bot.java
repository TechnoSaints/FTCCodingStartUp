package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.ArmServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.GrabberServoData;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda223DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.GrabberServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.LiftData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.Viper223Long2StageLiftData;

public abstract class Bot extends Component {
    private final LiftSingle lift;
    private final ServoSimple grabber, arm;

    public Bot(HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
        lift = new LiftSingle(hardwareMap, telemetry, "lift", false, new GoBilda223DcMotorData(), new LiftData21528());
        grabber = new ServoSimple(hardwareMap, telemetry, "grabber", new GrabberServoData21528());
        arm = new ServoSimple(hardwareMap, telemetry, "grabber", new ArmServoData21528());
        grabberClose();
    }

    public void grabberClose() {
        grabber.close();
    }

    public void grabberOpen() {
        grabber.open();
    }

    public void armClose() {
        arm.close();
    }

    public void armOpen() {
        arm.open();
    }

    public void liftUp(double speed) {
        lift.up(speed);
    }

    public void liftDown(double speed) {
        lift.down(speed);
    }

    public void liftStop() {
        lift.stop();
    }

    public void liftZero() {
        lift.zero();
    }
}