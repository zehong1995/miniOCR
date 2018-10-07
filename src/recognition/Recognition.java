package recognition;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
public class Recognition {
	static int n = 0;
	static long SUCCES = 0, FAILED = 0;
	public static long start (String inputPath, String outputPath) {
    	Notice ui = new Notice("开始识别");
    	ui.setVisible(true);
    	File imagePath = new File(inputPath);//输入目录
    	ArrayList<File> allImages = new ArrayList<>();
    	GetImage.getAllFile(imagePath, allImages);   //获取图片列表allImages
        Output.wbInit(outputPath + "\\result.xls");    //Excel初始化

        allImages.parallelStream().forEach((Image) -> {
        	++ n;
            ui.textField.setText(" 正在识别第 " + n + " 张图片");
            BufferedImage modifiedImage = ModifyImage.getModifiedImage(Image.getPath());
            ArrayList<String> result = OCR.ocr(modifiedImage);
            if(result.get(0).equals("1")) {
                String Number = result.get(1);
                String Name = result.get(2);
                Output.addItem(outputPath + "\\result.xls", Number, Name, Image.getName());
                System.out.println(Image.getName() + " OCR Result: \n注册号：" + Number + "\n名称：" + Name);
                SUCCES++;
            } else {
                System.out.println(Image.getName() + " 识别失败。\n");
                FAILED++;
            }
        });
        ui.setVisible(false);
        return (SUCCES * 1000000000 + FAILED);
    }
}