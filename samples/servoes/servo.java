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

        if (gamepad1.a) {
            servo.setPosition(1);
        }
        else if (gamepad1.b) {
            servo.setPosition(0)
        }



            telemetry.addData("Servo Position", servo.getPosition);
            telemetry.update();
        }
    }
}
