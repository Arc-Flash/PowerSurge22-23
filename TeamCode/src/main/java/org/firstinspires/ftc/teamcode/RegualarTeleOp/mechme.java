package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

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
        //DcMotor armR = hardwareMap.dcMotor.get("klaus");
        DcMotor potato = hardwareMap.dcMotor.get("20centpotatoes");
        Servo sl = hardwareMap.servo.get("s1");
        Servo sr = hardwareMap.servo.get("sr");


        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        armL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //armR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //armR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        potato.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        potato.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        sl.setDirection(Servo.Direction.FORWARD);
        sr.setDirection(Servo.Direction.REVERSE);

        int lucas = 0;
        int aiden = 0;
        int lev = 0;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            /*
            if (gamepad2.dpad_up) {
                lucas = lucas - 1;
            }
            else if (gamepad2.dpad_down) {
                lucas = lucas + 1;
            }

            if (gamepad2.dpad_left) {
                aiden = aiden -1;
            }
            if (gamepad2.dpad_right) {
                aiden = aiden + 1;
            }

            armL.setTargetPosition(lucas);
            armR.setTargetPosition(lucas);
            potato.setTargetPosition(aiden);

            if (lucas > 0 || lucas < 0) {
                armL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armL.setPower(.2);
                armR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armR.setPower(.2);
                potato.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                potato.setPower(.2);
            }
            */
            if (gamepad2.y) {
                armL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                //armR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                potato.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }


            if (gamepad2.dpad_down) {
                lucas = lucas - 2;
                armL.setTargetPosition(lucas);
                //armR.setTargetPosition(lucas);
            }
            else if (gamepad2.dpad_up) {
                lucas = lucas + 2;
                armL.setTargetPosition(lucas);
                //armR.setTargetPosition(lucas);
            }

            if (gamepad2.dpad_left) {
                lev++;
                potato.setTargetPosition(lev);
            }

            if (gamepad2.dpad_right) {
                lev--;
                potato.setTargetPosition(lev);
            }

            if (gamepad2.left_bumper) {
                potato.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                potato.setPower(.1);
            }




            if (gamepad2.right_bumper) {
                armL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //armR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armL.setPower(1);
                //armR.setPower(1);
            }

            if (gamepad2.left_bumper) {
                potato.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                potato.setPower(1);
            }

            if (gamepad2.left_stick_button) {
                sl.setPosition(1);
            }
            if (gamepad2.right_stick_button) {
                sr.setPosition(1);
            }

            //potato.setPower(gamepad2.right_stick_y);
            

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

            telemetry.addData("ArmL data:", armL.getTargetPosition());
            //telemetry.addData("ArmR Target", armR.getTargetPosition());
            telemetry.addData("ArmL current:", armL.getCurrentPosition());
            //telemetry.addData("ArmR current:", armR.getCurrentPosition());
            telemetry.update();
        }
    }

}