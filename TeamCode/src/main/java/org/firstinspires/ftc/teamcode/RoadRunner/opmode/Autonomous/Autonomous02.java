package org.firstinspires.ftc.teamcode.RoadRunner.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.RoadRunner.opmode.MoveUsingDistance;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.List;
@Autonomous
public class Autonomous02 extends OpMode {
    MoveUsingDistance moveUsingDistance = new MoveUsingDistance();
    private AprilTagProcessor aprilTagProcessor;
    private VisionPortal visionPortal;
    public WebcamName webcamName;
    public StringBuilder idsFound;
    List<AprilTagDetection> currentDetections;

    @Override
    public void init() {
        moveUsingDistance.moveForward(2, hardwareMap);
    }

    @Override
    public void init_loop() {
        currentDetections = aprilTagProcessor.getDetections();
    }

    @Override
    public void loop() {

    }
}
