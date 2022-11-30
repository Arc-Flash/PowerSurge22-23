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

        //Auto code goes below -Cameron
        motorFrontLeft.setPower(1);
        motorBackLeft.setPower(1);
        motorFrontRight.setPower(1);
        motorBackRight.setPower(1);

        Thread.sleep(2000);
        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);
    }

}

