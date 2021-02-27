package org.firstinspires.ftc.teamcode;


import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class OpencvNavigationtargetdtector extends OpenCvPipeline {

   Mat mat = new Mat();

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        
    }
}
