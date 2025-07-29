package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Drivetrain")
public class Drivetrain extends LinearOpMode {

    // Defining each of the motors as DcMotorEx for power control
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotorEx frontleftmotor = null;
    private DcMotorEx backleftmotor = null;
    private DcMotorEx frontrightmotor = null;
    private DcMotorEx backrightmotor = null;

    private double drive_max_power = 1; 

    @Override
    public void runOpMode() {
        // Initialize motors as DcMotorEx 
        frontleftmotor = hardwareMap.get(DcMotorEx.class, "frontleftmotor");
        backleftmotor = hardwareMap.get(DcMotorEx.class, "backleftmotor");
        frontrightmotor = hardwareMap.get(DcMotorEx.class, "frontrightmotor");
        backrightmotor = hardwareMap.get(DcMotorEx.class, "backrightmotor");

        // Configure drivetrain motors
        frontleftmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontleftmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Set motor directions
        frontleftmotor.setDirection(DcMotor.Direction.REVERSE);
        backleftmotor.setDirection(DcMotor.Direction.REVERSE);
        frontrightmotor.setDirection(DcMotor.Direction.FORWARD);
        backrightmotor.setDirection(DcMotor.Direction.FORWARD);


        // Preventing extra movement in the wheels
        frontleftmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontrightmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleftmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backrightmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while (opModeIsActive()) {
            // Drivetrain power control logic
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

            frontleftmotor.setPower(drive_max_power * ((y + x + rx) / denominator));
            backleftmotor.setPower(drive_max_power * ((y - x + rx) / denominator));
            frontrightmotor.setPower(drive_max_power * ((y - x - rx) / denominator));
            backrightmotor.setPower(drive_max_power * ((y + x - rx) / denominator));
        }

    }
}