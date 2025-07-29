package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "servoToggle")
public class servoToggle extends LinearOpMode {

    private Servo servo;
    private double servoInitPos = 0.5;
    private boolean toggleState = false;
    private boolean lastButtonState = false;

    @Override
    public void runOpMode() {
        servo = hardwareMap.get(Servo.class, "servo");
        servo.setPosition(servoInitPos);

        waitForStart();

        while (opModeIsActive()) {
            boolean buttonState = gamepad1.a;

            if (buttonState && !lastButtonState) {
                toggleState = !toggleState;
                servo.setPosition(toggleState ? 1.0 : 0.0); // Immediately set position to 1 or 0
            }

            lastButtonState = buttonState;

            telemetry.addData("Servo Position", servo.getPosition());
            telemetry.update();
        }
    }
}
