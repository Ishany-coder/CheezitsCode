package org.firstinspires.ftc.teamcode.CheezitsTeleop;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class CheezitsSwerve extends LinearOpMode {
    private double turn;
    private double xpos;
    private double ypos;
    private double ServoPosition;
    Drive myHardware;

    @Override
    public void runOpMode() throws InterruptedException {
        myHardware = new Drive(this.hardwareMap);

        while (opModeIsActive()) {
            // Read gamepad input
            turn = -gamepad1.left_stick_x;
            xpos = -gamepad1.right_stick_x;
            ypos = -gamepad1.right_stick_y;

            // Calculate servo position for turning
            ServoPosition = myHardware.getAngle(ypos, xpos);

            // Schedule turning the wheels first, then moving forward
            if(xpos != 0 || ypos != 0) {
                CommandScheduler.getInstance().schedule(
                        new SequentialCommandGroup(
                                new InstantCommand(() -> myHardware.turnDriveMotors(ServoPosition)), // Turn wheels
                                new InstantCommand(myHardware::moveForward) // Move forward
                        )
                );
            }
            // as long as turn doesnt equal null we turn
            if (turn != 0) {
                myHardware.turn(turn);
            }
        }
    }
}