package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name = "Autonomous2", group = "Autonomous")
public class Autonomous2 extends LinearOpMode {

    @Override
    public void runOpMode() {
        // Initialize your RoadRunner drive
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        // Define your starting position on the field
        Pose2d startPose = new Pose2d(0, 0, 0);
        drive.setPoseEstimate(startPose);

        // Build a simple trajectory: move forward 30 inches
        Trajectory forwardTraj = drive.trajectoryBuilder(startPose)
                .forward(12)
                .build();

        // Wait for start
        waitForStart();

        if (isStopRequested()) return;

        // Follow the trajectory
        drive.followTrajectory(forwardTraj);

        // Optional: Add more steps
        // Turn
        drive.turn(Math.toRadians(90));

        // Strafe
        Trajectory strafeTraj = drive.trajectoryBuilder(drive.getPoseEstimate())
                .strafeLeft(20)
                .build();

        drive.followTrajectory(strafeTraj);
    }
}
