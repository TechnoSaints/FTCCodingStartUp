package org.firstinspires.ftc.teamcode.common;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda117DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda223DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21527.GrabberServoData21527;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21527.LiftData21527;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.GrabberServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.LiftData21528;

public abstract class Bot21527 extends Component {

    private final LiftSingle lift;
    private final ServoSimple grabber;

    public Bot21527(HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
        lift = new LiftSingle(hardwareMap, telemetry, "arm", false, new GoBilda117DcMotorData(), new LiftData21527());
        grabber = new ServoSimple(hardwareMap, telemetry, "grabber", new GrabberServoData21527());
    }

    public void liftUp(double speed){lift.up(speed);}
    public void liftDown(double speed){lift.down(speed);}
    public void liftStop(){lift.stop();}
    public void liftZero(){lift.zero();}

    public void grabberOpen(){grabber.open();}
    public void grabberClose(){grabber.close();}
}
