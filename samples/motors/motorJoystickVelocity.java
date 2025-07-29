package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "motorJoystick")
public class motorJoystick extends LinearOpMode {

    private DcMotorEx motor;

    private static final double GEAR_RATIO = 15.0; // define your gear ratio here

    private static final int TICKS_PER_MOTOR_REV = 28;
    private static final int TICKS_PER_OUTPUT_REV = (int) (TICKS_PER_MOTOR_REV * GEAR_RATIO);

    private static final double OUTPUT_RPS = 1.0;
    private static final double TICKS_PER_SECOND = OUTPUT_RPS * TICKS_PER_OUTPUT_REV;

    @Override
    public void runOpMode() {

        motor = hardwareMap.get(DcMotorEx.class, "motor");
        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER); // required for velocity control

        waitForStart();

        while (opModeIsActive()) {

            double joystickInput = -gamepad2.right_stick_y;

            if (joystickInput > 0.1) {
                motor.setVelocity(joystickInput * TICKS_PER_SECOND);
            } else if (joystickInput < -0.1) {
                motor.setVelocity(joystickInput * TICKS_PER_SECOND); /
            } else {
                motor.setVelocity(0);
            }

            telemetry.addData("Joystick Input", joystickInput);
            telemetry.addData("Motor Current Position", motor.getCurrentPosition());
            telemetry.addData("Motor Velocity (ticks/s)", motor.getVelocity());
            telemetry.update();
        }
    }
}
