package org.firstinspires.ftc.teamcode.CheezitsTeleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class CheezitsSwerve extends LinearOpMode {
    private double turn;
    private double xpos;
    private double ypos;
    private double ServoPosition;
    drive myHardware = new drive(this.hardwareMap);
    @Override
    public void runOpMode() throws InterruptedException {
        turn = -gamepad1.left_stick_x;
        xpos = -gamepad1.right_stick_x;
        ypos = -gamepad1.right_stick_y;
        while(opModeIsActive()) {
            ServoPosition = myHardware.getAngle(ypos, xpos);
            myHardware.turnDriveMotors(ServoPosition);
            myHardware.turn(turn);
        }
    }
}
