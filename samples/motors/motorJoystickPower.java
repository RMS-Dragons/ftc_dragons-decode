package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "motorJoystick")
public class motorJoystick extends LinearOpMode {

    private DcMotor motor;

    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotor.class, "motor");

        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); 

        waitForStart();

        while (opModeIsActive()) {
            
            double joystickInput = gamepad1.right_stick_y
            double denominator = Math.max(Math.abs(joystickInput), 1);

            if (joystickInput > 0.05) {
                motor.setPower(joystickInput / denominator); 
            }

            // Telemetry
            telemetry.addData("Motor Power", motor.getPower());
            telemetry.addData("Motor Position ", motor.getCurrentPosition());
            telemetry.update();
        }
    }
}
