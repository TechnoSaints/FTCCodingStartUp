package opmode.auto;

import androidx.annotation.NonNull;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@Config
@Autonomous(name = "TESTAUTO", group = "Autonomous")

public class testAuto extends LinearOpMode {

    MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));


    Action trajectoryAction1;
    Action trajectoryAction2;
    Action trajectoryAction3;

    Action trajectoryCLOSE;

    // actionBuilder builds from the drive steps passed to it,
    // and .build(); is needed to build the trajectory
    trajectoryAction1 = drive.actionBuilder(drive.pose)
            .lineToYSplineHeading(33, Math.toRadians(0))
            .waitSeconds(2)
        .setTangent(Math.toRadians(90))
            .lineToY(48)
        .setTangent(Math.toRadians(0))
            .lineToX(32)
        .strafeTo(new Vector2d(44.5, 30))
            .turn(Math.toRadians(180))
            .lineToX(47.5)
        .waitSeconds(3)
        .build();



    @Override
        public void runOpMode() {
            // instantiate your MecanumDrive at a particular pose.
            MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, Math.toRadians(90)));
            // make a Claw instance
            ServoClaw claw = new ServoClaw(hardwareMap);

        }






    public class ServoClaw {
        private Servo claw;

        public ServoClaw(HardwareMap hardwareMap) {
            claw = hardwareMap.get(Servo.class, "claw");
        }
    }




}