package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.ArmLeftServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.ArmRightServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.DrivetrainData21528_A;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.WristServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda223DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.GrabberServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.WristSwivelServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.LiftData21528;

public class Bot21528_A extends Component {
    private final LiftSingle lift;
    private final ServoSimple grabber, armLeft, wrist, armRight, wristSwivel;
    private Drivetrain drivetrain;
    private LinearOpMode opMode;

    public Bot21528_A(LinearOpMode opMode, Telemetry telemetry) {
        super(telemetry);
        drivetrain = new Drivetrain(opMode, telemetry, new DrivetrainData21528_A(), new GoBilda312DcMotorData());
        lift = new LiftSingle(opMode.hardwareMap, telemetry, "lift", false, new GoBilda223DcMotorData(), new LiftData21528());
        grabber = new ServoSimple(opMode.hardwareMap, telemetry, "grabber", new GrabberServoData21528());
        armLeft = new ServoSimple(opMode.hardwareMap, telemetry, "armLeft", new ArmLeftServoData21528());
        armRight = new ServoSimple(opMode.hardwareMap, telemetry, "armRight", new ArmRightServoData21528());
        wrist = new ServoSimple(opMode.hardwareMap, telemetry, "wrist", new WristServoData21528());
        wristSwivel = new ServoSimple(opMode.hardwareMap, telemetry, "wristSwivel", new WristSwivelServoData21528());

        grabberClose();
        wristClose();
        armOpen();
    }

    public void grabberClose() {
        grabber.close();
    }

    public void grabberOpen() {
        grabber.open();
    }

    private void armLeftClose() {
        armLeft.close();
    }

    private void armLeftOpen() {
        armLeft.open();
    }

    private void armLeftMiddle() {
        armLeft.middle();
    }

    private void armLeftSpecimenHang() {
        armLeft.specimenHang();
    }

    private void armLeftSpecimenGrab() {
        armLeft.specimenGrab();
    }

    private void armRightClose() {
        armRight.close();
    }

    private void armRightOpen() {
        armRight.open();
    }

    private void armRightMiddle() {
        armRight.middle();
    }

    private void armRightSpecimenHang() {
        armRight.specimenHang();
    }

    private void armRightSpecimenGrab() {
        armRight.specimenGrab();
    }

    public void armClose() {
        armLeftClose();
        armRightClose();
    }

    public void armOpen() {
        armLeftOpen();
        armRightOpen();
    }


    public void armMiddle() {
        armLeftMiddle();
        armRightMiddle();
    }

    public void armSpecimenGrab() {
        armLeftSpecimenGrab();
        armRightSpecimenGrab();
    }

    public void armSpecimenHang() {
        armLeftSpecimenHang();
        armRightSpecimenHang();
    }

    public void wristOpen() {
        wrist.open();
    }

    public void wristMiddle() { wrist.middle(); }

    public void wristClose() {
        wrist.close();
    }

    public void wristSpecimenHang() {
        wrist.specimenHang();
    }

    public void wristSpecimenGrab() { wrist.specimenGrab(); }

    public void wristSwivel0() {
        wristSwivel.open();
    }

    public void wristSwivel90() { wristSwivel.middle(); }

    public void wristSwivel180() {
        wristSwivel.close();
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

    public void liftLowPosition() {
        lift.lowPosition();
    }

    public void liftMinPosition() {
        lift.minPosition();
    }

    public void liftStop() {
        lift.stop();
    }

    public void liftMoveDownToSwitch() {
        lift.moveDownToSwitch();
    }

    public void liftResetEncoder()
    {
        lift.resetEncoder();
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

    public void touchNoseSwitch() {
        drivetrain.touchNoseSwitch();
    }
}
