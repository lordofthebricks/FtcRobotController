package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.CRServo;

public class hardware {

    public DcMotor Right_Bottom;
    public DcMotor Right_Top;
    public DcMotor Left_Bottom;
    public DcMotor Left_Top;
    public DcMotor Shooter;
    public DcMotor loader;
    public CRServo Assister;
    //public CRServo AssisterTwo;
    public DcMotor Finger;
    public Servo Mover;

    //public DistanceSensor Lookie;
    public Servo Claw;
    public Servo mover;


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
        Finger = hwMap.get(DcMotor.class,"Finger");
        Mover =  hwMap.get(Servo.class,"Mover");

        //Lookie = hwMap.get(DistanceSensor.class,"Lookie");
        Claw = hwMap.get(Servo.class,"Claw");
        Assister = hwMap.get(CRServo.class,"Assister");
        //AssisterTwo = hwMap.get(CRServo.class,"AssisterTwo");
        




        Shooter.setPower(0);
        loader.setPower(0);
        Finger.setPower(0);

        loader.setDirection(DcMotorSimple.Direction.REVERSE);
        Right_Top.setDirection(DcMotorSimple.Direction.REVERSE);

        Right_Bottom.setDirection(DcMotorSimple.Direction.REVERSE);


        //Shooter.setDirection(DcMotorSimple.Direction.REVERSE);




    }
}