package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.ArmServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.DrivetrainData21528_B;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.WristServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda223DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.GrabberServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.LiftData21528;

public class Bot21528_B extends Component {
    private Drivetrain drivetrain;
    private final LiftSingle lift;
    private final ServoSimple grabber, arm, wrist;
    private LinearOpMode opMode;

    public Bot21528_B(LinearOpMode opMode, Telemetry telemetry) {
        super(telemetry);
        drivetrain = new Drivetrain(opMode, telemetry, new DrivetrainData21528_B(), new GoBilda312DcMotorData());
        lift = new LiftSingle(opMode.hardwareMap, telemetry, "lift", false, new GoBilda223DcMotorData(), new LiftData21528());
        grabber = new ServoSimple(opMode.hardwareMap, telemetry, "grabber", new GrabberServoData21528());
        arm = new ServoSimple(opMode.hardwareMap, telemetry, "arm1", new ArmServoData21528());
        wrist = new ServoSimple(opMode.hardwareMap, telemetry, "wrist", new WristServoData21528());
        grabberClose();
        armClose();
        wristClose();
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

    public void armMiddle() {
        arm.middle();
    }

    public void wristOpen() {
        wrist.open();
    }

    public void wristClose() {
        wrist.close();
    }

    public void liftUp(double speed) {
        lift.up(speed);
    }

    public void liftDown(double speed) {
        lift.down(speed);
    }

    public void liftHighPosition() {
        lift.highPosition();
    }

    public void liftMediumPosition() {
        lift.mediumPosition();
    }

    public void liftlowPosition() {
        lift.lowPosition();
    }

    public void liftMinPosition() {
        lift.minPosition();
    }

    public void liftStop() {
        lift.stop();
    }

    public void liftZero() {
        lift.zero();
    }

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

   // public void touchNoseSwitch() {
   //     drivetrain.touchNoseSwitch();
   // }
}
