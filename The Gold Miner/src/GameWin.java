import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameWin extends JFrame {

    //存储金块，石块
    List<Object> objectList = new ArrayList<>();
    Bg bg = new Bg();
    Line line = new Line(this);
    {
        //是否可以放置
        boolean isPlace = true;
        for (int i = 0; i < 11; i++) {
            double random = Math.random();
            Gold gold;//存放当前生成的金块
            if (random<0.3) {gold = new GoldMini();}
            else if (random<0.7) {gold = new Gold();}
            else {gold = new GoldPlus();}

            for (Object obj:objectList) {
                if (gold.getRec().intersects(obj.getRec())) {
                    //不可放置，需要重新生成
                    isPlace = false;
                }
            }
            if (isPlace) {objectList.add(gold);}
            else {isPlace=true;i--;}
        }
        for (int i = 0; i < 5; i++) {
            Rock rock = new Rock();
            for (Object obj:objectList) {
                if (rock.getRec().intersects (obj.getRec()))
                    {isPlace = false;}
                }
                if (isPlace) {objectList.add(rock);}
                else {isPlace=true;i--;}
            }
        }

    Image offScreenImage;
    void launch() {
        this.setVisible(true);
        this.setSize(768,1000);
        this.setLocationRelativeTo(null);
        this.setTitle("黄金矿工");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //左右摇摆,点击左键
                if (e.getButton() == 1&&line.state==0)
                {line.state=1;}
                //抓取返回,点击右键
                if (e.getButton() == 3&&line.state==3) {
                    Bg.waterFlag=true;
                    Bg.waterNum--;
                }
            }
        });

        while (true) {
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        offScreenImage = this.createImage(768,1000);
        Graphics gImage = offScreenImage.getGraphics();

        bg.paintSelf(gImage);
        //绘制物体
        for (Object obj:objectList)
        {
            obj.paintSelf(gImage);
        }
        line.paintSelf(gImage);
        g.drawImage(offScreenImage,0,0,null);
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}