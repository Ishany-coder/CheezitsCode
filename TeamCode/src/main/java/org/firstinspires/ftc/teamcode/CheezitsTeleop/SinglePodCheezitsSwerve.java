package org.firstinspires.ftc.teamcode.CheezitsTeleop;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

public class SinglePodCheezitsSwerve extends LinearOpMode {
    private double turn, xpos, ypos, ServoPosition;
    private SinglePodDrive driveTrain;

    @Override
    public void runOpMode() throws InterruptedException {
        driveTrain = new SinglePodDrive(this.hardwareMap);

        waitForStart(); // Ensure the opmode starts only after pressing start

        while (opModeIsActive()) {
            // Read gamepad input
            turn = -gamepad1.left_stick_x;
            xpos = -gamepad1.right_stick_x;
            ypos = -gamepad1.right_stick_y;

            // Calculate and constrain servo position
            ServoPosition = driveTrain.getAngle(ypos, xpos);

            // Handle movement and turning together
            if (Math.abs(ypos) > 0.1) {
                CommandScheduler.getInstance().schedule(
                        new InstantCommand(() -> {
                            if (ypos > 0) {
                                driveTrain.moveForwardAndTurn(ServoPosition);
                            } else {
                                driveTrain.moveBackwardAndTurn(ServoPosition);
                            }
                        })
                );
            } else if (Math.abs(ypos) <= 0.1) {
                driveTrain.stopServos();
            }

            // Handle turning logic separately
            if (Math.abs(turn) > 0.1) {
                driveTrain.turn(turn);
            }

            // Run the command scheduler
            CommandScheduler.getInstance().run();
        }
    }
}