package org.firstinspires.ftc.teamcode.RoadRunner.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.RoadRunner.opmode.MoveUsingDistance;
@Autonomous
public class Autonomous01 extends OpMode{
    MoveUsingDistance moveUsingDistance = new MoveUsingDistance();

    @Override
    public void init() {
        moveUsingDistance.moveForward(2, hardwareMap);
    }

    @Override
    public void loop() {

    }
}

