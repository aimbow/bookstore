package com.xx19.wyb;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.math.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class CheckCodeServlet
 */
@WebServlet("/CheckCode")
public class CheckCodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String tips[] = {"姚明", "肖战", "刘宇", "吕布", "张颜齐", "张文宏", "张文宇", "龚俊", "茅子俊", "牛顿"};
    private static final String dirs[] = {"ym", "xz", "ly", "lb", "zyq", "zwh", "zwy", "gj", "mzj", "nd"};
    private int code[] = new int[3];

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        int type = (int)(Math.random() * tips.length);
        int pos[] = getRandom(8, 3, -1);
        //for(int i=0; i<pos.length; i++)	System.out.print("session:" + pos[i]);
        String code = "";
        for(int i=0; i<pos.length; i++) {
            for(int j=i; j<pos.length; j++) {
                if(pos[i]>pos[j]) {
                    int t = pos[i];
                    pos[i] = pos[j];
                    pos[j] = t;
                }
            }
            code += pos[i];
        }
        System.out.print("session:"+code);
        HttpSession session = request.getSession();
        session.setAttribute("checkcode", code);

        mergeImage(getImageUrl(type, pos), type, request, response);
        //response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    private int[] getRandom(int m, int n, int o) {
        int num[] = new int[n];
        int c1 = -1;
        for(int i=0; i<n; i++) {
            boolean isexist = true;
            while(isexist) {
                isexist = false;
                c1 = (int)(Math.random() * m);
                if(c1 == o) {
                    isexist = true;
                    continue;
                }
                for(int j=0; j<i; j++) {
                    if(num[j]==c1) {
                        isexist = true;
                        break;
                    }
                }
            }
            // System.out.print("" + c1 + ",");
            num[i] = c1;
        }
        return num;
    }


    //选取图片
    private String[] getImageUrl(int type, int[] pos) {
        //保存取到的每一个图片的path，保证图片不会重复
        String imgs[] = new String[8];

        int typeimg[] = getRandom(5, 3, -1);

        int untypeimg[] = getRandom(tips.length, 5, type);

        for (int i = 0; i < pos.length; i++) {
            imgs[pos[i]] = "checkcode/" + dirs[type] + "/" + typeimg[i] + ".jpg";
        }
        int j = 0;
        for (int i = 0; i < imgs.length; i++) {
            if(imgs[i] == null || imgs[i].equals("")) {
                int img = (int)(Math.random() * 5);
                imgs[i] = "checkcode/" + dirs[untypeimg[j]] + "/" + img + ".jpg";
                j++;
            }
            // System.out.println(imgs[i]);
        }

		/*for (int i = 0; i < 5; i++) {
			int j = (int)(Math.random() * 3);
			int k = (int)(Math.random() * 5);
			if( j!= k) {
				String t = imgs[j];
				imgs[j] = imgs[k+3];
				imgs[k+3] = t;
			}
		}*/
        return imgs;
    }

    private void mergeImage(String[] imgurl, int type, HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //
        String baseurl = req.getSession().getServletContext().getRealPath("/");

        BufferedImage mergeImage = new BufferedImage(630, 400, BufferedImage.TYPE_INT_BGR);

        Graphics2D dg = (Graphics2D) mergeImage.createGraphics();
        dg.setColor(Color.WHITE);
        dg.fillRect(0,0,630,400);
        dg.setColor(Color.GRAY);
        Font font = new Font("宋体", Font.BOLD, 14);
        //FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        dg.setFont(font);
        dg.drawString("请点击下图中所有的：", 10, 40);
        dg.setColor(Color.RED);
        font = new Font("黑体", Font.BOLD, 24);
        dg.setFont(font);
        dg.drawString(tips[type], 200, 50);
        dg.setColor(Color.GRAY);
        dg.drawLine(0, 70, 630, 70);


        for (int i = 0; i < imgurl.length; i++) {
            //System.out.println(baseurl + imgurl[i]);
            File image = new File(baseurl + imgurl[i]);

            BufferedImage bufferedImage = ImageIO.read(image);
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            //从图片中读取RGB
            int[] imageBytes = new int[width*height];
            imageBytes = bufferedImage.getRGB(0, 0, width, height, imageBytes, 0, width);
            if ( i < 4) {
                mergeImage.setRGB(i*160, 80, width, height, imageBytes, 0, width);
            } else {
                mergeImage.setRGB((i-4)*160, 240, width, height, imageBytes, 0, width);
            }

        }


        ImageIO.write(mergeImage, "jpg", resp.getOutputStream());
        //ImageIO.write(mergeImage, "jpg", destImage);
    }
}
