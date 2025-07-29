package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "motorMacros")
public class motorMacros extends LinearOpMode {

    private DcMotorEx motor;

    private static final int TARGET_POSITION_UP = 1000;
    private static final int TARGET_POSITION_DOWN = 0;

    private boolean targetIsUp = false;  // track current target
    private boolean lastAState = false;

    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotorEx.class, "motor");
        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            boolean currentAState = gamepad1.a;

            // Detect button press (rising edge)
            if (currentAState && !lastAState) {
                if (!targetIsUp) {
                    // Move motor to UP position
                    motor.setTargetPosition(TARGET_POSITION_UP);
                    motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                    motor.setPower(0.7);
                    targetIsUp = true;
                } else {
                    // Move motor back DOWN
                    motor.setTargetPosition(TARGET_POSITION_DOWN);
                    motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                    motor.setPower(0.7);
                    targetIsUp = false;
                }
            }

            lastAState = currentAState;

            // Stop motor when target reached
            if (!motor.isBusy()) {
                motor.setPower(0);
                motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            }

            telemetry.addData("Motor Position", motor.getCurrentPosition());
            telemetry.addData("Motor Power", motor.getPower());
            telemetry.addData("Target Is Up?", targetIsUp);
            telemetry.update();
        }
    }
}
