package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "CRservo")
public class CRservo extends LinearOpMode {

    private Servo servo;
    private double servoInitPos = 0.5;

    @Override
    public void runOpMode() {
        servo = hardwareMap.get(Servo.class, "servo");
        servo.setPosition(servoInitPos);

        waitForStart();

        while (opModeIsActive()) {

            double  rightTrigger = gamepad1.right_trigger 
            double  leftTrigger = gamepad1.left_trigger 

            if (rightTrigger > 0) {
                servo.setPower(rightTrigger); // Clockwise rotation
            } else if (leftTrigger > 0) {
                servo.setPower(-leftTrigger); // Counterclockwise rotation
            } else {
                servo.setPower(0); // Stop the servo when no trigger is pressed
            }



            telemetry.addData("Servo Power", servo.getPower());
            telemetry.update();
        }
    }
}
