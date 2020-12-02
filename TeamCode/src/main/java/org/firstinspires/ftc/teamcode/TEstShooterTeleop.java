package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "test shooter teleop")
public class TEstShooterTeleop extends LinearOpMode {


            TestingHradware robot = new TestingHradware();
            private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()){



            if (gamepad1.x){
                robot.Shooter.setPower(0.8);
            } else {
                robot.Shooter.setPower(0);
            }


            if (gamepad1.a){
                robot.loader.setPower(.8);
            } else {
                robot.loader.setPower(0);
            }
        }

    }
}
