package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class EliServoThingy extends OpMode {
    private Servo leftServo;
    private Servo rightServo;
    private int leftpos;
    private int rightpos;
    private boolean pressed = false;
    @Override
    public void init() {
        leftServo = hardwareMap.get(Servo.class,"leftServo");
        rightServo = hardwareMap.get(Servo.class,"rightServo");
        leftpos = -1;
        rightpos = 1;
        leftServo.setPosition(leftpos);
        rightServo.setPosition(rightpos);
        pressed = false;
    }

    @Override
    public void loop() {
        if(gamepad1.x){
            if(pressed == false) {
                pressed = true;
                if (leftpos == -1) {
                    leftpos = 0;
                } else if (leftpos == 0) {
                    leftpos = -1;
                }
            }
        }
        if(gamepad1.b){
            if(pressed == false) {
                pressed = true;
                if (rightpos == 1) {
                    rightpos = 0;
                } else if (rightpos == 0) {
                    rightpos = 1;
                }
            }
        }
        leftServo.setPosition(leftpos);
        rightServo.setPosition(rightpos);
    }
}
