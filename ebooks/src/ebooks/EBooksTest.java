package ebooks;

import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EBooksTest {
    public static void main(String[] args) {
        //反序列化
        try {
            //检测是否已经序列化
            File file = new File("xl.txt");
            if (!file.exists()) {
                SerializeReptile();
            }
            Reptile r = DeserializeReptile();
            System.out.println("初始化准备已经完成，正在启动程序");
            GuiShow s = new GuiShow(new Frame());
            ShowBooks showbook = new ShowBooks(1, r, s);
            s.book = showbook;
            s.btn1Listener();
            s.btn2Listener();
            showbook.readChapter(1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ChapterErrorException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //序列化
    private static void SerializeReptile() throws FileNotFoundException,
            IOException, InterruptedException {
        Reptile reptile = new Reptile();
        reptile.installModule();
        reptile.callPython();
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File("./xl.txt")));
        oo.writeObject(reptile);
        System.out.println("Reptile对象序列化成功！");
        oo.close();
    }

    //反序列化
    private static Reptile DeserializeReptile() throws Exception, IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File("./xl.txt")));
        Reptile reptile = (Reptile) ois.readObject();
        System.out.println("Reptile对象反序列化成功！");
        return reptile;
    }
}
