package org.firstinspires.ftc.teamcode.RoadRunner.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RoadRunner.Functions;
import org.firstinspires.ftc.teamcode.RoadRunner.opmode.MoveUsingDistance;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.List;
@Autonomous
public class Autonomous02 extends OpMode {
    private int listSize = 0;
    MoveUsingDistance moveUsingDistance = new MoveUsingDistance();
    public static final DcMotorSimple.Direction FORWARD = DcMotorSimple.Direction.FORWARD;
    public static final DcMotorSimple.Direction REVERSE = DcMotorSimple.Direction.REVERSE;
    private AprilTagProcessor aprilTagProcessor;
    private VisionPortal visionPortal;
    public WebcamName webcamName;
    public StringBuilder idsFound;
    List<AprilTagDetection> currentDetections;
    private int latestID;
    private Functions functions = new Functions();
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    enum AprilTags {
        BLUE_TEAM_LEFT,
        BLUE_TEAM_CENTER,
        BLUE_TEAM_RIGHT,
        RED_TEAM_LEFT,
        RED_TEAM_CENTER,
        RED_TEAM_RIGHT,
        NONE
    }
    private AprilTags latestTag;
    @Override
    public void init() {
        latestTag = AprilTags.NONE;
        webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        aprilTagProcessor = functions.createAprilTagProcessor();
        visionPortal = functions.createVisionPortal(webcamName,aprilTagProcessor);
        frontLeft = hardwareMap.get(DcMotor.class,"fl");
        frontRight = hardwareMap.get(DcMotor.class,"fr");
        backLeft = hardwareMap.get(DcMotor.class,"bl");
        backRight = hardwareMap.get(DcMotor.class,"br");
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //frontLeft.setDirection(REVERSE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //frontRight.setDirection(FORWARD);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //backLeft.setDirection(REVERSE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //backRight.setDirection(FORWARD);
        moveUsingDistance.moveForward(6, hardwareMap);
    }

    @Override
    public void init_loop() {
        currentDetections = aprilTagProcessor.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            telemetry.addLine("ID FOUND: " + detection.id);
            int id = detection.id;
            if (id != latestID) {
                latestID = id;
                latestTag = getTagFromID(id);
                if (id == 1) {
                    double xOffset = detection.ftcPose.x;
                    telemetry.addLine("X Offset: " + detection.ftcPose.x);
                    while (xOffset < 3 || xOffset >= 4) {
                        if (xOffset >= 4) {
                            moveUsingDistance.moveForward(0.25, hardwareMap);
                        } else {
                            moveUsingDistance.moveBackward(0.25, hardwareMap);
                        }
                    }
                }
                else {
                    telemetry.addLine("X Offset: " + detection.ftcPose.x);
                }
            }
        }
            telemetry.addLine(String.valueOf(latestTag));
    }

    @Override
    public void start(){
        visionPortal.stopStreaming();
    }
    @Override
    public void loop() {
        double y = -gamepad1.left_stick_y; // Remember, this is reversed!
        double x = gamepad1.left_stick_x; // Counteract imperfect strafing
        double rx = -gamepad1.right_stick_x; //This is reversed for our turning
        drive(y, x, rx);
    }
    public void drive(double y, double x, double rx) {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (-y + x + rx) / denominator;
        double backLeftPower = (-y - x + rx) / denominator;
        double frontRightPower = (y + x + rx) / denominator;
        double backRightPower = (y - x + rx) / denominator;

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);
    }
    public AprilTags getTagFromID(int id) {
        AprilTags tag_name;
        switch (id){
            case 1:
                tag_name = AprilTags.BLUE_TEAM_LEFT;
            case 2:
                tag_name = AprilTags.BLUE_TEAM_CENTER;
            case 3:
                tag_name = AprilTags.BLUE_TEAM_RIGHT;
            case 4:
                tag_name = AprilTags.RED_TEAM_LEFT;
            case 5:
                tag_name = AprilTags.RED_TEAM_CENTER;
            case 6:
                tag_name = AprilTags.RED_TEAM_RIGHT;
            default:
                tag_name = AprilTags.NONE;
        }
        return tag_name;
    }
}
