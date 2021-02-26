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


            if (gamepad1.dpad_up);{
                position = position + 0.1;
                robot.Claw.setPosition(position);
                telemetry.addData("position of claw:",position);
                telemetry.update();
            }
            if (gamepad1.dpad_down); {
                position = position - 0.1;
                robot.Claw.setPosition(position);
                telemetry.addData("position of claw:",position);
                telemetry.update();
            }


        }
    }
}
