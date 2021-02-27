package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;

@TeleOp(name = "SErvoTester",group = "tests")
public class Servotesttleop extends LinearOpMode {

    hardware robot = new hardware();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);
        double position = 0;
        waitForStart();
        while (opModeIsActive()){


            if (gamepad1.right_trigger == 1) {
                robot.Claw.setPosition(0.1);
                telemetry.addData("position:", "0.1");
                telemetry.update();
            }

            if (gamepad1.left_trigger == 1) {
                robot.Claw.setPosition(0.2);
                telemetry.addData("position:", "0.2");
                telemetry.update();
            }
            if (gamepad1.left_bumper) {
                robot.Claw.setPosition(0.3);
                telemetry.addData("position:", "0.3");
                telemetry.update();
            }
            if (gamepad1.right_bumper) {
                robot.Claw.setPosition(0.4);
                telemetry.addData("position:", "0.4");
                telemetry.update();
            }
            if (gamepad1.b) {
                robot.Claw.setPosition(0.5);
                telemetry.addData("position:", "0.5");
                telemetry.update();
            }
            if (gamepad1.a) {
                robot.Claw.setPosition(0.6);
                telemetry.addData("position:", "0.6");
                telemetry.update();
            }
            if (gamepad1.x) {
                robot.Claw.setPosition(0.7);
                telemetry.addData("position:", "0.7");
                telemetry.update();
            }
            if (gamepad1.y) {
                robot.Claw.setPosition(0.8);
                telemetry.addData("position:", "0.8");
                telemetry.update();
            }
            if (gamepad1.left_stick_button) {
                robot.Claw.setPosition(0.9);
                telemetry.addData("position:", "0.9");
                telemetry.update();
            }


        }
    }
}
