package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class ConverterMy implements TextGraphicsConverter {

    private double maxRatio;
    private int maxWidth;
    private int maxHeight;
    private TextColorSchema schema = new TextColorSchemaMy();

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {

        BufferedImage img = ImageIO.read(new URL(url));

        int width = img.getWidth();
        int height = img.getHeight();
        int maxHeight = getMaxHeight();
        int maxWidth = getMaxWidth();
        int newWidth;
        int newHeight;

        if (width > maxWidth && height > maxHeight) {
            int aspectRationWidthToMaxWidth = width / maxWidth;
            int aspectRationHeightToMaxHeight = height / maxHeight;
            if (aspectRationWidthToMaxWidth > aspectRationHeightToMaxHeight) {
                newWidth = width / aspectRationWidthToMaxWidth;
                newHeight = height / aspectRationWidthToMaxWidth;
            } else {
                newWidth = width / aspectRationHeightToMaxHeight;
                newHeight = height / aspectRationHeightToMaxHeight;
            }

        } else {
            newWidth = width;
            newHeight = height;
        }

        double ratio = newWidth > newHeight ? (double) newWidth / newHeight : (double) newHeight / newWidth;
        if (ratio > getMaxRatio()) {
            throw new BadImageSizeException(ratio, getMaxRatio());
        }

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);

        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D graphics = bwImg.createGraphics();

        graphics.drawImage(scaledImage, 0, 0, null);
        ImageIO.write(bwImg, "png", new File("out.png"));
        WritableRaster bwRaster = bwImg.getRaster();

        char[][] symbolC = new char[newHeight][newWidth];
        for (int i = 0; i < newHeight; i++) {
            for (int k = 0; k < newWidth; k++) {
                int color = bwRaster.getPixel(k, i, new int[3])[0];
                char c = schema.convert(color);
                symbolC[i][k] = c;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char[] chars : symbolC) {
            for (char aChar : chars) {
                sb.append(aChar);
                sb.append(aChar);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        this.maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.maxHeight = height;
    }


    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;

    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }

    public double getMaxRatio() {
        return maxRatio;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }
}
