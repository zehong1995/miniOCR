package recognition;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Output {
	//Excel����ʼ������ӱ�ͷ
    public static void wbInit (String excelPath) {
        @SuppressWarnings("resource")
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet1 = wb.createSheet("Result");
        HSSFRow row0 = sheet1.createRow(0);
        row0.createCell(0).setCellValue("��ҵ����");
        row0.createCell(1).setCellValue("��ҵע���");
        try {
            FileOutputStream fos = new FileOutputStream(excelPath);
            wb.write(fos);
            fos.close();
        } catch (Exception e) {
            
        }
    }
    //ʶ��ɹ������ÿ������
    public static void addItem (String excelPath,String Num,String Name,String picName) {
        HSSFWorkbook wb1 = null;
        try {
            FileInputStream fis = new FileInputStream(excelPath);
            wb1 = new HSSFWorkbook(fis);
        } catch(Exception e) {
            
        }
        HSSFSheet sheet1 = wb1.getSheetAt(0);
        //��ȡ��ǰ�հ����к�
        int rowNum = sheet1.getLastRowNum() +1;
        HSSFRow row = sheet1.createRow(rowNum);
        //д����롢���ơ�ͼƬ����
        row.createCell(0).setCellValue(Name);
        row.createCell(1).setCellValue(Num);
      //  row.createCell(2).setCellValue(picName);
        try {
            FileOutputStream fos = new FileOutputStream(excelPath);
            wb1.write(fos);
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}