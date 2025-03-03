package org.firstinspires.ftc.robotcontroller.internal.CheezitsTeleop;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Drive {
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

    /**
     * Turns the drivetrain servos to simulate turning movement.
     * @param turn The turn direction (positive = right, negative = left).
     */
    public void turn(double turn) {
        if (turn > 0) {
            topLeftServo1.setPosition(0.5);
            topLeftServo2.setPosition(0.5);
            topRightServo1.setPosition(0);
            topRightServo2.setPosition(0);
            bottomRightServo1.setPosition(0.5);
            bottomRightServo2.setPosition(0.5);
            bottomLeftServo1.setPosition(0);
            bottomLeftServo2.setPosition(0);
        } else if (turn < 0) {
            topLeftServo1.setPosition(0);
            topLeftServo2.setPosition(0);
            topRightServo1.setPosition(0.5);
            topRightServo2.setPosition(0.5);
            bottomRightServo1.setPosition(0);
            bottomRightServo2.setPosition(0);
            bottomLeftServo1.setPosition(0.5);
            bottomLeftServo2.setPosition(0.5);
        }
    }

    /**
     * Converts movement input to servo angles and applies it.
     * @param ypos The forward/backward movement input.
     * @param xpos The left/right movement input.
     * @return The calculated angle for servo movement.
     */
    public double getAngle(double ypos, double xpos) {
        return (Math.atan2(ypos, xpos)) / Math.PI; // Normalize between [-1,1]
    }

    /**
     * Moves the drivetrain in a specific direction.
     * @param angle The movement angle for servos.
     */
    public void turnDriveMotors(double angle) {
        topLeftServo1.setPosition(angle);
        topLeftServo2.setPosition(angle);
        topRightServo1.setPosition(angle);
        topRightServo2.setPosition(angle);
        bottomRightServo1.setPosition(angle);
        bottomRightServo2.setPosition(angle);
        bottomLeftServo1.setPosition(angle);
        bottomLeftServo2.setPosition(angle);
    }

    /**
     * Moves the robot forward.
     */
    public void moveForward() {
        // Implement forward movement logic
    }

    /**
     * Moves the robot backward.
     */
    public void moveBackward() {
        // Implement backward movement logic
    }

    /**
     * Stops all servos by maintaining their current position.
     */
    public void stopServos() {
        topLeftServo1.setPosition(topLeftServo1.getPosition());
        topLeftServo2.setPosition(topLeftServo2.getPosition());
        topRightServo1.setPosition(topRightServo1.getPosition());
        topRightServo2.setPosition(topRightServo2.getPosition());
        bottomLeftServo1.setPosition(bottomLeftServo1.getPosition());
        bottomLeftServo2.setPosition(bottomLeftServo2.getPosition());
        bottomRightServo1.setPosition(bottomRightServo1.getPosition());
        bottomRightServo2.setPosition(bottomRightServo2.getPosition());
    }
}