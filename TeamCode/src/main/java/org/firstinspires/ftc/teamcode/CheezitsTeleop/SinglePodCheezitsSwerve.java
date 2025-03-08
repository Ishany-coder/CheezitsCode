package org.firstinspires.ftc.teamcode.CheezitsTeleop;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class SinglePodCheezitsSwerve {
    private final int GearRatio = 8;
    private final Servo topLeftServo1;
    private final Servo topLeftServo2;

    public SinglePodCheezitsSwerve(HardwareMap hardwareMap) {
        topLeftServo1 = hardwareMap.get(Servo.class, "topLeftServo1");
        topLeftServo2 = hardwareMap.get(Servo.class, "topLeftServo2");
    }


    public void turn(double turn) {
        double adjustedTurn = turn * GearRatio;

        int wholeRotations = (int) adjustedTurn;  // Number of whole rotations
        double fractionalRotation = adjustedTurn - wholeRotations; // Remaining fractional rotation

        for (int i = 0; i < wholeRotations; i++) {
            setServoPositions(1, 1);
            delay(50); // Allow servos time to complete each rotation
            setServoPositions(0, 0);
        }

        if (fractionalRotation > 0) {
            setServoPositions(fractionalRotation, fractionalRotation);
        }
    }

    public double getAngle(double ypos, double xpos) {
        return (Math.atan2(ypos, xpos)) / Math.PI;
    }

    public void turnDriveMotors(double angle) {
        double adjustedAngle = angle * GearRatio;

        int wholeRotations = (int) adjustedAngle;
        double fractionalRotation = adjustedAngle - wholeRotations;

        for (int i = 0; i < wholeRotations; i++) {
            setServoPositions(1, 1);
            delay(50);
            setServoPositions(0, 0);
        }

        if (fractionalRotation > 0) {
            setServoPositions(fractionalRotation, fractionalRotation);
        }
    }

    public void moveForward() {
        // move Forward
        topLeftServo1.setDirection(Servo.Direction.REVERSE);
        ContinouslyRotate();
    }

    public void moveBackward() {
        // move Backward
        topLeftServo2.setDirection(Servo.Direction.REVERSE);
        ContinouslyRotate();
    }

    public void stopServos() {
        setServoPositions(topLeftServo1.getPosition(), topLeftServo2.getPosition());
    }

    private void setServoPositions(double tl1, double tl2) {
        topLeftServo1.setPosition(tl1);
        topLeftServo2.setPosition(tl2);
    }

    private void ContinouslyRotate() {
        if (topLeftServo1.getPosition() >= 1 || topLeftServo2.getPosition() >= 1) {
            topLeftServo1.setPosition(0);
            topLeftServo2.setPosition(0);
        } else {
            topLeftServo1.setPosition(topLeftServo1.getPosition() + 0.1);
            topLeftServo2.setPosition(topLeftServo2.getPosition() + 0.1);
        }
    }

    private void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}