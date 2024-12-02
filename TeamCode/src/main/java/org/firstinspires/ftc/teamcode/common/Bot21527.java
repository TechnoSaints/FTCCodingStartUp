package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda117DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21527.ArmData21527;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21527.ClawServoData21527;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21527.DrivetrainData21527;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda312DcMotorData;


public class Bot21527 extends Component {
    private LinearOpMode opMode;
    private Drivetrain drivetrain;
    private LiftSingle arm;
    private ServoSimple claw;

    public Bot21527(HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
        drivetrain = new Drivetrain(opMode, telemetry, new DrivetrainData21527(), new GoBilda312DcMotorData());
        arm = new LiftSingle(hardwareMap, telemetry, "arm", false, new GoBilda117DcMotorData(), new ArmData21527());
        claw = new ServoSimple(hardwareMap, telemetry, "claw", new ClawServoData21527());
    }

    public void armUp(double speed){arm.up(speed);}

    public void armDown(double speed){arm.down(speed);}

    public void armStop(){arm.stop();}

    public void openClaw(){claw.open();}

    public void closeClaw(){claw.close();}

    public void turnToHeading(double heading) {
        drivetrain.turnToHeading(heading);
    }

    // Turn a specified distance in degrees
    public void turnForDistance(double distance) {
        drivetrain.turnForDistance(distance);
    }

    public void moveDirection(double axial, double strafe, double yaw) {
        drivetrain.moveDirection(axial, strafe, yaw);
    }

    public void moveDirectionNoEnc(double axial, double strafe, double yaw) {
        drivetrain.moveDirection(axial, strafe, yaw);
    }

    public void creepDirection(double axial, double strafe, double yaw) {
        drivetrain.creepDirection(axial, strafe, yaw);
    }

    public void creepStraightForDistance(double distance) {
        drivetrain.creepForwardForDistance(distance);
    }

    // Move straight for a specified distance in inches
    public void moveForwardForDistance(double distance) {
        drivetrain.moveForwardForDistance(distance);
    }

    public void strafeRightForDistance(double distance) {
        drivetrain.strafeRightForDistance(distance);
    }

    public void stopDrive() {
        drivetrain.moveDirection(0, 0, 0);
    }

}