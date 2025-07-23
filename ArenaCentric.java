package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "ArenaCentricDrive")
public class ArenaCentricDrive extends LinearOpMode {

    private DcMotorEx frontLeft, frontRight, backLeft, backRight;
    private BNO055IMU imu;

    @Override
    public void runOpMode() {
        // Motor setup
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontleftmotor");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontrightmotor");
        backLeft = hardwareMap.get(DcMotorEx.class, "backleftmotor");
        backRight = hardwareMap.get(DcMotorEx.class, "backrightmotor");

        frontLeft.setDirection(DcMotorEx.Direction.REVERSE);
        backLeft.setDirection(DcMotorEx.Direction.REVERSE);

        // IMU setup
        imu = hardwareMap.get(BHI260AP.class, "imu");
        BNO055IMU.Parameters imuParams = new BNO055IMU.Parameters();
        imuParams.angleUnit = BHI260AP.AngleUnit.RADIANS;
        imu.initialize(imuParams);

        waitForStart();

        while (opModeIsActive()) {
            // Reset yaw if needed
            if (gamepad1.x) {
                imu.resetYaw();
            }

            // Get joystick input
            double y = -gamepad1.left_stick_y;  // forward/back
            double x = gamepad1.left_stick_x;   // strafe
            double rx = gamepad1.right_stick_x; // rotate

            // Get bot heading from IMU
            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            // Rotate joystick input by bot heading for field-centric control
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            // Optional: compensate for imperfect strafing
            rotX *= 1.1;

            // Normalize motor powers
            double denom = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1.0);
            double frontLeftPower = (rotY + rotX + rx) / denom;
            double backLeftPower = (rotY - rotX + rx) / denom;
            double frontRightPower = (rotY - rotX - rx) / denom;
            double backRightPower = (rotY + rotX - rx) / denom;

            // Set motor powers
            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);

            // Telemetry
            telemetry.addData("Heading (radians)", botHeading);
            telemetry.update();
        }
    }
}
