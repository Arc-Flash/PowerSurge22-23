package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class mechme extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("backLeft");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("backRight");
        DcMotor armL = hardwareMap.dcMotor.get("andrew");
        DcMotor armR = hardwareMap.dcMotor.get("klaus");

        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        armL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armL.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        armR.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        int lucas = 0;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            if (gamepad2.dpad_up) {
                lucas = lucas - 20;
            }
            else if (gamepad2.dpad_down) {
                lucas = lucas(lucas) + 20;
            }

            armL.setTargetPosition(lucas);
            armR.setTargetPosition(lucas);

            if (lucas > 0 || lucas < 0) {
                armL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armL.setPower(1);
                armR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armR.setPower(1);
            }

            if (gamepad2.y) {
                lucas  = 0;
                armL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }

            double y = gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);
        }
    }

    private int lucas(int lucas) {
        return lucas;
    }
}