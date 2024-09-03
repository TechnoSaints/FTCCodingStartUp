package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.AutoBot;

public abstract class AutoTemplate extends LinearOpMode {
    private ElapsedTime timer = new ElapsedTime();

    protected AutoBot bot;

    private Pose2d startPose, pose1, pose2, pose3;

    private int detection = 1;

    protected Action trajectoryAction1, trajectoryAction2, selectedTrajectoryAction;

    protected MultipleTelemetry multipleTelemetry;

    protected AutoTemplate() {
        startPose = new Pose2d(0, 0, Math.toRadians(0));
        pose1 = new Pose2d(24, 0, Math.toRadians(-90));
        pose2 = new Pose2d(24, 24, Math.toRadians(0));
        pose3 = new Pose2d(0, 24, Math.toRadians(90));
    }

    @Override
    public void runOpMode() {

        multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.addLine("Initializing.");
        telemetry.update();

        bot = new AutoBot(hardwareMap, multipleTelemetry, startPose);
        telemetry.addLine("Bot created.");
        telemetry.update();

        telemetry.addLine("Building trajectory actions.");
        telemetry.update();

        trajectoryAction1 = bot.drivetrain().actionBuilder(startPose)
                .afterTime(0, bot.liftToCruisingPosition())
                .lineToX(24)
                .afterTime(2.5, bot.closeGrabber())
                .splineToLinearHeading(pose2, pose2.heading)
                .afterDisp(12, bot.openGrabber())
                .waitSeconds(1)
                .splineToSplineHeading(pose3, pose3.heading)
                .afterTime(0.5, bot.closeGrabber())
                .splineToLinearHeading(startPose, startPose.heading)
                .afterTime(5.0, bot.liftToBottomPosition())
                .build();

        telemetry.addLine("trajectory1 built");
        telemetry.update();

        trajectoryAction2 = bot.drivetrain().actionBuilder(startPose)
                .afterTime(0, bot.liftToCruisingPosition())
                .lineToX(24)
                .afterTime(2.5, bot.closeGrabber())
                .splineToLinearHeading(pose2, pose2.heading)
                .afterDisp(12, bot.openGrabber())
                .waitSeconds(1)
                .splineToSplineHeading(pose3, pose3.heading)
                .afterTime(0.5, bot.closeGrabber())
                .splineToLinearHeading(startPose, startPose.heading)
                .afterTime(5.0, bot.liftToBottomPosition())
                .build();

        telemetry.addLine("trajectory2 built");
        telemetry.update();


        sleep(500);
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