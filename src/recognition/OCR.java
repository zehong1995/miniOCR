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
        	//读取前两行字
            if(modifiedImage != null) {
                String OCRResult = instance.doOCR(modifiedImage);
                String[] Results = OCRResult.split("\n");
                Number = Results[0].replaceAll(" ", "");
                Name = Results[1].replaceAll(" ", "");
                Name = Name.replaceAll("有眼公司", "有限公司");
                Name = Name.replaceAll("有B艮公司", "有限公司");
                Number = Number.replaceAll("丁", "T");
               // Name = Results[1].replaceAll("…", "二");
              //目前会将所有的“：”识别为“二”
                //Number = Number.split("二")[1];
                //Name = Name.split("二")[1];
                isSucceed = "1";
            }
        } catch(TesseractException e) {
            e.printStackTrace();
        }
        ArrayList<String> res = new ArrayList<String>();
        //存储识别到的名称及号码
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
            //读取前两行字
            if(modifiedImage != null) {
                String OCRResult = instance.doOCR(modifiedImage);
                OCRResult= OCRResult.replaceAll("\n\n","\n");
                String[] Results = OCRResult.split("\n");
                //目前会将所有的“：”识别为“二”
                String Replace[] = new String[] {
                    "二", ":", "：", "…"
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
                    Number = Number.replaceAll("丁", "T");
                    Name = Name.replaceAll(" ", "");
                    Name = Name.replaceAll("有眼公司", "有限公司");
                    Name = Name.replaceAll("有B艮公司", "有限公司");
                }
            }
        } catch(TesseractException e){
            e.printStackTrace();
        }
        ArrayList<String> res = new ArrayList<String>();
        //存储识别到的名称及号码
        res.add(String.valueOf(isSucceed - 1));
        res.add(Number);
        res.add(Name);
        return res;

    }
}
