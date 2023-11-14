package org.firstinspires.ftc.teamcode.RoadRunner;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@Disabled
public class Functions extends OpMode {
    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }

    public WebcamName getWebcamName (String webcam_name){
        return hardwareMap.get(WebcamName.class, webcam_name);
    }
    public AprilTagProcessor createAprilTagProcessor() {
        return AprilTagProcessor.easyCreateWithDefaults();
    }
    public VisionPortal createVisionPortal (WebcamName webcamName, AprilTagProcessor processor){
        return VisionPortal.easyCreateWithDefaults(webcamName, processor);
    }
    public VisionPortal createAprilTagDetector(String webcam_name){
        WebcamName webcamName = getWebcamName(webcam_name);
        AprilTagProcessor atp = createAprilTagProcessor();
        VisionPortal vp = createVisionPortal(webcamName,atp);
        return vp;
    }
}
