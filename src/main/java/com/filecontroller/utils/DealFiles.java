package com.filecontroller.utils;

import java.io.File;
import java.util.Base64;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DealFiles {
    /**
     * 获取视频指定帧的Base64编码
     *
     * @param videoPath   视频文件路径
     * @param frameNumber 帧序号（从0开始）
     * @return Base64编码的图片
     */
    public static String getFrameAsBase64(String videoPath, int frameNumber) throws IOException {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath);
        grabber.start();

        try {
            // 跳转到指定帧
            grabber.setFrameNumber(frameNumber);

            Frame frame = grabber.grabImage();
            if (frame == null) {
                throw new IOException("无法获取指定帧");
            }

            // 转换为BufferedImage
            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage image = converter.getBufferedImage(frame);

            // 转换为Base64编码的PNG
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageBytes = baos.toByteArray();

            return "data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytes);
        } finally {
            grabber.close();
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
            if ( hours > 0 ) {
                return String.format("%02d:%02d:%02d", hours, minutes, secs);
            } else {
                return String.format("%02d:%02d", minutes, secs);
            }

        } finally {
            grabber.stop();
        }
    }

    /**
     * 获取上传时间(timestamp格式)
     */
    public static long getUploadTimestamp(String videoPath) throws IOException {
        Path path = Path.of(videoPath);
        BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
        return attrs.creationTime().toMillis();
    }

    /**
     * 获取文件or文件夹大小
     */
    public static long getFileOrFolderSize(String filepath) throws Exception {
        Path path = Paths.get(filepath);

        if (Files.isRegularFile(path)) {
            return Files.size(path);
        }

        return Files.walk(path)
                .filter(p -> p.toFile().isFile())
                .mapToLong(p -> {
                    try {
                        return Files.size(p);
                    } catch (Exception e) {
                        return 0L;
                    }
                })
                .sum();
    }


    /**
     * 获取图片宽和高
     */
    public static Map<String, Integer> getImgWAH(String imagePath) throws IOException {
        Map<String, Integer> res = new HashMap<>();
        File file = new File(imagePath);
        ImageInputStream iis = ImageIO.createImageInputStream(file);

        Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
        if (readers.hasNext()) {
            ImageReader reader = readers.next();
            reader.setInput(iis);

            int width = reader.getWidth(0);
            int height = reader.getHeight(0);

            System.out.println("图片宽度: " + width + " 像素");
            System.out.println("图片高度: " + height + " 像素");
            res.put("width", width);
            res.put("height", height);
            reader.dispose();
            iis.close();
        } else {
            res.put("width", 0);
            res.put("height", 0);
        }
        return res;
    }

    /**
     * 获取视频时长(hh:mm:ss格式)
     */
    public static Boolean createFolder (String path)  {
        File file = new File(path);
        if (!file.exists()){
            return file.mkdirs();
        }
        return false;
    }
}