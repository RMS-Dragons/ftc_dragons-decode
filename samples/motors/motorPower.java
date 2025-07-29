package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "motorPower")
public class motorPower extends LinearOpMode {

    private DcMotor motor;
    private static final double GEAR_RATIO = 15.0;
    private static final double MOTOR_POWER = 0.5;

    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotor.class, "motor");

        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); 

        waitForStart();

        while (opModeIsActive()) {
            
            while (gamepad1.dpad_up) {
                motor.setPower(MOTOR_POWER);
            } while (gamepad1.dpad_down) {
                motor.setPower(-MOTOR_POWER); // Spin backward
            } else {
                motor.setPower(0); // Stop motor
            }

            // Telemetry
            telemetry.addData("Motor Power", motor.getPower());
            telemetry.addData("Motor Position ", motor.getCurrentPosition());
            telemetry.update();
        }
    }
}
