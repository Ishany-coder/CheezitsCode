package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class drive {
    private Servo topLeftServo1;
    private Servo topLeftServo2;
    private Servo topRightServo1;
    private Servo topRightServo2;
    private Servo bottomLeftServo1;
    private Servo bottomLeftServo2;
    private Servo bottomRightServo1;
    private Servo bottomRightServo2;
    private double theta;

    public drive(HardwareMap hardwareMap){
        topLeftServo1 = hardwareMap.get(Servo.class, "topLeftServo1");
        topLeftServo2 = hardwareMap.get(Servo.class, "topLeftServo2");
        topRightServo1 = hardwareMap.get(Servo.class, "topRightServo1");
        topRightServo2 = hardwareMap.get(Servo.class, "topRightServo2");
        bottomLeftServo1 = hardwareMap.get(Servo.class, "bottomLeftServo1");
        bottomLeftServo2 = hardwareMap.get(Servo.class, "bottomLeftServo2");
        bottomRightServo1 = hardwareMap.get(Servo.class, "bottomRightServo1");
        bottomRightServo2 = hardwareMap.get(Servo.class, "bottomRightServo2");
    }

    public void turn(double turn){
        if (turn > 0){
            topLeftServo1.setPosition(0.5);
            topLeftServo2.setPosition(0.5);
            topRightServo1.setPosition(0);
            topRightServo2.setPosition(0);
            bottomRightServo1.setPosition(0.5);
            bottomRightServo2.setPosition(0.5);
            bottomLeftServo1.setPosition(0);
            bottomLeftServo2.setPosition(0);
        }
        else if(turn < 0){
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
    public double getAngle(double ypos, double xpos){
        // java function atan 2 returns in radians so divide by pi to get servo position
        theta = (Math.atan(ypos/xpos))/Math.PI;
        return theta;
    }

    public void turnDriveMotors(double angle){
        topLeftServo1.setPosition(angle);
        topLeftServo2.setPosition(angle);
        topRightServo1.setPosition(angle);
        topRightServo2.setPosition(angle);
        bottomRightServo1.setPosition(angle);
        bottomRightServo2.setPosition(angle);
        bottomLeftServo1.setPosition(angle);
        bottomLeftServo2.setPosition(angle);
    }
    public void moveForward(double ypos){
        if(ypos>0){
            // Move forward
        }
        else if(ypos<0){
            // move backward
        }
    }
}