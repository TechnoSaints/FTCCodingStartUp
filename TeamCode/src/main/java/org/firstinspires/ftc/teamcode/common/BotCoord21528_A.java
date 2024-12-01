package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.ArmLeftServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.ArmRightServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.DrivetrainData21528_A;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.WristServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.GoBilda223DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.GrabberServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.WristSwivelServoData21528;
import org.firstinspires.ftc.teamcode.common.hardware_data.team21528.LiftData21528;

public class BotCoord21528_A extends Bot21528_A {
    private DrivetrainCoord drivetrainCoord;

    public BotCoord21528_A(LinearOpMode opMode, Telemetry telemetry) {
        super(opMode, telemetry);
        drivetrainCoord = new DrivetrainCoord(opMode, telemetry, new DrivetrainData21528_A(), new GoBilda312DcMotorData());
    }

    public void setPose(Pose2D currentPose) {
        drivetrainCoord.setPose(currentPose);
    }

    public void moveToPose(Pose2D targetPose) {
        drivetrainCoord.moveToPose(targetPose);
    }

}
