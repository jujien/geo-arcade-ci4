package physic;

import base.GameObject;
import base.GameObjectManager;

import java.util.Arrays;
import java.util.List;

/**
 * Created by huynq on 3/17/18.
 */
public class RunHitObject<O extends GameObject & PhysicBody & HitObject> {

    private List<Class<O>> objects; // danh sach cac kieu gameObject bi va cham

    public RunHitObject(Class<O>... objects) { // no du mot mang
        this.objects = Arrays.asList(objects);
    }

    /**
     * Parameter dau vao se la con gameObject di va cham co nhung ra buoc sau:
     * - Luon luon la gameObject
     * - No phai co BoxCollider hay no cach khac la luon la PhysicBody
     * - no luon la HitObject. de goi dc getHit cua tuong con khi va cham*/
    public <T extends GameObject & PhysicBody & HitObject> void run(T gameObject) { //object di va cham
        BoxCollider boxCollider = gameObject.getBoxCollider(); // Lay BoxCollider
        this.objects.forEach(oClass -> { // duyet qua tat ca cac kieu
            O object = GameObjectManager.instance.checkCollision(boxCollider, oClass); // tuong kieu mot lay ra con gameObject co the va cham
            if (object != null) { //kiem tra khac null
                //Se xay ra van de con object di va cham cha biet con bi va cham la ai && con bi va cham cha biet con va cham phai no la i
                object.getHit(gameObject); //doi voi con di va cham. khi goi getHit thi truyen parameter la con bi va cham vao de no biet no dang va cham vs ai
                gameObject.getHit(object); // doi vs con bi va cham, khi goi getHit thi truyen parameter la con di va cham de no biet no dang bi con nao vao cham phai
            }
        });
    }
}
