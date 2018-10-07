package recognition;
import java.io.File;
import java.util.ArrayList;

public class GetImage {
    //获取所给目录下所有图片的路径
	public static void getAllFile(File fileDir, ArrayList<File> allFiles) {
	    File[] fs =fileDir.listFiles();
	    if(fs == null) {
	    	return;    
	    }
        for (File f : fs) {
            if(f.isDirectory()) {
                GetImage.getAllFile(f,allFiles);
            }
            if(f.isFile()) {
                String name = f.getName().toLowerCase();
                if(name.endsWith(".png") || name.endsWith(".jpg") || 
                    name.endsWith(".jpeg") || name.endsWith(".bmp")) {
                    //将图片路径添加至allFiles
                    allFiles.add(f);
                }
            }
        }
    }
}
