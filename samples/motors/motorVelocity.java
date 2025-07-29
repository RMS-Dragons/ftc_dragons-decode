package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;


@TeleOp(name = "motorVelocity")
public class motorVelocity extends LinearOpMode {

    private DcMotorEx motor;

    // Gear ratio (easily changeable)
    private static final double GEAR_RATIO = 15.0;

    // Encoder specs
    private static final int TICKS_PER_MOTOR_REV = 28; // Internal motor ticks
    private static final int TICKS_PER_OUTPUT_REV = (int) (TICKS_PER_MOTOR_REV * GEAR_RATIO);

    // Motor speed in revolutions per second (at the output shaft)
    private static final double OUTPUT_RPS = 1.0; // change this to adjust speed
    private static final double TICKS_PER_SECOND = OUTPUT_RPS * TICKS_PER_OUTPUT_REV;

    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotorEx.class, "motor");

        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            while (gamepad1.dpad_up) {
                motor.setVelocity(TICKS_PER_SECOND); // Spin forward
            } while (gamepad1.dpad_down) {
                motor.setVelocity(-TICKS_PER_SECOND); // Spin backward
            } else {
                motor.setVelocity(0); // Stop motor
            }

            // Telemetry
            telemetry.addData("Motor Velocity (tps)", motor.getVelocity());
            telemetry.addData("Motor Position (ticks)", motor.getCurrentPosition());
            telemetry.update();
        }
    }
}
