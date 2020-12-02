package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.vars;

@TeleOp(name = "Normal teleop")
public class teleop1 extends LinearOpMode {


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
