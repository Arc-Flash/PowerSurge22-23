package org.firstinspires.ftc.teamcode.RegualarTeleOp;

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
        DcMotor arm = hardwareMap.dcMotor.get("andrew"); //Motor attatched to the back of the robot
        DcMotor elbowMotor = hardwareMap.dcMotor.get("20centpotatoes"); // Motor that is locate on the arm
        Servo sl = hardwareMap.servo.get("s1");
        Servo sr = hardwareMap.servo.get("sr");


        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorFrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elbowMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elbowMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        sl.setDirection(Servo.Direction.FORWARD);
        sr.setDirection(Servo.Direction.REVERSE);

        // initializing the shoulder and elbow targets at a non-zero value
        int sholderPosition = 1;
        int elbowJoint = 1;

        waitForStart();

        // continuous loop that runs after after the play button is pushed
        if (isStopRequested()) return;

        arm.setTargetPosition(sholderPosition);
        elbowMotor.setTargetPosition(elbowJoint);

        // Runs while the opmode is active
        while (opModeIsActive()) {

            //Runs when
            if (gamepad2.y) {
                arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                elbowMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }

            // Runs when the dpad is pushed down on game-pad 2
            if (gamepad2.dpad_down) {
                sholderPosition = sholderPosition - 2;
                arm.setTargetPosition(sholderPosition);
            }

            // Runs when the dpad is pushed up on game-pad 2
            if (gamepad2.dpad_up) {
                sholderPosition = sholderPosition + 2;
                arm.setTargetPosition(sholderPosition);
            }

            // Runs when the dpad is pushed left on game-pad 2
            if (gamepad2.dpad_left) {
                elbowJoint = -6;
                elbowMotor.setTargetPosition(elbowJoint);
            }

            // Runs when the dpad is pushed right on game-pad 2
            if (gamepad2.dpad_right) {
                elbowJoint = 6;
                elbowMotor.setTargetPosition(elbowJoint);
            }



            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.setPower(1);
            elbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elbowMotor.setPower(1);

            //Runs when the left stick is pressed down
            //Moves servos position so that they are closed
            if (gamepad2.left_stick_button) {
                sl.setPosition(1);
                sr.setPosition(1);
            }

            //Runs when the right stick is pressed down
            //Moves servos position so that they are open
            if (gamepad2.right_stick_button) {
                sr.setPosition(.1);
                sl.setPosition(.1);
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

            telemetry.addData("ArmL data:", arm.getTargetPosition());
            telemetry.addData("ArmL current:", arm.getCurrentPosition());
            telemetry.addData("20 target:", elbowMotor.getTargetPosition());
            telemetry.addData("20 current:", elbowMotor.getCurrentPosition());
            telemetry.addData("20 power:", elbowMotor.getPower());
            telemetry.update();
        }
    }

}