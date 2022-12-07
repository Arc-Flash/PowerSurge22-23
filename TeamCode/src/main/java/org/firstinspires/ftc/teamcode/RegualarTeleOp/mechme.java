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
        elbowMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sl.setDirection(Servo.Direction.FORWARD);
        sr.setDirection(Servo.Direction.REVERSE);

        // initializing the shoulder and elbow targets at a non-zero value
        int sholderPosition = 0;
        int elbowJoint = 0;

        boolean sens = false;
        waitForStart();

        // continuous loop that runs after after the play button is pushed
        if (isStopRequested()) return;

        arm.setTargetPosition(sholderPosition);
        elbowMotor.setTargetPosition(elbowJoint);

        // Runs while the opmode is active
        while (opModeIsActive()) {

            //Runs when
            if (gamepad2.y) {
                //arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                elbowMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }

            // Runs when the dpad is pushed down on game-pad 2
            if (gamepad2.a) {
                sholderPosition = -2;

                arm.setTargetPosition(sholderPosition);
            }

            // Runs when the dpad is pushed up on game-pad 2
            if (gamepad2.x) {
                sholderPosition = -30;

                arm.setTargetPosition(sholderPosition);
            }


            // Runs when the dpad is pushed left on game-pad 2
            if (gamepad2.dpad_down) {
                elbowJoint = elbowJoint - 6;
                elbowMotor.setTargetPosition(elbowJoint);
            }

            // Runs when the dpad is pushed right on game-pad 2
            if (gamepad2.dpad_up) {
                elbowJoint = elbowJoint + 6;
                elbowMotor.setTargetPosition(elbowJoint);
            }


            elbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elbowMotor.setPower(1);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.setPower(1);


            //Runs when the left stick is pressed down
            //Moves servos position so that they are closed
            if (gamepad2.left_bumper) {
                sl.setPosition(1);
                sr.setPosition(1);
            }

            //Runs when the right stick is pressed down
            //Moves servos position so that they are open
            if (gamepad2.right_bumper) {
                sr.setPosition(.3);
                sl.setPosition(.3);
            }

            
            /*
            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = -gamepad1.right_stick_x;

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
            */

            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = -gamepad1.right_stick_x;
            final double v1 = -r * Math.cos(robotAngle) + rightX;
            final double v2 = -r * Math.sin(robotAngle) - rightX;
            final double v3 = -r * Math.sin(robotAngle) + rightX;
            final double v4 = -r * Math.cos(robotAngle) - rightX;

            if (gamepad1.right_bumper) {
                sens = true;
            }
            else {
                sens = false;
            }


            if (gamepad2.left_trigger == 1 && gamepad2.right_trigger == 1) {
                motorFrontLeft.setPower(1);
                motorBackLeft.setPower(1);
                motorBackRight.setPower(1);
                motorFrontRight.setPower(1);
            }
            else if (sens == true) {
                motorFrontLeft.setPower(v1/3);
                motorFrontRight.setPower(v2/3);
                motorBackLeft.setPower(v3/3);
                motorBackRight.setPower(v4/3);
            }
            else {
                motorFrontLeft.setPower(v1);
                motorFrontRight.setPower(v2);
                motorBackLeft.setPower(v3);
                motorBackRight.setPower(v4);
            }

            telemetry.addData("20 target:", elbowMotor.getTargetPosition());
            telemetry.addData("20 current:", elbowMotor.getCurrentPosition());
            telemetry.addData("20 power:", elbowMotor.getPower());
            telemetry.addData("Arm Power" , arm.getPower());
            telemetry.addData("Arm Target", arm.getTargetPosition());
            telemetry.addData("Arm Current", arm.getCurrentPosition());
            telemetry.addData("Servo 1 position", sr.getPosition());
            telemetry.addData("Servo Left Position", sl.getPosition());
            telemetry.update();
        }
    }

}