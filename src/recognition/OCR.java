/*package recognition;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
public class OCR {
    public static ArrayList<String> ocr (BufferedImage modifiedImage) {
        String isSucceed = "0";
        ITesseract instance = new Tesseract();
        instance.setLanguage("chi_sim");
        String Number = null;
        String Name = null;
        try {
        	//��ȡǰ������
            if(modifiedImage != null) {
                String OCRResult = instance.doOCR(modifiedImage);
                String[] Results = OCRResult.split("\n");
                Number = Results[0].replaceAll(" ", "");
                Name = Results[1].replaceAll(" ", "");
                Name = Name.replaceAll("���۹�˾", "���޹�˾");
                Name = Name.replaceAll("��B�޹�˾", "���޹�˾");
                Number = Number.replaceAll("��", "T");
               // Name = Results[1].replaceAll("��", "��");
              //Ŀǰ�Ὣ���еġ�����ʶ��Ϊ������
                //Number = Number.split("��")[1];
                //Name = Name.split("��")[1];
                isSucceed = "1";
            }
        } catch(TesseractException e) {
            e.printStackTrace();
        }
        ArrayList<String> res = new ArrayList<String>();
        //�洢ʶ�𵽵����Ƽ�����
        res.add(isSucceed);
        res.add(Number);
        res.add(Name);
        return res;
    }
}
*/
package recognition;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCR {
    public static ArrayList<String> ocr(BufferedImage modifiedImage){
        int isSucceed = 0;
        ITesseract instance = new Tesseract();
        instance.setLanguage("chi_sim");
        String Number = null;
        String Name =null;
        try {
            //��ȡǰ������
            if(modifiedImage != null) {
                String OCRResult = instance.doOCR(modifiedImage);
                OCRResult= OCRResult.replaceAll("\n\n","\n");
                String[] Results = OCRResult.split("\n");
                //Ŀǰ�Ὣ���еġ�����ʶ��Ϊ������
                String Replace[] = new String[] {
                    "��", ":", "��", "��"
                };  
                if(Results.length >= 2) {  //Debug
                    for(int i = 0; i < 4; ++ i) {
                    	if(Results[0].split(Replace[i]).length >= 2) {
                            Number = Results[0].split(Replace[i])[1];
                            ++ isSucceed;
                            break;
                    	}
                    }
                    for(int i = 0; i < 4; ++ i) {
                    	if(Results[1].split(Replace[i]).length >= 2) {
                            Name = Results[1].split(Replace[i])[1];
                            ++ isSucceed;
                            break;
                    	}
                    }
                    Number = Number.replaceAll(" ", "");
                    Number = Number.replaceAll("��", "T");
                    Name = Name.replaceAll(" ", "");
                    Name = Name.replaceAll("���۹�˾", "���޹�˾");
                    Name = Name.replaceAll("��B�޹�˾", "���޹�˾");
                }
            }
        } catch(TesseractException e){
            e.printStackTrace();
        }
        ArrayList<String> res = new ArrayList<String>();
        //�洢ʶ�𵽵����Ƽ�����
        res.add(String.valueOf(isSucceed - 1));
        res.add(Number);
        res.add(Name);
        return res;

    }
}
