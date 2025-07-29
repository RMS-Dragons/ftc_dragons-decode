package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "servo")
public class servo extends LinearOpMode {

    private Servo servo;
    private double servoInitPos = 0.5; 

    @Override
    public void runOpMode() {
        servo = hardwareMap.get(Servo.class, "servo");
        servo.setPosition(servoInitPos);

        waitForStart();

        while (opModeIsActive()) {
            double joystickValue = -gamepad1.right_stick_y; 
            double joystickSensitivity = 0.5;

            // Calculate new position and clip it to [0.0, 1.0]
            double targetPos = servoInitPos + (joystickValue * joystickSensitivity);
            targetPos = Math.max(0.0, Math.min(1.0, targetPos));

            servo.setPosition(targetPos);

            telemetry.addData("Servo Position", servo.getPosition());
            telemetry.update();
        }
    }
}
