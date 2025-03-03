package org.firstinspires.ftc.robotcontroller.internal.CheezitsAuto;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.geometry.Pose2d;
import org.firstinspires.ftc.robotcontroller.internal.CheezitsTeleop.Drive;

public class DriveSplineCommand extends CommandBase {
    private final Drive drive;
    private final SquIDController squidController;
    private final Pose2d startPose, endPose;
    private final long duration;
    private long startTime;

    public DriveSplineCommand(Drive drive, SquIDController squidController, Pose2d start, Pose2d end, double timeSeconds) {
        this.drive = drive;
        this.squidController = squidController;
        this.startPose = start;
        this.endPose = end;
        this.duration = (long) (timeSeconds * 1000);
        addRequirements((Subsystem) drive);
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void execute() {
        // Calculate movement correction using SquIDController (Fixed to pass a double)
        double movement = squidController.calculate(startPose.getTranslation().getDistance(endPose.getTranslation()), 0);

        // Convert movement to an angle for servo control
        double angle = movement / Math.PI;

        // Apply movement to servos via Drive class
        drive.turnDriveMotors(angle);
    }

    @Override
    public boolean isFinished() {
        return System.currentTimeMillis() - startTime >= duration;
    }

    @Override
    public void end(boolean interrupted) {
        drive.stopServos();
    }
}