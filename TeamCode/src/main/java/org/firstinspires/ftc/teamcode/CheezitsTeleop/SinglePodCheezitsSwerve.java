package org.firstinspires.ftc.teamcode.CheezitsTeleop;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class SinglePodCheezitsSwerve extends LinearOpMode {
    private double turn;
    private double xpos;
    private double ypos;
    private double ServoPosition;
    SinglePodDrive driveTrain;

    @Override
    public void runOpMode() throws InterruptedException {
        driveTrain = new SinglePodDrive(this.hardwareMap);

        while (opModeIsActive()) {
            // Read gamepad input
            turn = -gamepad1.left_stick_x;
            xpos = -gamepad1.right_stick_x;
            ypos = -gamepad1.right_stick_y;

            // Calculate servo position for turning
            ServoPosition = driveTrain.getAngle(ypos, xpos);

            // Schedule turning the wheels first, then moving forward
            //if ypos > 0 move forward
            if(ypos > 0) {
                CommandScheduler.getInstance().schedule(
                        new SequentialCommandGroup(
                                new InstantCommand(() -> driveTrain.turnDriveMotors(ServoPosition)), // Turn wheels
                                new InstantCommand(() -> driveTrain.moveForward()) // Move forward
                        )
                );
                // at any point if the ypos is less then 0 then move back
            } else if (ypos < 0) {
                CommandScheduler.getInstance().schedule(
                        new SequentialCommandGroup(
                                new InstantCommand(() -> driveTrain.turnDriveMotors(ServoPosition)),
                                new InstantCommand(() -> driveTrain.moveBackward())
                        )
                );

            }
            // as long as turn doesnt equal null we turn
            if (turn != 0) {
                driveTrain.turn(turn);
            }
        }
    }
}
