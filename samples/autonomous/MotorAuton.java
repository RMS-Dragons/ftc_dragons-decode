package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name = "motorAuton")
public class motorAuton extends LinearOpMode {

    private DcMotor motor;
 
    @Override
    public void runOpMode() {

        motor = hardwareMap.get(Servo.class, "motor");

        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); 
        motor.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        motor.setPower(0.5);
        sleep(2000);

        motor.setPower(-0.5);
        sleep(2000);
    
    }
}
