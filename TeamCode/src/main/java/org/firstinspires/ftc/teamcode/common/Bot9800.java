package org.firstinspires.ftc.teamcode.common;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.DrivetrainData9800;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.ArmServoData9800;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.GrabberServoData9800;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.LiftDataIntake9800;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.LiftDataOuttake9800;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.OuttakeGrabberServoData9800;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.OuttakeArmServoData9800;
import org.firstinspires.ftc.teamcode.common.hardware_data.team9800.WristServoData9800;


public class Bot9800 extends Component {
    private LinearOpMode opMode;
    private Drivetrain drivetrain;

    private final LiftSingle intakeLift, outtakeLift;
    private final ServoSimple grabber, arm, outtakeGrabber, outtakeArm, wrist;

    public Bot9800(LinearOpMode opMode, Telemetry telemetry) {
        super(telemetry);
        drivetrain = new Drivetrain(opMode, telemetry, new DrivetrainData9800(), new GoBilda312DcMotorData());
        intakeLift = new LiftSingle(opMode.hardwareMap, telemetry, "intakeLift", true, new GoBilda312DcMotorData(), new LiftDataIntake9800());
        outtakeLift = new LiftSingle(opMode.hardwareMap, telemetry, "outtakeLift", false, new GoBilda312DcMotorData(), new LiftDataOuttake9800());
        grabber = new ServoSimple(opMode.hardwareMap, telemetry, "grabber", new GrabberServoData9800());
        arm = new ServoSimple(opMode.hardwareMap, telemetry, "arm", new ArmServoData9800());
        wrist = new ServoSimple(opMode.hardwareMap, telemetry, "wrist", new WristServoData9800());
        outtakeGrabber = new ServoSimple(opMode.hardwareMap, telemetry, "outtakeGrabber", new OuttakeGrabberServoData9800());
        outtakeArm = new ServoSimple(opMode.hardwareMap, telemetry, "outtakeArm", new OuttakeArmServoData9800());
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

    public void wristClose() {
        wrist.close();
    }

    public void wristOpen() {
        wrist.open();
    }

    public void intakeLiftUp(double speed) {
        intakeLift.up(speed);
    }

    public void intakeLiftDown(double speed) {
        intakeLift.down(speed);
    }

    public void intakeLiftStop() {
        intakeLift.stop();
    }

    public void intakeLiftZero() {
        intakeLift.zero();
    }
    
    public void chamberPosition(){
        outtakeLift.chamberPosition();
    }

    public void releasePosition(){
        outtakeLift.releasePosition();
    }

    public void highBucketPosition(){
        outtakeLift.highBucketPosition();
    }

    public void grabPosition(){
        intakeLift.grabPosition();
    }

    public void outtakeLiftUp(double speed) {
        outtakeLift.up(speed);
    }

    public void outtakeLiftDown(double speed) {
        outtakeLift.down(speed);
    }

    public void outtakeLiftZero() {
        outtakeLift.zero();
    }

    public void outtakeLiftStop() {
        outtakeLift.stop();
    }

    public void outtakeGrabberClose() {
        outtakeGrabber.close();
    }

    public void outtakeGrabberOpen() {
        outtakeGrabber.open();
    }

    public void outtakeArmClose() {
        outtakeArm.close();
    }

    public void outtakeArmOpen() {
        outtakeArm.open();
    }

    public void creepDirection(double axial, double strafe, double yaw) {
        drivetrain.creepDirection(axial, strafe, yaw);
    }

    public void moveDirection(double axial, double strafe, double yaw) {
        drivetrain.moveDirection(axial, strafe, yaw);
    }


    public void moveForwardForDistance(double distance) {
        drivetrain.moveForwardForDistance(distance);
    }

    public void strafeRightForDistance(double distance) {
        drivetrain.strafeRightForDistance(distance);
    }

    public void turnToHeading(double heading) {
        drivetrain.turnToHeading(heading);
    }

    public void stopDrive() {
        drivetrain.moveDirection(0, 0, 0);
    }
}

