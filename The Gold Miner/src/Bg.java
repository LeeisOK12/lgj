import java.awt.*;

public class Bg {
    //总分
    static int count = 0;
    //药水数量
    static int waterNum = 3;
    //药水状态,默认F，T表示正在使用
    static boolean waterFlag = false;
    //载入图片
    Image bg = Toolkit.getDefaultToolkit().getImage("imgs/bg.jpg");
    Image bg1 = Toolkit.getDefaultToolkit().getImage("imgs/bg1.jpg");
    Image peo = Toolkit.getDefaultToolkit().getImage("imgs/peo.png");
    Image water = Toolkit.getDefaultToolkit().getImage("imgs/water.png");
    //绘制
    void paintSelf(Graphics g) {
        g.drawImage(bg1,0,0,null);
        g.drawImage(bg,0,200,null);
        g.drawImage(peo,310,50,null);
        drawWord(g,30,Color.black,"积分:" + count,30,150);
        //药水组件
        g.drawImage(water,450,40,null);
        drawWord(g,30,Color.black,"*" + waterNum,510,70);
    }

    //绘制字符串
    public static void drawWord(Graphics g,int size,Color color,String str,int x,int y) {
        g.setColor(color);
        g.setFont(new Font("仿宋",Font.BOLD,size));
        g.drawString(str,x,y);
    }
}