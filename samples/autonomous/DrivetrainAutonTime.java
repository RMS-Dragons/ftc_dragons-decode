package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "DrivetrainAuton")
public class DrivetrainAuton extends LinearOpMode {

    private DcMotor backright;
    private DcMotor backleft; 
    private DcMotor frontleft;
    private DcMotor frontright;

    @Override
    public void runOpMode() {

        backleft = hardwareMap.get(DcMotor.class, "backleftmotor");
        backright = hardwareMap.get(DcMotor.class, "backrightmotor");
        frontleft = hardwareMap.get(DcMotor.class, "frontleftmotor");
        frontright = hardwareMap.get(DcMotor.class, "frontrightmotor");

        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        // Forwards
        frontleft.setPower(0.5);
        frontright.setPower(0.5);
        backleft.setPower(0.5);
        backright.setPower(0.5);
        sleep(2000); // Miliseconds

        // Backwards
        frontleft.setPower(-0.5);
        frontright.setPower(-0.5);
        backleft.setPower(-0.5);
        backright.setPower(-0.5);   
        sleep(2000);

        // Strafe Right
        frontleft.setPower(0.5);
        frontright.setPower(-0.5);
        backleft.setPower(-0.5);
        backright.setPower(0.5);
        sleep(2000);

        // Strafe Left
        frontleft.setPower(-0.5);
        frontright.setPower(0.5);
        backleft.setPower(0.5);
        backright.setPower(-0.5);
        sleep(2000);

        // Turn Right
        frontleft.setPower(0.5);
        frontright.setPower(-0.5);
        backleft.setPower(0.5);
        backright.setPower(-0.5);   
        sleep(2000);

        // Turn Left
        frontleft.setPower(-0.5);
        frontright.setPower(0.5);
        backleft.setPower(-0.5);
        backright.setPower(0.1);           
        sleep(2000);

    }      
}
