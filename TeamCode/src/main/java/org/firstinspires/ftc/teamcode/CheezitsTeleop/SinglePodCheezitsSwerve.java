package org.firstinspires.ftc.teamcode.CheezitsTeleop;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp(name="cheezitsSwerve", group="CheezitsTeleOp")
public class SinglePodCheezitsSwerve extends LinearOpMode {
    private double turn, xpos, ypos, ServoPosition;
    private SinglePodDrive driveTrain;

    @Override
    public void runOpMode() throws InterruptedException {
        driveTrain = new SinglePodDrive(this.hardwareMap);

        waitForStart(); // Ensure the opmode starts only after pressing start

        while (opModeIsActive()) {
            // Read gamepad input
            while (opModeIsActive()) {
                turn = -gamepad1.left_stick_x;
                xpos = -gamepad1.right_stick_x;
                ypos = -gamepad1.right_stick_y;

                ServoPosition = driveTrain.getAngle(ypos, xpos);

                if (Math.abs(ypos) > 0.1 || Math.abs(turn) > 0.1) {
                    if (ypos > 0) {
                        driveTrain.moveForwardAndTurn(ypos, turn);
                    } else {
                        driveTrain.moveBackwardAndTurn(ypos, turn);
                    }
                } else {
                    driveTrain.stopServos(); // This stops motion immediately
                }
            }

            // Run the command scheduler
            CommandScheduler.getInstance().run();
        }
    }
}