package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class hardware {

    public DcMotor Right_Bottom;
    public DcMotor Right_Top;
    public DcMotor Left_Bottom;
    public DcMotor Left_Top;
    public DcMotor Shooter;
    public DcMotor loader;
    public Servo Tilty;
    public Servo Tilty2;
    public DcMotor Finger;
    public DistanceSensor Lookie;
    public Servo Claw;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();
    public hardware() {

    }
    public void init(HardwareMap ahwMap){
        hwMap = ahwMap;
        Right_Bottom = hwMap.get(DcMotor.class, "Right_Bottom");
        Right_Top = hwMap.get(DcMotor.class, "Right_Top");
        Left_Bottom = hwMap.get(DcMotor.class, "Left_Bottom");
        Left_Top = hwMap.get(DcMotor.class, "Left_Top");
        Shooter = hwMap.get(DcMotor.class,"Shooter");
        loader = hwMap.get(DcMotor.class,"Loader");
        Tilty = hwMap.get(Servo.class,"Tilty");
        Tilty2 = hwMap.get(Servo.class,"Tilty2");
        Finger = hwMap.get(DcMotor.class,"Finger");
        Lookie = hwMap.get(DistanceSensor.class,"Lookie");
        Claw = hwMap.get(Servo.class,"Claw");


        Shooter.setPower(0);
        loader.setPower(0);
        Finger.setPower(0);

        loader.setDirection(DcMotorSimple.Direction.REVERSE);
        Right_Top.setDirection(DcMotorSimple.Direction.REVERSE);

        Right_Bottom.setDirection(DcMotorSimple.Direction.REVERSE);


        //Shooter.setDirection(DcMotorSimple.Direction.REVERSE);




    }
}

