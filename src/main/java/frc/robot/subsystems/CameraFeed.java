package frc.robot.subsystems;

import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CameraFeed extends SubsystemBase {
    private final VideoSource cameraVideo;

    public CameraFeed(String feedName) {
        // Locate front camera
        cameraVideo = CameraServer.getInstance().getServer(feedName).getSource();
    }

    public CameraFeed(String feedName, ShuffleboardLayout layout) {
        this(feedName);
        layout.add(feedName, cameraVideo);
        // TODO: Add layout for camera controls
    }

    public VideoSource getSource() {
        return cameraVideo;
    }

    // TODO: Add properties for manipulating video feed
}