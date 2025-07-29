package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name = "servoAuton")
public class servoAuton extends LinearOpMode {

    private Servo servo;
    private double servoInitPos = 0.5;

    @Override
    public void runOpMode() {

        servo = hardwareMap.get(Servo.class, "servo");
        servo.setPosition(servoInitPos);

        waitForStart();

        servo.setPosition(1);

        sleep(500);

        servo.setPosition(0);
    
    }
}
