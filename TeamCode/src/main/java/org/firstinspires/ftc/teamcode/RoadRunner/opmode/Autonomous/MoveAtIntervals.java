package org.firstinspires.ftc.teamcode.RoadRunner.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RoadRunner.opmode.MoveUsingDistance;

@Autonomous
public class MoveAtIntervals extends OpMode{
    private ElapsedTime main;
    private double lastMovedSecond = 0;
    private double movementInterval = 3;
    MoveUsingDistance moveUsingDistance = new MoveUsingDistance();

    @Override
    public void init() {
        main = new ElapsedTime();
    }
    @Override
    public void init_loop(){
        if((main.seconds() -  lastMovedSecond) >= movementInterval) {
            moveUsingDistance.moveForward(2, hardwareMap);
            lastMovedSecond = main.seconds();
        }
    }

    @Override
    public void loop() {

    }
}

