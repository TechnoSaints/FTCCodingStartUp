package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.AutoBot3DeadWheel;

@Config
@Autonomous(name = "AutoTemplate3DeadWheel", group = "Linear OpMode")
public class AutoTemplate3DeadWheel extends LinearOpMode {
    private ElapsedTime timer = new ElapsedTime();

    protected AutoBot3DeadWheel bot;

    private Pose2d startPose, pose1, pose2, pose3;

    private int detection = 1;

    protected Action trajectoryAction1, trajectoryAction2, selectedTrajectoryAction;

    protected MultipleTelemetry multipleTelemetry;

    public AutoTemplate3DeadWheel() {
    }

    @Override
    public void runOpMode() {

        multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.addLine("Initializing.");
        telemetry.update();

        startPose = new Pose2d(0, 0, Math.toRadians(0));
        pose1 = new Pose2d(36, 12, Math.toRadians(0));
        pose2 = new Pose2d(24, 24, Math.toRadians(0));
        pose3 = new Pose2d(12, -12, Math.toRadians(0));

        bot = new AutoBot3DeadWheel(hardwareMap, multipleTelemetry, startPose);
        telemetry.addLine("Bot created.");
        telemetry.update();

        telemetry.addLine("Building trajectory actions.");
        telemetry.update();

        // A sample trajectory action that moves around and call some bot actions
        trajectoryAction1 = bot.drivetrain().actionBuilder(startPose)
//                .afterTime(0), bot.liftToCruisingPosition())
//                .lineToX(36)
//                .lineToX(0)

                .splineToLinearHeading(pose1, pose1.heading)
//                .afterTime(2.5, bot.closeGrabber())
                .splineToLinearHeading(pose2, pose2.heading)
//                .afterDisp(12, bot.openGrabber())
//                .waitSeconds(1)
                .splineToLinearHeading(pose3, pose3.heading)
//                .afterTime(0.5, bot.closeGrabber())

                .splineToLinearHeading(startPose, startPose.heading)
//                .afterTime(5.0, bot.liftToBottomPosition())
                .build();

        telemetry.addLine("trajectoryAction1 built");
        telemetry.update();

        // Another sample trajectory action that moves around and call some bot actions
        // Initially is the same as trajectoryAction1, but may be changed
        trajectoryAction2 = bot.drivetrain().actionBuilder(startPose)
                .afterTime(0, bot.liftToBottomPosition())
                .lineToX(pose2.position.x)
                .afterTime(2.5, bot.closeGrabber())
                .splineToLinearHeading(pose2, pose2.heading)
                .afterDisp(12, bot.openGrabber())
                .waitSeconds(1)
                .splineToSplineHeading(pose3, pose3.heading)
                .afterTime(0.5, bot.closeGrabber())
                .splineToLinearHeading(startPose, startPose.heading)
                .afterTime(5.0, bot.liftToBottomPosition())
                .build();

        telemetry.addLine("trajectoryAction2 built");
        telemetry.update();
        telemetry.addLine("Entering detection loop.");
        telemetry.update();

        while (!isStarted() && !isStopRequested()) {
            telemetry.addLine("Detecting stuff");
            telemetry.update();
            detection = 1;
            sleep(50);
        }
        if (isStopRequested()) return;

        if (detection == 1) {
            selectedTrajectoryAction = trajectoryAction1;
        } else {
            selectedTrajectoryAction = trajectoryAction2;
        }
        while (!isStopRequested() && opModeIsActive()) {
            Actions.runBlocking(new SequentialAction(
                    selectedTrajectoryAction,
                    bot.shutdown()
            ));
        }
    }
}