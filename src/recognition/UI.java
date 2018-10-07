package recognition;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UI extends JFrame {
    private static final long serialVersionUID = 1L;
    JButton inputButton = new JButton("选择输入目录路径");
    JButton outputButton = new JButton("选择输出目录路径");
    JButton confirmButton = new JButton("确认并执行");
    JLabel lable = new JLabel("欢迎使用网店工商信息执照OCR软件！");
    Font font1 = new Font("宋体",0,25);
    Font font2 = new Font("宋体",0,37);
    String inputPath = "";
    String outputPath = "";
    boolean inputConfirm = false, outputConfirm = false;
    public UI () {
        this.setTitle("网店工商信息执照OCR软件");
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        lable.setBounds(48,20,1000,50);
        lable.setFont(font1);
        inputButton.setBounds(110, 100, 250, 40);
        inputButton.setFont(font1);
        outputButton.setBounds(110, 180, 250, 40);
        outputButton.setFont(font1);
        confirmButton.setBounds(110, 260, 250, 40);
        confirmButton.setFont(font1);     
        inputButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked (MouseEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = chooser.showSaveDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    inputPath = chooser.getSelectedFile().getPath();
                    System.out.println("你选择的输入目录是：" + inputPath);
                    inputButton.setText("输入路径已选择");
                    inputConfirm = true;
                } else if(inputPath.equals("")) {
                    JOptionPane.showMessageDialog(inputButton, "输入格式有误！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    return;
				}
            }
		});
        outputButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked (MouseEvent e) {
                JFileChooser chooser = new JFileChooser();
        		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        		int returnVal = chooser.showSaveDialog(null);
        		if (returnVal == JFileChooser.APPROVE_OPTION) {
        			outputPath = chooser.getSelectedFile().getPath();
        			System.out.println("你选择的输出目录路径是：" + outputPath);
        			outputButton.setText("输出路径已选择");
        			outputConfirm = true;
        		} else if(inputPath.equals("")) {
                    JOptionPane.showMessageDialog(outputButton, "输入格式有误！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    return;
				}
            }
		});
        confirmButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	if(inputConfirm && outputConfirm) {
            		long hashCode = Recognition.start(inputPath, outputPath);
            		long SUCCES = hashCode / 1000000000;
            		long FAILED = hashCode % 1000000000;
       				JOptionPane.showMessageDialog(confirmButton, "识别结束！\n成功：" + SUCCES
       						+ "张，失败：" + FAILED + "张\nresult.xls存放在：\n" + outputPath, "提示",JOptionPane.INFORMATION_MESSAGE);
            	} else if(inputConfirm){
                    JOptionPane.showMessageDialog(inputButton, "请选择输出目录！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    return;
				} else if(outputConfirm) {
					JOptionPane.showMessageDialog(inputButton, "请选择输入目录！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    return;
				} else {
					JOptionPane.showMessageDialog(inputButton, "请选择输入输出目录！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    return;
				}
   			}
   		});
		this.add(inputButton);
		this.add(outputButton);
		this.add(confirmButton);
		this.add(lable);
	}
    public static void main (String[] args) {
        UI ui = new UI();
        ui.setVisible(true);
        ui.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
class Notice extends JFrame {
    private static final long serialVersionUID = 1L;
    Font font1 = new Font("宋体", 0, 37);
    Font font2 = new Font("宋体", 0, 25);
    TextField textField = new TextField("开始识别");
    public Notice (String str) {
        this.setLayout(null);
        this.setTitle("正在识别...");
        this.setBounds(300, 300, 500, 200);
        textField.setBackground(null);
        textField.setEditable(false);
        textField.setFont(font2);
        textField.setBounds(25,40,400,40);
        add(textField);	
    }
}