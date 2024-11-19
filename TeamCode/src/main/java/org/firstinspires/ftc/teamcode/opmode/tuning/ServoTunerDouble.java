package org.firstinspires.ftc.teamcode.opmode.tuning;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Servo Tuner Double", group = "Tuning")
@Disabled
public class ServoTunerDouble extends LinearOpMode {

    static final double INCREMENT = 0.01;     // amount to slew servo each CYCLE_MS cycle
    static final int CYCLE_MS = 50;     // period of each cycle
    static final double MAX_POS = 1.0;     // Maximum rotational position
    static final double MIN_POS = 0.0;     // Minimum rotational position

    // Define class members
    Servo servo1, servo2;
    double position1, position2 = (MAX_POS - MIN_POS) / 2; // Start at halfway position

    @Override
    public void runOpMode() {

        // Connect to servo (Assume Robot Left Hand)
        // Change the text in quotes to match any servo name on your robot.
        servo1 = hardwareMap.get(Servo.class, "arm1");
        servo2 = hardwareMap.get(Servo.class, "arm2");

        // Wait for the start button
        telemetry.addData(">", "Press Start to tune servos.");
        telemetry.update();
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                position1 += INCREMENT;
                position2 -= INCREMENT;

            } else if (gamepad1.left_bumper) {
                position1 -= INCREMENT;
                position2 += INCREMENT;
            }

            if (position1 >= MAX_POS)
                position1 = MAX_POS;
            if (position2 >= MAX_POS)
                position2 = MAX_POS;
            if (position1 <= MIN_POS)
                position1 = MIN_POS;
            if (position2 <= MIN_POS)
                position2 = MIN_POS;


            // Display the current value
            telemetry.addData("position1:", "%5.2f", position1);
            telemetry.addData("position2", "%5.2f", position2);
            telemetry.addLine("Right Bumper to Go One Direction");
            telemetry.addLine("Left Bumper to Go the Other Direction");
            telemetry.update();
            // Set the servo to the new position and pause;
            servo1.setPosition(position1);
            servo2.setPosition(position2);

            sleep(CYCLE_MS);
        }
    }
}

