package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class TeleOpComp extends OpMode {

    // 7:26am 1/28/16 Whitefield Robotics Programming Team. #4444 The RoboCats!

    //drive motors.
    DcMotor motorRight;
    DcMotor motorLeft;

    //lift mechanism motors.
    DcMotor lift;
    DcMotor rotation;

    //brush servos.
    Servo zipRight;
    Servo zipLeft;

    public TeleOpComp() {
    }

    @Override
    public void init() {

        // set the config file to the text in green.
        motorLeft = hardwareMap.dcMotor.get("Right_Drive");
        motorRight = hardwareMap.dcMotor.get("Left_Drive");
        lift = hardwareMap.dcMotor.get("lift");
        rotation = hardwareMap.dcMotor.get("rotation");
        zipRight = hardwareMap.servo.get("zip_right");
        zipLeft = hardwareMap.servo.get("zip_left");

    }

    @Override
    public void loop() {

        // all of this codes clips the analog stick controls to match the motor power commands.
        float left = gamepad1.left_stick_y;
        float right = gamepad1.right_stick_y;

        right = Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);

        right = (float) scaleInput(right);
        left = (float) scaleInput(left);

        motorRight.setPower(right);
        motorLeft.setPower(left);

        ////////////////////////////servo controls///////////////////////////////////////////////

        // if the dpad down button is pressed then the plate will rotate down.
        if (gamepad1.y){
            rotation.setPower(0.3);
        } else {

            // if the dpad up button is pressed then plate will rotate up.
            if (gamepad1.dpad_up) {
                rotation.setPower(0);
            }

            // if neither button is pushed then the lift plate will remain still.
            else {
                lift.setPower(0);
            }
        }

        ///////////////////////////////zipline controls///////////////////////////////////////////////

        // if the dpad left button is pushed then the brush will start to rotate in.
        if (gamepad1.b){

            zipRight.setPosition(1.0);
        }
        else{
            //if the dpad right button is pushed then the brush will start to rotate out.
            if (gamepad1.dpad_right){
                zipRight.setPosition(0.5);
            }
            else{

                //if neither button is pushed then the servos will remain at rest.
                zipRight.setPosition(0.5);
            }

        }

        if(gamepad1.x){
            zipLeft.setPosition(1.0);
        }
       else{
            if(gamepad1.dpad_right){
                zipLeft.setPosition(0.5);
            }
            else{
                zipLeft.setPosition(0.5);
            }
        }

        //////////////////////////////lift controls/////////////////////////////////////////////////

        // if the right bumper is pushed then the lift mech will retract down.
        if (gamepad1.left_bumper) {
            lift.setPower(-1);
        }

        else{

            //if the left bumper was pushed then the lift mech will ascend up.
            if (gamepad1.right_bumper) {
                lift.setPower(1);
            }

            // if neither button is pushed then the lift mech will remain still.
            else{
                lift.setPower(0);
            }
        }

        ////////////////////////////////////////////////////////////////////////////////////////////

        //this code will send data to the phones
        telemetry.addData("Text", "Team#4444 ROBOCATS!!!");
        telemetry.addData("left tgt pwr", "left  pwr: " + String.format("%.2f", left));
        telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", right));
    }

    @Override
    public void stop() {
    }

    // more code to clip the gamepads analog stick outputs
    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };
        int index = (int) (dVal * 16.0);

        if (index < 0) {
            index = -index;
        }
        if (index > 16) {
            index = 16;
        }
        double dScale = 0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }
        return dScale;
    }
}

