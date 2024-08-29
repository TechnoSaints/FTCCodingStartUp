package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.common.AutoBot;

public abstract class AutoMasterTemplate extends LinearOpMode
{
    private ElapsedTime timer = new ElapsedTime();

    protected AutoBot bot;

    private Pose2d startPose, pose1, pose2, pose3;

    protected Action traj1, traj2, task1, task2;

    protected MultipleTelemetry multipleTelemetry;

    protected AutoMasterTemplate() {
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

            telemetry.addLine("Building trajectories.");
            telemetry.update();

            traj1 = bot.drivetrain().actionBuilder(startPose)
                    .splineToLinearHeading(pose1, pose1.heading)
                    .afterTime(0.5, task1)
                    .waitSeconds(0.50)
                    .splineToLinearHeading(pose2, pose2.heading)
                    .waitSeconds(3)
                    .splineToLinearHeading(pose3, pose3.heading)
                    .afterTime(0.5, task2)
                    .splineToLinearHeading(startPose, startPose.heading)
                    .build();

            telemetry.addLine("Center near traj built.");
            telemetry.update();

        sleep(500);
        telemetry.addLine("Entering prop detection loop.");
        telemetry.update();

        while (!isStarted() && !isStopRequested()) {

            telemetry.update();

            sleep(50);
        }

        while (!isStopRequested() && opModeIsActive()) {
            Actions.runBlocking(new SequentialAction(
                                    traj1));
        }
    }
}