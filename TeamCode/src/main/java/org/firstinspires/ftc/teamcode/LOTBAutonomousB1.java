package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;


@Autonomous
public class LOTBAutonomousB1 extends LinearOpMode {

    private static final long SLEEP_10 = 10;
    private static final long SLEEP_25 = 25;
    private static final long SLEEP_50 = 50;


    OpenCvInternalCamera phoneCam;
    SkystoneDeterminationPipeline pipeline;
    hardware robot = new hardware();
    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 537.6;//356.3 ;    // eg: DC Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 3.77953 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            //first hundred digits of pi fr more accuracy
            (WHEEL_DIAMETER_INCHES * 3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679);

    /**
     * Override this method and place your code here.
     * <p>
     * Please do not swallow the InterruptedException, as it is used in cases
     * where the op mode needs to be terminated early.
     *
     * @throws InterruptedException
     */
    @Override

    public void runOpMode() throws InterruptedException {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.FRONT, cameraMonitorViewId);
        pipeline = new SkystoneDeterminationPipeline();
        phoneCam.setPipeline(pipeline);


        // We set the viewport policy to optimized view so the preview doesn't appear 90 deg
        // out when the RC activity is in portrait. We do our actual image processing assuming
        // landscape orientation, though.
        phoneCam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

        phoneCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                phoneCam.startStreaming(320,240, OpenCvCameraRotation.SIDEWAYS_LEFT);
            }
        });

        robot.init(hardwareMap);
        waitForStart();

        double FORWARD_SPEED = 0.5;



        while (opModeIsActive())
        {


            /*telemetry.addData("Analysis", pipeline.getAnalysis());
            telemetry.addData("Position", pipeline.position);
            telemetry.update();

            // Step 1:  Drive forward for 3 seconds
            robot.Left_Bottom.setPower(FORWARD_SPEED);
            robot.Right_Bottom.setPower(-FORWARD_SPEED);
            robot.Right_Top.setPower(-FORWARD_SPEED);
            robot.Left_Top.setPower(FORWARD_SPEED);
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 3.0)) {
                telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
                telemetry.update();
            }



            stop();

             */



            moveTowardRings();
            sleep(2000);
            int ringCount = scanRings();



            double moveLength = 29;
            sleep(2000);

            switch(ringCount)
            {
                case 0:
                    strafeLeft(15);
                    sleep(500);
                    moveTowards(moveLength);
                    break;
                case 1:
                    moveLength = 53;
                    moveTowards(moveLength);
                    sleep(500);
                    strafeRight(17);
                    break;
                default:
                    moveLength = 75;
                    strafeLeft(15);
                    sleep(500);
                    moveTowards(moveLength);
                    break;

            }
            sleep(1000);

            dropTheGoalie();
            sleep(1000);
            driveBackToShootingLine(moveLength, ringCount);
            //and if possible..
            shootRings();
            stop();
        }
    }

    private void strafeRight(double moveLength) {
        telemetry.addData("Strafe Right: ", moveLength);
        telemetry.update();
        encoderDriveWithoutTime(0.3, moveLength, -moveLength, moveLength, -moveLength);
    }

    private void strafeLeft(double moveLength) {
        telemetry.addData("Strafe Left: ", moveLength);
        telemetry.update();
        encoderDriveWithoutTime(0.3, -moveLength, moveLength, -moveLength, moveLength);
    }

    private void moveTowards(double moveLength) {

        telemetry.addData("Move Length: ", moveLength);
        telemetry.update();
        encoderDriveWithoutTime(0.5, -moveLength, -moveLength, -moveLength, -moveLength);

    }

    public void encoderDriveWithoutTime ( double speed,
                                          double Left_Bottom_Inches,
                                          double Right_Bottom_Inches,
                                          double Right_Top_Inches,
                                          double Left_Top_Inches
    ){
        int newLeftBottomTarget;
        int newRightBottomTarget;
        int newRightTopTarget;
        int newLeftTopTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {


            // Determine new target position, and pass to motor controller
            newLeftBottomTarget = robot.Left_Bottom.getCurrentPosition() + (int) ((Left_Bottom_Inches) * COUNTS_PER_INCH);
            newRightBottomTarget = robot.Right_Bottom.getCurrentPosition() + (int) ((Right_Bottom_Inches) * COUNTS_PER_INCH);
            newRightTopTarget = robot.Right_Top.getCurrentPosition() + (int) ((Right_Top_Inches) * COUNTS_PER_INCH);
            newLeftTopTarget = robot.Left_Top.getCurrentPosition() + (int) ((Left_Top_Inches) * COUNTS_PER_INCH);

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
                    (robot.Left_Bottom.isBusy() && robot.Right_Bottom.isBusy() && robot.Left_Top.isBusy() && robot.Right_Top.isBusy())) {

                // Display it for the driver.
                //telemetry.addData("Path1", "Running to %7d :%7d", newLeftBottomTarget, newRightBottomTarget, newLeftTopTarget, newRightTopTarget);
                //telemetry.addData("Path2", "Running at %7d :%7d", robot.Left_Bottom.getCurrentPosition(), robot.Right_Bottom.getCurrentPosition());
                robot.Left_Top.getCurrentPosition();
                robot.Right_Top.getCurrentPosition();
                //telemetry.update();
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

    private void shootRings() {

        //If there is time, shoot the rings; Might have to mix the order of the methods a bit to make it work.
    }

    private void driveBackToShootingLine(double moveLength, int ringCount) {

        //Drive back to the shooting line (Around the middle of the field;
        //Also, create If statement to see how far to move back relative to where you are (A box, B box, C box) )
        switch (ringCount){
            case 0:
                break;
            case 1:
                telemetry.addData("Move Back 25 in", "");
                telemetry.update();
                encoderDriveWithoutTime(0.5, 20, 20, 20, 20);
                break;
            default:
                telemetry.addData("Move Back 53 in", "");
                telemetry.update();
                encoderDriveWithoutTime(0.5, 45, 45, 45, 45);
                break;
        }
    }

    private void dropTheGoalie() {

        //Drop the wobbly goal or (if pushing the goal) just do nothing
    }

    private void moveTowardsC(double moveLength) {

        //Move towards the C box
        encoderDriveWithoutTime(0.5, -85, -85, -85, -85);
    }

    private void moveTowardsB(double moveLength) {

        //Move towards the B box
        encoderDriveWithoutTime(0.5, -57, -57, -57, -57);
    }

    private void moveTowardsA(double moveLength) {

        //Move towards the A box
        encoderDriveWithoutTime(.5, -33, -33, -33, -33);
    }

    private int scanRings() {

        sleep(2000);
        int avg1 = pipeline.getAnalysis();

        final int FOUR_RING_THRESHOLD = 167;//150;
        final int ONE_RING_THRESHOLD = 150;//135;

        int ringCount = 0;

        if(avg1 > FOUR_RING_THRESHOLD){
            ringCount = 4;
        }else if (avg1 > ONE_RING_THRESHOLD){
            ringCount = 1;
        }

        telemetry.addData("Postition", avg1);
        telemetry.addData("Ring Count:", ringCount);
        telemetry.update();
        sleep(2000);

        /*while (opModeIsActive())
        {
            telemetry.addData("Analysis", pipeline.getAnalysis());
            telemetry.addData("Position", pipeline.position);
            telemetry.update();

            // Don't burn CPU cycles busy-looping in this sample
            sleep(50);
        } */

        //Copy the code from the opencv example and make it return 3 ints; 0, 1, 4
        return ringCount;


    }

    private void moveTowardRings() {

        //encoderDriveWithoutTime for around 30 inches
        encoderDriveWithoutTime(0.5, -34, -34, -34, -34);
    }


    public static class SkystoneDeterminationPipeline extends OpenCvPipeline
    {
        /*
         * An enum to define the skystone position
         */
        public enum RingPosition
        {
            FOUR,
            ONE,
            NONE
        }

        /*
         * Some color constants
         */
        static final Scalar BLUE = new Scalar(0, 0, 255);
        static final Scalar GREEN = new Scalar(0, 255, 0);

        /*
         * The core values which define the location and size of the sample regions
         */
        static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(75,68);

        static final int REGION_WIDTH = 45;
        static final int REGION_HEIGHT = 33;

        final int FOUR_RING_THRESHOLD = 170;//150;
        final int ONE_RING_THRESHOLD = 150;//135;

        Point region1_pointA = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x,
                REGION1_TOPLEFT_ANCHOR_POINT.y);
        Point region1_pointB = new Point(
                REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
                REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

        /*
         * Working variables
         */
        Mat region1_Cb;
        Mat YCrCb = new Mat();
        Mat Cb = new Mat();
        int avg1;

        // Volatile since accessed by OpMode thread w/o synchronization
        private volatile EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition position = EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition.FOUR;

        /*
         * This function takes the RGB frame, converts to YCrCb,
         * and extracts the Cb channel to the 'Cb' variable
         */
        void inputToCb(Mat input)
        {
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Cb, 1);
        }

        @Override
        public void init(Mat firstFrame)
        {
            inputToCb(firstFrame);

            region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
        }

        @Override
        public Mat processFrame(Mat input)
        {
            inputToCb(input);

            avg1 = (int) Core.mean(region1_Cb).val[0];

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    BLUE, // The color the rectangle is drawn in
                    2); // Thickness of the rectangle lines

            position = EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition.FOUR; // Record our analysis
            if(avg1 > FOUR_RING_THRESHOLD){
                position = EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition.FOUR;
            }else if (avg1 > ONE_RING_THRESHOLD){
                position = EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition.ONE;
            }else{
                position = EasyOpenCVExample.SkystoneDeterminationPipeline.RingPosition.NONE;
            }

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    GREEN, // The color the rectangle is drawn in
                    -1); // Negative thickness means solid fill

            return input;
        }

        public int getAnalysis()
        {
            return avg1;
        }



    }

}
