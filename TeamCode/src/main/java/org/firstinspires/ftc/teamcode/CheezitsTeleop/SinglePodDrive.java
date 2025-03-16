package org.firstinspires.ftc.teamcode.CheezitsTeleop;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class SinglePodDrive {
    private final double GearRatio = 0.1;
    private final Servo topLeftServo1;
    private final Servo topLeftServo2;

    public SinglePodDrive(HardwareMap hardwareMap) {
        topLeftServo1 = hardwareMap.get(Servo.class, "topLeftServo1");
        topLeftServo2 = hardwareMap.get(Servo.class, "topLeftServo2");
    }

    // 🛠 Improved Turn Function: Only moves one servo at a time
    public void turn(double turn) {
        double adjustedTurn = Range.clip(turn * GearRatio, -1.0, 1.0);

        if (turn > 0) {
            topLeftServo1.setPosition(0.5 + adjustedTurn * 0.5); // Rotate right
        } else if (turn < 0) {
            topLeftServo2.setPosition(0.5 - adjustedTurn * 0.5); // Rotate left
        }
    }

    public double getAngle(double ypos, double xpos) {
        return (Math.atan2(ypos, xpos)) / Math.PI;
    }

    // ✅ Moves forward while turning using one servo
    public void moveForwardAndTurn(double angle) {
        double adjustedAngle = Range.clip(angle, 0.0, 1.0);
        topLeftServo1.setPosition(adjustedAngle);
    }

    // ✅ Moves backward while turning using one servo
    public void moveBackwardAndTurn(double angle) {
        double adjustedAngle = Range.clip(angle, 0.0, 1.0);
        topLeftServo2.setPosition(adjustedAngle);
    }

    // ✅ Move Forward without turning
    public void moveForward() {
        topLeftServo1.setDirection(Servo.Direction.FORWARD);
        topLeftServo1.setPosition(1);
    }

    // ✅ Move Backward without turning
    public void moveBackward() {
        topLeftServo2.setDirection(Servo.Direction.REVERSE);
        topLeftServo2.setPosition(1);
    }

    // ✅ Stop all movement
    public void stopServos() {
        topLeftServo1.setPosition(0.5);
        topLeftServo2.setPosition(0.5);
    }
}