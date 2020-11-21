package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name = "GimliTeleopDriverControl")
public class teleop1 extends LinearOpMode {
    static final double     COUNTS_PER_MOTOR_REV    = 1425.2;//356.3 ;    // eg: DC Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 3.75 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            //first hundred digits of pi fr more accuracy


            (WHEEL_DIAMETER_INCHES * 3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679);


            hardware robot = new hardware();
            private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        while (opModeIsActive()){
            robot.Right_Top.setPower(gamepad1.right_stick_y);
            robot.Right_Bottom.setPower(gamepad1.right_stick_y);
            robot.Left_Top.setPower(gamepad1.left_stick_y);
            robot.Left_Bottom.setPower(gamepad1.left_stick_y);


            while (gamepad1.x){
                robot.Shooter.setPower(0.5);
            }


            while (gamepad1.a){
                robot.loader.setPower(.8);
            }
        }

    }
}
