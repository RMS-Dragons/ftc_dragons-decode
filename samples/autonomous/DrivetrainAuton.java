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

    private int backleftPos;
    private int backrightPos;
    private int frontleftPos;
    private int frontrightPos;

    private static final int GEAR_RATIO = 15;
    private static final int ENCODE_COUNTS_PER_REV = (28 * GEAR_RATIO); 
    private static final double WHEEL_DIAMETER_INCHES = 3.7795275591; 
    private static final double COUNTS_PER_INCH = (ENCODER_COUNTS_PER_REV) / (Math.PI * WHEEL_DIAMETER_INCHES);

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

        backleftPos = 0;
        backrightPos = 0;
        frontrightPos = 0;
        frontleftPos = 0;

        waitForStart();

        private void drive(int frontleftTarget, int frontrightTarget, int backleftTarget, int backrightTarget) {
            frontleftPos += frontleftTarget;
            frontrightPos += frontrightTarget;
            backleftPos += backleftTarget;
            backrightPos += backrightTarget;
    
            frontleft.setTargetPosition(frontleftPos);
            frontright.setTargetPosition(frontrightPos);
            backleft.setTargetPosition(backleftPos);
            backright.setTargetPosition(backrightPos);
    
            frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    
            frontleft.setPower(0.5);
            frontright.setPower(0.5);
            backleft.setPower(0.5);
            backright.setPower(0.5);
        }
        // Forwards
        int frontLeftCounts = (int) (10 * COUNTS_PER_INCH);
        int frontRightCounts = (int) (10 * COUNTS_PER_INCH);
        int backLefttCounts = (int) (10 * COUNTS_PER_INCH);
        int backRightCounts = (int) (10 * COUNTS_PER_INCH);
        drive(frontLeftCounts, frontRightCounts, backLeftCounts, backRightCounts);

        while (opModeIsActive() && (backleft.isBusy() || backright.isBusy() || frontleft.isBusy() || frontright.isBusy())) {}

        // Backwards
        int frontLeftCounts1 = (int) (10 * -COUNTS_PER_INCH);
        int frontRightCounts1 = (int) (10 * -COUNTS_PER_INCH);
        int backLefttCounts1 = (int) (10 * -COUNTS_PER_INCH);
        int backRightCounts1 = (int) (10 * -COUNTS_PER_INCH);
        drive(frontLeftCounts1, frontRightCounts1, backLeftCounts1, backRightCounts1);

        while (opModeIsActive() && (backleft.isBusy() || backright.isBusy() || frontleft.isBusy() || frontright.isBusy())) {} 

        // Strafe Left
        int frontLeftCounts2 = (int) (10 * -COUNTS_PER_INCH);
        int frontRightCounts2 = (int) (10 * COUNTS_PER_INCH);
        int backLefttCounts2 = (int) (10 * COUNTS_PER_INCH);
        int backRightCounts2 = (int) (10 * -COUNTS_PER_INCH);
        drive(frontLeftCounts2, frontRightCounts2, backLeftCounts2, backRightCounts2);

        while (opModeIsActive() && (backleft.isBusy() || backright.isBusy() || frontleft.isBusy() || frontright.isBusy())) {}

        // Strafe Right
        int frontLeftCounts3 = (int) (10 * COUNTS_PER_INCH);
        int frontRightCounts3 = (int) (10 * -COUNTS_PER_INCH);
        int backLefttCounts3 = (int) (10 * -COUNTS_PER_INCH);
        int backRightCounts3 = (int) (10 * COUNTS_PER_INCH);
        drive(frontLeftCounts3, frontRightCounts3, backLeftCounts3, backRightCounts3);

        while (opModeIsActive() && (backleft.isBusy() || backright.isBusy() || frontleft.isBusy() || frontright.isBusy())) {}

         //  Turn Left
        int frontLeftCounts4 = (int) (48 * -COUNTS_PER_INCH);
        int frontRightCounts4 = (int) (48 * COUNTS_PER_INCH);
        int backLefttCounts4 = (int) (48 * -COUNTS_PER_INCH);
        int backRightCounts4 = (int) (48 * COUNTS_PER_INCH);
        drive(frontLeftCounts4, frontRightCounts4, backLeftCounts4, backRightCounts4);

        while (opModeIsActive() && (backleft.isBusy() || backright.isBusy() || frontleft.isBusy() || frontright.isBusy())) {}

         //  Turn Right
         int frontLeftCounts5 = (int) (48 * COUNTS_PER_INCH);
         int frontRightCounts5 = (int) (48 * -COUNTS_PER_INCH);
         int backLefttCounts5 = (int) (48 * COUNTS_PER_INCH);
         int backRightCounts5 = (int) (48 * -COUNTS_PER_INCH);
         drive(frontLeftCounts5, frontRightCounts5, backLeftCounts5, backRightCounts5);
 
         while (opModeIsActive() && (backleft.isBusy() || backright.isBusy() || frontleft.isBusy() || frontright.isBusy())) {}


        backleft.setPower(0);
        backright.setPower(0);
        frontleft.setPower(0);
        frontright.setPower(0);
    }      
}
