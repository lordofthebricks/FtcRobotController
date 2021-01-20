package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.vars;
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
            //robot.Right_Top.setPower(gamepad1.right_stick_y);
            //robot.Right_Bottom.setPower(gamepad1.right_stick_y);
            //robot.Left_Top.setPower(gamepad1.left_stick_y);
            //robot.Left_Bottom.setPower(gamepad1.left_stick_y);


            if (gamepad1.x){
                robot.Shooter.setPower(1);

            } else {
                robot.Shooter.setPower(0);
            }


            if (gamepad1.y){
                robot.loader.setPower(.8);

            } else {
                robot.loader.setPower(0);

            }
            if (gamepad1.dpad_up){
                position += .1;
            }
            if (gamepad1.dpad_down){
                position -= .1;
            }


            if (gamepad1.a){
                robot.Tilty.setPosition(position);
                telemetry.addData("position of tilty",position);
            }
            while (gamepad1.right_stick_x == -1 && gamepad1.right_stick_y == 1) {

                robot.Left_Top.setPower(.6);
                robot.Right_Bottom.setPower(.6);
            }
            while (gamepad1.right_stick_y == 1 && gamepad1.right_stick_x == -1) {

                robot.Left_Top.setPower(-.6);
                robot.Right_Bottom.setPower(-.6);
            }
            while (gamepad1.left_stick_x == -1 && gamepad1.right_stick_y == 1) {

                robot.Left_Bottom.setPower(.6);
                robot.Right_Top.setPower(.6);
            }
            while (gamepad1.left_stick_x == 1 && gamepad1.left_stick_y == 1) {

                robot.Left_Bottom.setPower(-.6);
                robot.Right_Top.setPower(-.6);

            }
            
            while (gamepad1.right_stick_x == 1) {

                robot.Right_Top.setPower(.6);
                robot.Right_Bottom.setPower(-.6);
                robot.Left_Bottom.setPower(.6);
                robot.Left_Top.setPower(-.6);
            }
            //This is the Strafe
            // --Speeds changed by Coach 12/13/19
            while (gamepad1.left_stick_x == -1) {

                robot.Right_Top.setPower(-.6);
                robot.Right_Bottom.setPower(.6);
                robot.Left_Bottom.setPower(-.6);
                robot.Left_Top.setPower(.6);


            }
            while (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y == 0 && gamepad1.right_stick_x == 0 && gamepad1.right_stick_y == 0){
                robot.Left_Bottom.setPower(0);
                robot.Left_Top.setPower(0);
                robot.Right_Bottom.setPower(0);
                robot.Right_Top.setPower(0);
            }


        }

    }



}
