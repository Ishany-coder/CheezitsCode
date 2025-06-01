package org.firstinspires.ftc.teamcode.CheezitsTeleop;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class SinglePodDrive {
    private final Servo topLeftServo1;
    private final Servo topLeftServo2;

    public SinglePodDrive(HardwareMap hardwareMap) {
        topLeftServo1 = hardwareMap.get(Servo.class, "topLeftServo1");
        topLeftServo2 = hardwareMap.get(Servo.class, "topLeftServo2");
    }

    // Moves forward and turns by directly setting servo positions (call repeatedly from loop)
    public void moveForwardAndTurn(double forwardPower, double turnPower) {
        double leftPower = Range.clip(forwardPower - turnPower, 0.0, 1.0);
        double rightPower = Range.clip(forwardPower + turnPower, 0.0, 1.0);

        topLeftServo1.setPosition(leftPower);
        topLeftServo2.setPosition(rightPower);
    }

    // Moves backward and turns by directly setting servo positions (call repeatedly from loop)
    public void moveBackwardAndTurn(double backwardPower, double turnPower) {
        double leftPower = Range.clip(1.0 - backwardPower + turnPower, 0.0, 1.0);
        double rightPower = Range.clip(1.0 - backwardPower - turnPower, 0.0, 1.0);

        topLeftServo1.setPosition(leftPower);
        topLeftServo2.setPosition(rightPower);
    }
    public double getAngle(double xPos, double yPos){
        return Math.toDegrees(Math.atan2(xPos, yPos));
    }

    // ✅ Stop all movement
    public void stopServos() {
        topLeftServo1.setPosition(0.5);
        topLeftServo2.setPosition(0.5);
    }
}