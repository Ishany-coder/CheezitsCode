package org.firstinspires.ftc.robotcontroller.internal.CheezitsTeleop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Cheezits Auto Servo", group="Cheezits")
public class ExampleFile extends LinearOpMode {

    private double xpos;
    private double ypos;
    private double servoPosition;
    private drive myHardware;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize hardware
        myHardware = new drive(this.hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart(); // Wait for the start signal

        // Example movement: Move forward for 3 seconds, then stop
        long moveTime = 3000; // Move for 3 seconds
        long startTime = System.currentTimeMillis();

        while (opModeIsActive() && (System.currentTimeMillis() - startTime < moveTime)) {
            xpos = 0;   // No strafing
            ypos = 1;   // Move forward

            // Calculate servo positions based on movement
            servoPosition = myHardware.getAngle(ypos, xpos);
            myHardware.turnDriveMotors(servoPosition);

            telemetry.addData("Moving Forward", "Servo Pos: %.2f", servoPosition);
            telemetry.update();
        }

        // Stop the servos after moving
        myHardware.stopServos();
    }
}