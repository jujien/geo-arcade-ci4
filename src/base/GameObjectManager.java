package base;

import game.enemy.Enemy;
import game.player.Player;
import game.square.Square;
import physic.BoxCollider;
import physic.PhysicBody;

import java.awt.*;
import java.util.Vector;

public class GameObjectManager {

    public static GameObjectManager instance = new GameObjectManager(); //chi co duy nhat mot object gameobjectmanager

    private Vector<GameObject> vector = new Vector<>();
    private Vector<GameObject> temp = new Vector<>();

    //chi dc phep khoi tao object trong class nay, ngoai class thi ko dc
    private GameObjectManager() {

    }

    public void runAll() {
        this.vector
                .stream()
                .filter(gameObject -> gameObject.isAlive)
                .forEach(gameObject -> gameObject.run());
        this.vector.addAll(this.temp);
        this.temp.clear();
    }

    public void renderAll(Graphics graphics) {
        this.vector
                .stream()
                .filter(gameObject -> gameObject.isAlive) //loc object dang song
                .forEach(gameObject -> gameObject.render(graphics));
    }

    public Player getPlayer() {
        return (Player) this.vector
                .stream()
                .filter(gameObject -> gameObject instanceof Player) //duyet tat ca phan tu sao cho thoa dien kien truyen (thuc chat true || false) vao thi lay
                .findFirst() //phan tu dau tien
                .orElse(null); // neu ko lay dc tra null
    }
    //Kieu Square
    // Ta biet rang viet cac ham checkCollision cho tat ca cac truong hop va cham -> tra ve nhung con bi va cham: Square, Enmey...
    //Cac ham check collision co dac diem chung: isAlive, check kieu cua gameobject == kieu tra ve, co boxcollider, box collider overlap
    // Viet mot ham duy nhat va dung chung cho tat ca truong hop -> Generic de lam viec day
    //Generic la gi?? Generic thuc ra no giong nhu mot nha may co day chuyen sx co dinh, chi khac nhau nguyen lieu dau vao, sp dau ra
    //dc quyen kiem soat dau vao va dau ra, tuy nhien cai day chuyen luon co dinh.
    //code: kieu tra ve, vao parameter dau vao deu phep thay doi thoai mai, nhung xu ly code ben trong luon co dinh va khong thay doi, luon
    //luon la mau chung (doi khi goi a template)


    //Generic:
    /**
     * Luon luon co mot kieu chung chung (hien tai dat la T), hoan toan dc phep thay doi khi sd
     * ex T: long, String, GameObject ...
     * trong truong hop nay T bi gioi han chi GameObject. boi vi tat ca con bi va cham ta muon lay ra deu ke thua tu GameObject
     * Kieu tra ve dc thoai mai thay doi tuy y
     * Class<T> cls: cho nay la noi ta muon thay doi kieu tra ve la kieu gi. Ex: muon kieu tra ve la Square thi truyen kieu Square vao de lay dc square*/
    public <T extends GameObject> T checkCollision(BoxCollider other, Class<T> cls) { //Square
        return (T) this.vector
                .stream()
                .filter(gameObject -> gameObject.isAlive) //loc con con song
                .filter(gameObject -> cls.isInstance(gameObject)) //kiem tra con gameObject co dung la kieu tra ve mong muon hay ko
                .filter(gameObject -> gameObject instanceof PhysicBody) // kiem tra GameObject co la PhysicBody hay ko ( de loc ra nhung con co BoxCollider)
                .filter(gameObject -> {
                    BoxCollider boxCollider = ((PhysicBody) gameObject).getBoxCollider();//ko null va ta lay dc boxcollider ra
                    return boxCollider.checkCollider(other);
                })
                .findFirst() //lay phan tu dau tien
                .orElse(null); // tra ve null

    }


    /**
     * Khai bao recycle de tai su dung bat ky con gameObject nao minh mong muon.
     * Tiep tuc su dung generic de tai su dung code
     * DK tai su dung:
     * - gameObject da chet
     * - gameObject co kieu minh muon muon*/
    public <T extends GameObject> T recycle(Class<T> cls) {
        T t = (T) this.vector
                .stream()
                .filter(gameObject -> !gameObject.isAlive) // loc ra nhung con da chet
                .filter(gameObject -> cls.isInstance(gameObject)) // kt gameObject co kieu == kieu mong muon hay ko
                .findFirst()
                .orElse(null);
        if (t == null) { //chua ton tai con nay trong vector
            try {
                t = cls.newInstance(); //tao moi mot con
                this.add(t); //add vao vector
                return t;
            } catch (InstantiationException e) {
                e.printStackTrace();
                return null;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }
        t.isAlive = true; // hoi sinh gameObject
        return t;
    }

    public void add(GameObject gameObject) {
        this.temp.add(gameObject);
    }
}
