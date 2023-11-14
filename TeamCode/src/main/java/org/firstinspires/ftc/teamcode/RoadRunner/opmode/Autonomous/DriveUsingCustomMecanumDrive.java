package org.firstinspires.ftc.teamcode.RoadRunner.opmode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.RoadRunner.CustomMecanumDrive;
import org.firstinspires.ftc.teamcode.RoadRunner.SampleMecanumDrive;

import java.util.Arrays;
import java.util.List;

public class DriveUsingCustomMecanumDrive extends OpMode {
    public static final DcMotorSimple.Direction FORWARD = DcMotorSimple.Direction.FORWARD;
    public static final DcMotorSimple.Direction REVERSE = DcMotorSimple.Direction.REVERSE;
    List<String> motor_configs;
    List<DcMotorSimple.Direction> motor_directions;
    CustomMecanumDrive customMecanumDrive;
    @Override
    public void init() {
        motor_configs = Arrays.asList("fl","fr","bl","br");
        //motor_directions = Arrays.asList(REVERSE,FORWARD,REVERSE,FORWARD);
        motor_directions = Arrays.asList(FORWARD,FORWARD,FORWARD,FORWARD);
        customMecanumDrive = new CustomMecanumDrive(hardwareMap, motor_configs, motor_directions);
    }

    @Override
    public void loop() {
        drive();
    }
    public void drive(){
        CustomMecanumDrive drive = new CustomMecanumDrive(hardwareMap, motor_configs, motor_directions);
        drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drive.setWeightedDrivePower(
                new Pose2d(
                        -gamepad1.left_stick_y,
                        gamepad1.left_stick_x,
                        -gamepad1.right_stick_x
                )
        );
    }
}
