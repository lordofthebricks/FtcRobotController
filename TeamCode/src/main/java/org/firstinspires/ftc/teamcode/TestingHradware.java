package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class TestingHradware {
    public DcMotor Shooter;
    public DcMotor loader;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();
    public TestingHradware() {

    }
    public void init(HardwareMap ahwMap){
        hwMap = ahwMap;
        Shooter = hwMap.get(DcMotor.class,"Shooter");
        loader = hwMap.get(DcMotor.class,"Loader");


        Shooter.setPower(0);
        loader.setPower(0);

        loader.setDirection(DcMotorSimple.Direction.REVERSE);
        Shooter.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
