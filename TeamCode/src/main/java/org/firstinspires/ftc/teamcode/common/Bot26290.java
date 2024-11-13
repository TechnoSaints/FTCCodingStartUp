package org.firstinspires.ftc.teamcode.common;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda117DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda223DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda60DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.team26290.ArmData26290;
import org.firstinspires.ftc.teamcode.common.hardware_data.team26290.DrivetrainData26290;
import org.firstinspires.ftc.teamcode.common.hardware_data.team26290.GrabberServoData26290;
import org.firstinspires.ftc.teamcode.common.hardware_data.team26290.LiftData26290;

public class Bot26290 extends Component {
    private Drivetrain drivetrain;

    private LiftSingle lift, arm;
    private ServoSimple grabber;

    public Bot26290(LinearOpMode opMode, HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
        drivetrain = new Drivetrain(opMode, telemetry, new DrivetrainData26290(), new GoBilda312DcMotorData());
        grabber = new ServoSimple(hardwareMap, telemetry, "grabber", new GrabberServoData26290());
        lift = new LiftSingle(hardwareMap, telemetry, "lift", true, new GoBilda223DcMotorData(), new LiftData26290());
        arm = new LiftSingle(hardwareMap, telemetry, "arm", false, new GoBilda60DcMotorData(), new ArmData26290());
        grabberClose();
    }

    public void grabberClose() {
        grabber.close();
    }

    public void grabberOpen() {
        grabber.open();
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

    public void armUp(int speed){
        arm.up(speed);
    }

    public void armDown(int speed){
        arm.down(speed);
    }

    public void armStop(){
        arm.stop();
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

}