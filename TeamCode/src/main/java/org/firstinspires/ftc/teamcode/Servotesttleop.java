package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "SErvoTester",group = "tests")
public class Servotesttleop extends LinearOpMode {
    TestingHradware robot = new TestingHradware();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);
        double position = 0;
        waitForStart();
        while (opModeIsActive()){

            if (gamepad1.a); {
                robot.Tilty.setPosition(position);
                telemetry.addData("position of tilty:",position);
                telemetry.update();
            }
            if (gamepad1.dpad_up);{
                position =+ 0.1;
            }
            if (gamepad1.dpad_down); {
                position =- 0.1;
            }


        }
    }
}
