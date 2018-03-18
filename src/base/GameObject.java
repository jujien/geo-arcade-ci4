package base;

import renderer.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {
    //public BufferedImage image; //chi khai 1 image va hinh anh ko thay doi theo thoi gian
    public Renderer renderer;
    public Vector2D position;
    public boolean isAlive;

    public GameObject() {
        this.position = new Vector2D();
        this.isAlive = true;
    }


    public void run() {

    }

    public void render(Graphics graphics) {
        if (this.renderer != null) {
            this.renderer.render(graphics, this.position);
        }
         // chi rende thoi, con render the nao phu thuoc ma ta cam image render hay animation thong qua thang renderer
        //cung render
//        if (this.image != null) {
//            // chi ve mot buc anh duy nhat
//            graphics.drawImage(this.image, (int)this.position.x - this.image.getWidth() / 2, (int)this.position.y - this.image.getHeight() / 2, null);
//        } -> ko giai quyet dc
    }

    /**
     * Neu toi muon hien thi mot hieu ung dong no do thi giai quyet then nao??
     * Thi thuc ra la ve mot huong ung thuc chat la ve rat nhieu buc anh lien tiep -> Animation
     * Vs cach dung 1 buffered image thi chi tao ra hinh tinh ma ko the tao ra animation dc
     * Khong phai cai nao cung dung animation
     * Se co hai truong hop: ve anh tinh, animtion
     * Dua ra cach giai quyet doi vs render cua gameObject
     * co mot propertype co the thoai mai chuyen doi giua render mot buc anh vs mot animation*/

}
