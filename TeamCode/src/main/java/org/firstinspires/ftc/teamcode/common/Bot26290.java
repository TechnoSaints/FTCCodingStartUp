package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda223DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda60DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.team26290.ArmData26290;
import org.firstinspires.ftc.teamcode.common.hardware_data.team26290.DrivetrainData26290;
import org.firstinspires.ftc.teamcode.common.hardware_data.team26290.GrabberServoData26290;
import org.firstinspires.ftc.teamcode.common.hardware_data.team26290.HorizontalWristServoData26290;
import org.firstinspires.ftc.teamcode.common.hardware_data.team26290.LiftData26290;
import org.firstinspires.ftc.teamcode.common.hardware_data.team26290.VerticalWristServoData26290;

public class Bot26290 extends Component {
    private Drivetrain drivetrain;

    private LiftSingle arm;
    private LiftSingleNoSensor lift;
    private ServoSimple grabber, horizontalWrist, verticalWrist;

    public Bot26290(LinearOpMode opMode, HardwareMap hardwareMap, Telemetry telemetry) {
        super(telemetry);
        drivetrain = new Drivetrain(opMode, telemetry, new DrivetrainData26290(), new GoBilda312DcMotorData());
        grabber = new ServoSimple(hardwareMap, telemetry, "grabber", new GrabberServoData26290());
        horizontalWrist = new ServoSimple(hardwareMap, telemetry, "hWrist", new HorizontalWristServoData26290());
        verticalWrist = new ServoSimple(hardwareMap, telemetry, "vWrist", new VerticalWristServoData26290());
        lift = new LiftSingleNoSensor(hardwareMap, telemetry, "lift", false, new GoBilda223DcMotorData(), new LiftData26290());
        arm = new LiftSingle(hardwareMap, telemetry, "arm", false, new GoBilda60DcMotorData(), new ArmData26290());
        grabberClose();
        back();
        left();
    }

    public void grabberClose() {
        grabber.close();
    }

    public void grabberOpen() {
        grabber.open();
    }

    public void back(){
        verticalWrist.open();
    }

    public void down(){
        verticalWrist.middle();
    }

    public void up(){
        verticalWrist.close();
    }

    public void left(){
        horizontalWrist.close();
    }

    public void right(){
        horizontalWrist.open();
    }

    public void liftUp(double speed) {
        lift.up(speed, arm.lowered());
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
        lift.stop(arm.lowered());
    }

    public void liftZero() {
        lift.zero();
    }

    public void armUp(double speed){
        arm.up(speed);
    }

    public void armDown(double speed){
        arm.down(speed);
    }

    public void armStop(){
        arm.stop();
    }

    public void armZero(){arm.zero();}

    public void armHighPosition() {
        arm.highPosition();
    }

    public void armMediumHighPosition(){arm.mediumHighPosition();}

    public void armMediumPosition() {
        arm.mediumPosition();
    }

    public void armMediumLowPosition(){arm.mediumLowPosition();}

    public void armlowPosition() {
        arm.lowPosition();
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