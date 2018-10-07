package recognition;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UI extends JFrame {
    private static final long serialVersionUID = 1L;
    JButton inputButton = new JButton("ѡ������Ŀ¼·��");
    JButton outputButton = new JButton("ѡ�����Ŀ¼·��");
    JButton confirmButton = new JButton("ȷ�ϲ�ִ��");
    JLabel lable = new JLabel("��ӭʹ�����깤����Ϣִ��OCR�����");
    Font font1 = new Font("����",0,25);
    Font font2 = new Font("����",0,37);
    String inputPath = "";
    String outputPath = "";
    boolean inputConfirm = false, outputConfirm = false;
    public UI () {
        this.setTitle("���깤����Ϣִ��OCR���");
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
                    System.out.println("��ѡ�������Ŀ¼�ǣ�" + inputPath);
                    inputButton.setText("����·����ѡ��");
                    inputConfirm = true;
                } else if(inputPath.equals("")) {
                    JOptionPane.showMessageDialog(inputButton, "�����ʽ����", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
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
        			System.out.println("��ѡ������Ŀ¼·���ǣ�" + outputPath);
        			outputButton.setText("���·����ѡ��");
        			outputConfirm = true;
        		} else if(inputPath.equals("")) {
                    JOptionPane.showMessageDialog(outputButton, "�����ʽ����", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
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
       				JOptionPane.showMessageDialog(confirmButton, "ʶ�������\n�ɹ���" + SUCCES
       						+ "�ţ�ʧ�ܣ�" + FAILED + "��\nresult.xls����ڣ�\n" + outputPath, "��ʾ",JOptionPane.INFORMATION_MESSAGE);
            	} else if(inputConfirm){
                    JOptionPane.showMessageDialog(inputButton, "��ѡ�����Ŀ¼��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                    return;
				} else if(outputConfirm) {
					JOptionPane.showMessageDialog(inputButton, "��ѡ������Ŀ¼��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                    return;
				} else {
					JOptionPane.showMessageDialog(inputButton, "��ѡ���������Ŀ¼��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
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
    Font font1 = new Font("����", 0, 37);
    Font font2 = new Font("����", 0, 25);
    TextField textField = new TextField("��ʼʶ��");
    public Notice (String str) {
        this.setLayout(null);
        this.setTitle("����ʶ��...");
        this.setBounds(300, 300, 500, 200);
        textField.setBackground(null);
        textField.setEditable(false);
        textField.setFont(font2);
        textField.setBounds(25,40,400,40);
        add(textField);	
    }
}