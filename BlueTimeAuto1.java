package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Micah, Elaine, Alex, and Arian on 1/22/2016.
 */



public class BlueTimeAuto  extends LinearOpMode {

    // 7:26am 1/28/16 Whitefield Robotics Programming Team. #4444 The RoboCats!

    double br1Position;
    double br1Delta = 0.1;
    double br2Position;
    double br2Delta = 0.1;

    //drive motors.
    DcMotor motorRight;
    DcMotor motorLeft;

    //lift mechanism motors.
    DcMotor lift;
    DcMotor rotation;

    //brush servos.
    Servo br1;
    Servo br2;

    @Override
    public void runOpMode() throws InterruptedException {

        // name the hardware devices we will use to be used in the config file
        motorLeft = hardwareMap.dcMotor.get("Left_Drive");
        motorRight = hardwareMap.dcMotor.get("Right_Drive");
        lift = hardwareMap.dcMotor.get("lift");
        rotation = hardwareMap.dcMotor.get("rotation");
        br1 = hardwareMap.servo.get("br1");
        br2 = hardwareMap.servo.get("br2");

        // reverse left motor so right and left go the same way.
        motorRight.setDirection(DcMotor.Direction.REVERSE);


        //waits for the start button to be pushed.
        waitForStart();


        // goes fowared for a sort blast.
        motorRight.setPower(-.7);
        motorLeft.setPower(-.7);
        sleep(700);
        motorRight.setPower(0);
        motorLeft.setPower(0);
        sleep(50);

        // turns to align with the shelter.
        motorRight.setPower(.6);
        motorLeft.setPower(-.6);
        sleep(400);
        motorRight.setPower(-0);
        motorLeft.setPower(-0);
        sleep(50);

        //goes to shelter.
        motorRight.setPower(-.9 );
        motorLeft.setPower(-1);
        sleep(2800);
        motorRight.setPower(0);
        motorLeft.setPower(0);
        sleep(50);

        // turns to face shelter.
        motorRight.setPower(.6);
        motorLeft.setPower(-.6);
        sleep(300);
        motorRight.setPower(0);
        motorLeft.setPower(0);
        sleep(50);

        // goes fully into square.
        motorRight.setPower(0.5);
        motorLeft.setPower(-0.5);
        sleep(500);
        motorRight.setPower(0);
        motorLeft.setPower(0);
    }
}
