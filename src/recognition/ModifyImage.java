package recognition;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.util.ImageHelper;

public class ModifyImage {
    public static BufferedImage getModifiedImage (String filePath) {
        File file = new File(filePath);
        BufferedImage result = null;
        try {
            BufferedImage bi = ImageIO.read(file);
            //仅识别标准图片 黑底，白水印
	        //判断第一个像素的颜色
	        if(bi.getRGB(0, 0) != 0) {
	            return result;
	        } else {
	            int height = bi.getHeight();
	            int width = bi.getWidth();
	            //去水印
	            for (int i = 0; i < width; ++ i) {
	                for (int j = 0; j < height; ++ j) {
	                    if (bi.getRGB(i, j) == -1710619){
	                        bi.setRGB(i, j, 0);
	                    }
	                }
	            }
	            int newWidth = width / 2;
	            int newHeight = height / 4;
	            result = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                result.createGraphics().drawImage(bi, 0, 0, Color.WHITE, null);
                //灰度
                result = ImageHelper.convertImageToGrayscale(result.getSubimage(0, 0, newWidth, newHeight));
                //裁切并放大10倍
                result = ImageHelper.convertImageToBinary(result);
                //
                result = ImageHelper.getScaledInstance(result, newWidth * 10, newHeight * 10);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}