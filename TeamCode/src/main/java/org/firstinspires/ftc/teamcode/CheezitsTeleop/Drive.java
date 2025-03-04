package org.firstinspires.ftc.teamcode.CheezitsTeleop;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Drive {
    private final int GearRatio = 8;
    private final Servo topLeftServo1;
    private final Servo topLeftServo2;
    private final Servo topRightServo1;
    private final Servo topRightServo2;
    private final Servo bottomLeftServo1;
    private final Servo bottomLeftServo2;
    private final Servo bottomRightServo1;
    private final Servo bottomRightServo2;

    public Drive(HardwareMap hardwareMap) {
        topLeftServo1 = hardwareMap.get(Servo.class, "topLeftServo1");
        topLeftServo2 = hardwareMap.get(Servo.class, "topLeftServo2");
        topRightServo1 = hardwareMap.get(Servo.class, "topRightServo1");
        topRightServo2 = hardwareMap.get(Servo.class, "topRightServo2");
        bottomLeftServo1 = hardwareMap.get(Servo.class, "bottomLeftServo1");
        bottomLeftServo2 = hardwareMap.get(Servo.class, "bottomLeftServo2");
        bottomRightServo1 = hardwareMap.get(Servo.class, "bottomRightServo1");
        bottomRightServo2 = hardwareMap.get(Servo.class, "bottomRightServo2");
    }

    public void turn(double turn) {
        double adjustedTurn = turn * GearRatio;

        int wholeRotations = (int) adjustedTurn;  // Number of whole rotations
        double fractionalRotation = adjustedTurn - wholeRotations; // Remaining fractional rotation

        for (int i = 0; i < wholeRotations; i++) {
            setServoPositions(1, 1, 1, 1, 1, 1, 1, 1);
            delay(50); // Allow servos time to complete each rotation
            setServoPositions(0, 0, 0, 0, 0, 0, 0, 0);
        }

        if (fractionalRotation > 0) {
            setServoPositions(fractionalRotation, fractionalRotation, fractionalRotation, fractionalRotation,
                    fractionalRotation, fractionalRotation, fractionalRotation, fractionalRotation);
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
            setServoPositions(1, 1, 1, 1, 1, 1, 1, 1);
            delay(50);
            setServoPositions(0, 0, 0, 0, 0, 0, 0, 0);
        }

        if (fractionalRotation > 0) {
            setServoPositions(fractionalRotation, fractionalRotation, fractionalRotation, fractionalRotation,
                    fractionalRotation, fractionalRotation, fractionalRotation, fractionalRotation);
        }
    }

    public void moveForward() {
        setServoPositions(1, 1, 1, 1, 1, 1, 1, 1);
    }

    public void moveBackward() {
        setServoPositions(0, 0, 0, 0, 0, 0, 0, 0);
    }

    public void stopServos() {
        setServoPositions(topLeftServo1.getPosition(), topLeftServo2.getPosition(),
                topRightServo1.getPosition(), topRightServo2.getPosition(),
                bottomLeftServo1.getPosition(), bottomLeftServo2.getPosition(),
                bottomRightServo1.getPosition(), bottomRightServo2.getPosition());
    }

    private void setServoPositions(double tl1, double tl2, double tr1, double tr2,
                                   double bl1, double bl2, double br1, double br2) {
        topLeftServo1.setPosition(tl1);
        topLeftServo2.setPosition(tl2);
        topRightServo1.setPosition(tr1);
        topRightServo2.setPosition(tr2);
        bottomLeftServo1.setPosition(bl1);
        bottomLeftServo2.setPosition(bl2);
        bottomRightServo1.setPosition(br1);
        bottomRightServo2.setPosition(br2);
    }

    private void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}