package recognition;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
public class Recognition {
	static int n = 0;
	static long SUCCES = 0, FAILED = 0;
	public static long start (String inputPath, String outputPath) {
    	Notice ui = new Notice("��ʼʶ��");
    	ui.setVisible(true);
    	File imagePath = new File(inputPath);//����Ŀ¼
    	ArrayList<File> allImages = new ArrayList<>();
    	GetImage.getAllFile(imagePath, allImages);   //��ȡͼƬ�б�allImages
        Output.wbInit(outputPath + "\\result.xls");    //Excel��ʼ��

        allImages.parallelStream().forEach((Image) -> {
        	++ n;
            ui.textField.setText(" ����ʶ��� " + n + " ��ͼƬ");
            BufferedImage modifiedImage = ModifyImage.getModifiedImage(Image.getPath());
            ArrayList<String> result = OCR.ocr(modifiedImage);
            if(result.get(0).equals("1")) {
                String Number = result.get(1);
                String Name = result.get(2);
                Output.addItem(outputPath + "\\result.xls", Number, Name, Image.getName());
                System.out.println(Image.getName() + " OCR Result: \nע��ţ�" + Number + "\n���ƣ�" + Name);
                SUCCES++;
            } else {
                System.out.println(Image.getName() + " ʶ��ʧ�ܡ�\n");
                FAILED++;
            }
        });
        ui.setVisible(false);
        return (SUCCES * 1000000000 + FAILED);
    }
}