package semiproject;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.Socket;

import javax.imageio.ImageIO;

public class ImageResize extends Thread{
	Socket client;
	
	
	
	
	public static void main(String[] args){

        try{
        	for (int i = 1; i <= 20; i++) {
				
        		String imgOriginalPath= "D:\\dev\\eclipse\\workspace\\basic\\src\\Images\\ball"+i+".png";           // ���� �̹��� ���ϸ�
        		String imgTargetPath= "D:\\dev\\eclipse\\workspace\\basic\\src\\Images\\ball"+i+"_2.png";    // �� �̹��� ���ϸ�
        		String imgFormat = "jpg";                             // �� �̹��� ����. jpg, gif ��
        		int newWidth = 100;                                  // ���� �� ����
        		int newHeigt = 100;      
        		
        		// ���� �̹��� ��������
        		Image image = ImageIO.read(new File(imgOriginalPath));
        		
        		// �̹��� ��������
        		// Image.SCALE_DEFAULT : �⺻ �̹��� �����ϸ� �˰��� ���
        		// Image.SCALE_FAST    : �̹��� �ε巯�򺸴� �ӵ� �켱
        		// Image.SCALE_REPLICATE : ReplicateScaleFilter Ŭ������ ��üȭ �� �̹��� ũ�� ���� �˰���
        		// Image.SCALE_SMOOTH  : �ӵ����� �̹��� �ε巯���� �켱
        		// Image.SCALE_AREA_AVERAGING  : ��� �˰��� ���
        		
        		Image resizeImage = image.getScaledInstance(newWidth, newHeigt, Image.SCALE_SMOOTH);
        		
        		// �� �̹���  �����ϱ�
        		BufferedImage newImage = new BufferedImage(newWidth, newHeigt, BufferedImage.TYPE_INT_RGB);
        		Graphics g = newImage.getGraphics();
        		g.drawImage(resizeImage, 0, 0, null);
        		g.dispose();
        		ImageIO.write(newImage, imgFormat, new File(imgTargetPath));
			}
             
        }catch (Exception e){
            e.printStackTrace();
        }
    }


	

}
