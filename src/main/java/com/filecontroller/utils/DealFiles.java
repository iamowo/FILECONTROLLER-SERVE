package com.filecontroller.utils;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class DealFiles {
    /**
     * 获取视频第一帧的Base64编码
     */
    public static String getFirstFrameAsBase64(String videoPath) throws Exception {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath);
        try {
            grabber.start();

            // 获取第一帧
            Frame frame = grabber.grabImage();
            if (frame == null) {
                throw new IOException("无法获取视频帧");
            }

            // 转换为BufferedImage
            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage image = converter.getBufferedImage(frame);

            // 转换为Base64
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            byte[] imageBytes = baos.toByteArray();

            return Base64.encodeBase64String(imageBytes);
        } finally {
            grabber.stop();
        }
    }
    /**
     * 获取视频时长(hh:mm:ss格式)
     */
    public static String getVideoDuration(String videoPath) throws Exception {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath);
        try {
            grabber.start();

            // 获取时长(微秒)
            long duration = grabber.getLengthInTime();

            // 转换为秒
            double seconds = (double) duration / 1_000_000; // 因为FFmpeg返回的时间单位是微秒

            // 格式化为hh:mm:ss
            int hours = (int) (seconds / 3600);
            int minutes = (int) ((seconds % 3600) / 60);
            int secs = (int) (seconds % 60);

            return String.format("%02d:%02d:%02d", hours, minutes, secs);
        } finally {
            grabber.stop();
        }
    }

    /**
     * 获取上传时间(timestamp格式)
     */
    public static long getUploadTimestamp(String videoPath) throws IOException {
        Path path = Paths.get(videoPath);
        BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
        return attrs.creationTime().toMillis();
    }
}
