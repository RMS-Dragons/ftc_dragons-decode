package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "servoToggleGradual")
public class servoToggleGradual extends LinearOpMode {

    private Servo servo;
    private boolean toggleState = false;
    private boolean lastButtonState = false;
    private double targetPosition = 0.0;
    private final double stepSize = 0.1;
    private double servoInitPos = 0.5;

    @Override
    public void runOpMode() {
        servo = hardwareMap.get(Servo.class, "servo");
        servo.setPosition(servoInitPos);

        waitForStart();

        while (opModeIsActive()) {

            boolean buttonState = gamepad1.a;

            if (buttonState && !lastButtonState) {
                toggleState = !toggleState;
                targetPosition = toggleState ? 1.0 : 0.0;
            }
            lastButtonState = buttonState;

            double currentPosition = servo.getPosition();

            // Gradually move servo toward target
            if (currentPosition < targetPosition) {
                currentPosition = Math.min(currentPosition + stepSize, targetPosition);
                servo.setPosition(currentPosition);
            } else if (currentPosition > targetPosition) {
                currentPosition = Math.max(currentPosition - stepSize, targetPosition);
                servo.setPosition(currentPosition);
            }

            telemetry.addData("Servo Position", currentPosition);
            telemetry.addData("Target Position", targetPosition);
            telemetry.update();

        }
    }
}
