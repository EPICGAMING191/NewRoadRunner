package org.firstinspires.ftc.teamcode.RoadRunner.opmode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RoadRunner.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.RoadRunner.opmode.MoveUsingDistance;

@TeleOp
public class MoveAtIntervalsTeleOpVersion extends OpMode{
    public enum Mode {
        AUTONOMOUS_MODE,
        DRIVER_MODE
    }
    public Mode currentMode;
    private ElapsedTime main;
    private double lastMovedSecond = 0;
    private double movementInterval = 3;
    MoveUsingDistance moveUsingDistance = new MoveUsingDistance();

    @Override
    public void init() {
        main = new ElapsedTime();
        currentMode = Mode.AUTONOMOUS_MODE;
    }

    @Override
    public void loop() {
        switch (currentMode) {
            case AUTONOMOUS_MODE:
                if ((main.seconds() - lastMovedSecond) >= movementInterval) {
                    moveUsingDistance.moveForward(2, hardwareMap);
                    lastMovedSecond = main.seconds();
                }
                if(gamepad1.x){
                    currentMode = Mode.DRIVER_MODE;
                }
                break;
            case DRIVER_MODE:
                drive();
                if(gamepad1.x){
                    currentMode = Mode.AUTONOMOUS_MODE;
                    main.reset();
                }
        }
    }
    public void drive(){
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drive.setWeightedDrivePower(
                new Pose2d(
                        -gamepad1.left_stick_y,
                        -gamepad1.left_stick_x,
                        -gamepad1.right_stick_x
                )
        );
    }
}

