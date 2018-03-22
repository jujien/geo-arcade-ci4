package game.player.bullet;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.enemy.Enemy;
import game.enemy.bullet.BulletEnemy;
import game.enemyhard.EnemyHard;
import game.enemyhard.bullet.BulletEnemyHard;
import game.square.Square;
import physic.BoxCollider;
import physic.HitObject;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.ImageRenderer;
import utils.Utils;

public class Bullet extends GameObject implements PhysicBody, HitObject {

    public Vector2D velocity;
    private BoxCollider boxCollider;
    private RunHitObject runHitObject;

    public Bullet() {
        this.renderer = new ImageRenderer("resources/player/player_bullet.png");
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(10, 10);
        this.runHitObject = new RunHitObject(
                Square.class,
                Enemy.class,
                EnemyHard.class,
                BulletEnemy.class,
                BulletEnemyHard.class
        );
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        this.boxCollider.position.set(this.position); //cho box di chuyen theo
        this.runHitObject.run(this);
        //TRuogn hop Square vs Bullet Player
        /**
         * Deu co mot con gameObject di va cham vs con khac (TH nay la BulletPlayer)
         * Co mot hoac nhieu con gameObject bi con gameObject di va cham va cham phai -> Se co mot danh sach cac kieu con gameObject bi va cham(TH nay BulletPlayer la con di va cham thi nhung con bi va cham se la Square, Enemy, EnemyBullet, EnemeyHard, EnemeyBulletHard)
         * Phan xu ly:
         * - Lay box collider cua con gameObject di va cham
         * - Lay ra con gameObject co kieu thuoc danh sach tren
         * - Kiem tra khac null( co dang va cham hay ko)
         * - Neu va cham, thi goi getHit cua con gameObject di va cham va bi va cham
         * -> Generic*/
//        Square square = GameObjectManager.instance.checkCollision(this.boxCollider, Square.class);
//        if (square != null) {
//            ///Xu ly
//            square.getHit();
//            this.getHit();
//        } //Viet mot lan dung dung nhieu cho
//
//        //Truong hop Enemy vs Bullet Player
//        Enemy enemy = GameObjectManager.instance.checkCollision(this.boxCollider, Enemy.class);
//        if (enemy != null) {
//            ///Xu ly
//        }

    }

    @Override
    public void getHit(GameObject gameObject) { // paramter la mot con gameObject bi va cham
        this.isAlive = false; // nhung con chet van con tren vector gameObject -> lan chiem bo nho
        ExplosionBullet explosionBullet = GameObjectManager.instance.recycle(ExplosionBullet.class);
        explosionBullet.create(this.position);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
