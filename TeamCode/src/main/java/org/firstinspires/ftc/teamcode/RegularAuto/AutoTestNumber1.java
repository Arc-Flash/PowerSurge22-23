package org.firstinspires.ftc.teamcode.RegularAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class AutoTestNumber1 extends LinearOpMode {
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



        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            //Please help idk wut any of this stuff mean ahfoshfaoiusdfoausdhfduhfoquwehrqerqwerqwer
            double frontLeftPower = 10;
            double backLeftPower = 10;
            double frontRightPower = 10;
            double backRightPower = 10;
            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);
            Thread.sleep(1000);
            frontLeftPower = 0;
            backLeftPower = 0;
            frontRightPower = 0;
            backRightPower = 0;
            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);
            Thread.sleep(3000);
            frontLeftPower = 10;
            backLeftPower = 10;
            frontRightPower = 10;
            backRightPower = 10;
            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);
            Thread.sleep(1000);
            frontLeftPower = 0;
            backLeftPower = 0;
            frontRightPower = 0;
            backRightPower = 0;
            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]

            telemetry.addData("ArmL data:", armL.getTargetPosition());
            //telemetry.addData("ArmR Target", armR.getTargetPosition());
            telemetry.addData("ArmL current:", armL.getCurrentPosition());
            //telemetry.addData("ArmR current:", armR.getCurrentPosition());
            telemetry.update();
        }
    }

}

