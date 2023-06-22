package com.aDeAymee.superMarioBros;
import com.aDeAyme.superMarioBros.controllerTest.camera_test;
import com.aDeAyme.superMarioBros.controllerTest.gameEngineTest;
import com.aDeAyme.superMarioBros.modelTest.*;
import com.aDeAyme.superMarioBros.viewTest.UI_manager_test;
import com.aDeAyme.superMarioBros.viewTest.animation_test;
import com.aDeAyme.superMarioBros.viewTest.MapSelectionItemTest;
import com.aDeAyme.superMarioBros.viewTest.MapSelectionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        bricks_test.class,
        coin_test.class,
        FireFlowerTest.class,
        mario_test.class,
        SuperMushroomTest.class,
        KoopaTroopaTest.class,
        fireball_test.class,
        MarioFireTest.class,
        MarioNormalTest.class,
        MarioSuperTest.class,
        OneUpMushroomTest.class,
        EndFlagTest.class,
        boostItem_test.class,
        GameObjectTest.class,
        map_test.class,
        animation_test.class,
        UI_manager_test.class,
        MapSelectionItemTest.class,
        MapSelectionTest.class,
        camera_test.class,
        gameEngineTest.class,
})
public class test_suite {
}
