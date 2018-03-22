package game.enemy;

import action.Action;
import action.ActionAdapter;
import action.SequenceAction;
import action.WaitAction;
import base.GameObject;
import base.GameObjectManager;
import game.enemyhard.EnemyHard;
import game.enemyhard.EnemyHardMove;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemySqawner extends GameObject {
    private Random random;

    public EnemySqawner() {
        this.random = new Random();
    }


    public void create() {
        Action waitStart = new WaitAction(600);
        Action create = new ActionAdapter() {
            @Override
            public boolean run(GameObject owner) {
                Enemy enemy = GameObjectManager.instance.recycle(Enemy.class);
                if (random.nextInt(2) == 0) {
                    enemy.direction = Direction.LEFT_TO_RIGHT;
                } else  {
                    enemy.direction = Direction.UP_DOWN;
                }
                enemy.position.set(random.nextInt(400), 20);
                enemy.velocity.set(random.nextInt(3) + 1, random.nextInt(2) + 1);
                return true;
            }
        };

        this.add(
                new SequenceAction(
                        waitStart,
                        new ActionAdapter() {
                            private int count = 0;
                            private Action sequence = new SequenceAction(
                                    create, new WaitAction(300)
                            );
                            @Override
                            public boolean run(GameObject owner) {
                                if (this.count == 5) {
                                    return true;
                                } else {
                                    if (this.sequence.run(owner)) {
                                        this.sequence.reset();
                                        this.count += 1;
                                    }
                                    return false;
                                }
                            }
                        }
                )
        );
    }

    public void createHard() {
        List<EnemyHard> enemyHards = new ArrayList<>();
        Action create = new ActionAdapter() {
            @Override
            public boolean run(GameObject owner) {
                EnemyHard enemyHard = GameObjectManager.instance.recycle(EnemyHard.class);
                enemyHard.position.set(random.nextInt(400), 0);
                enemyHard.enemyHardMove = new EnemyHardMove();
                enemyHard.enemyHardMove.velocity.set(random.nextInt(2) + 1, 0);
                enemyHards.add(enemyHard);
                return true;
            }
        };
        this.add(
                new SequenceAction(
                        new WaitAction(2500),
                        new ActionAdapter() {
                            private int count = 0;
                            @Override
                            public boolean run(GameObject owner) {
                                enemyHards.removeIf(enemyHard -> !enemyHard.isAlive);
                                if (enemyHards.isEmpty()) {
                                    if (this.count == 5) {
                                        return true;
                                    } else {
                                        create.run(owner);
                                        create.reset();
                                        this.count += 1;
                                    }
                                }
                                return enemyHards.isEmpty() ;
                            }
                        }
                )
        );
    }

}
