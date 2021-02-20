package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.vars.COUNTS_PER_INCH;


@TeleOp(name = "Offical teleop ")

public class TeleopFrodo extends LinearOpMode {

    double position;

        hardware robot = new hardware();
        private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);




        waitForStart();

        while (opModeIsActive()){
            robot.Right_Top.setPower(gamepad1.right_stick_y);
            robot.Right_Bottom.setPower(gamepad1.right_stick_y);
            robot.Left_Top.setPower(gamepad1.left_stick_y);
            robot.Left_Bottom.setPower(gamepad1.left_stick_y);





            if (gamepad1.x){
                robot.Shooter.setPower(1);

            } else if (gamepad1.b) {
                robot.Shooter.setPower(0);
            }


            if (gamepad1.y){
                robot.loader.setPower(.8);

            } else {
                robot.loader.setPower(0);

            }

            if (gamepad1.a){
                robot.loader.setPower(-.8);

            } else {
                robot.loader.setPower(0);

            }

            if (gamepad1.dpad_right){
                position += .1;
                robot.Tilty.setPosition(position);
                robot.Tilty2.setPosition(-position);
                telemetry.addData("position of tilty",position);
            }
            if (gamepad1.dpad_left){
                position -= .1;
                robot.Tilty.setPosition(position);
                robot.Tilty2.setPosition(position);
                telemetry.addData("position of tilty",position);
            }



            if (gamepad1.dpad_up){
                robot.Finger.setPower(.3);
            }
            else if (gamepad1.dpad_down){
                robot.Finger.setPower(-.3);
            } else {
                robot.Finger.setPower(0);
            }

            while (gamepad1.right_stick_x == -1 && gamepad1.right_stick_y == 1) {
                robot.Right_Bottom.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Left_Top.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Left_Top.setPower(.6);
                robot.Right_Bottom.setPower(.6);
            }
            while (gamepad1.right_stick_y == 1 && gamepad1.right_stick_x == -1) {
                robot.Right_Bottom.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Left_Top.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Left_Top.setPower(-.6);
                robot.Right_Bottom.setPower(-.6);
            }
            while (gamepad1.left_stick_x == -1 && gamepad1.right_stick_y == 1) {
                robot.Right_Top.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Left_Bottom.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Left_Bottom.setPower(.6);
                robot.Right_Top.setPower(.6);
            }
            while (gamepad1.left_stick_x == 1 && gamepad1.left_stick_y == 1) {
                robot.Right_Top.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Left_Bottom.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Left_Bottom.setPower(-.6);
                robot.Right_Top.setPower(-.6);
            }
            //This is the Strafe
            while (gamepad1.right_stick_x == 1) {
                robot.Right_Top.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Right_Bottom.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Left_Bottom.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Left_Top.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Right_Top.setPower(.6);
                robot.Right_Bottom.setPower(-.6);
                robot.Left_Bottom.setPower(.6);
                robot.Left_Top.setPower(-.6);
            }
            //This is the Strafe
            // --Speeds changed by Coach 12/13/19
            while (gamepad1.left_stick_x == -1) {
                robot.Right_Top.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Right_Bottom.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Left_Bottom.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Left_Top.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Right_Top.setPower(-.6);
                robot.Right_Bottom.setPower(.6);
                robot.Left_Bottom.setPower(-.6);
                robot.Left_Top.setPower(.6);


            }


        }

    }


    public void encoderDrive(double speed,
                             double Left_Bottom_Inches,
                             double Right_Bottom_Inches,
                             double Right_Top_Inches,
                             double Left_Top_Inches,
                             double timeoutS) {
        int newLeftBottomTarget;
        int newRightBottomTarget;
        int newRightTopTarget;
        int newLeftTopTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {


            // Determine new target position, and pass to motor controller
            newLeftBottomTarget = robot.Left_Bottom.getCurrentPosition() + (int) (Left_Bottom_Inches * COUNTS_PER_INCH);
            newRightBottomTarget = robot.Right_Bottom.getCurrentPosition() + (int) (Right_Bottom_Inches * COUNTS_PER_INCH);
            newRightTopTarget = robot.Right_Top.getCurrentPosition() + (int) (Right_Top_Inches * COUNTS_PER_INCH);
            newLeftTopTarget = robot.Left_Top.getCurrentPosition() + (int) (Left_Top_Inches * COUNTS_PER_INCH);

            robot.Left_Bottom.setTargetPosition(newLeftBottomTarget);
            robot.Right_Bottom.setTargetPosition(newRightBottomTarget);
            robot.Right_Top.setTargetPosition(newRightTopTarget);
            robot.Left_Top.setTargetPosition(newLeftTopTarget);

            // Turn On RUN_TO_POSITION
            robot.Left_Bottom.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.Right_Bottom.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.Left_Top.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.Right_Top.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.Left_Bottom.setPower(Math.abs(speed));
            robot.Right_Bottom.setPower(Math.abs(speed));
            robot.Left_Top.setPower(Math.abs(speed));
            robot.Right_Top.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.Left_Bottom.isBusy() && robot.Right_Bottom.isBusy() && robot.Left_Top.isBusy() && robot.Right_Top.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftBottomTarget, newRightBottomTarget, newLeftTopTarget, newRightTopTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        robot.Left_Bottom.getCurrentPosition(),
                        robot.Right_Bottom.getCurrentPosition());
                robot.Left_Top.getCurrentPosition();
                robot.Right_Top.getCurrentPosition();
                telemetry.update();
            }

            // Stop all motion;
            robot.Left_Bottom.setPower(0);
            robot.Right_Bottom.setPower(0);
            robot.Left_Top.setPower(0);
            robot.Right_Top.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.Left_Bottom.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.Right_Bottom.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.Left_Top.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.Right_Top.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move

        }
    }
}
