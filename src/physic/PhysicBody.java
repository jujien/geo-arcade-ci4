package physic;

/**
 * Khai bao interface.
 * Interface la gi? No chi cho phep khai bao ham, nhung ham ko can phai dien giai, viec dien giai thuoc ve class ke thua interface nay
 * Trong Java thi chi cho phep don ke thua. tuc la mot class chi dc phep ke thua tu 1 class cha. interface sinh ra giai quyet cho phep data ke thua
 * thuc chat la 1 class co the ke thua tu nhieu interface khac nhau*/
public interface PhysicBody { // ke thua interface nay bat buoc dien giai ham
    //Chi khai bao ham
    BoxCollider getBoxCollider(); //tra ve box collider cua gameobejct
}
