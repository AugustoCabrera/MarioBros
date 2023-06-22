package com.aDeAymee.superMarioBros;
import com.aDeAyme.superMarioBros.controllerTest.CameraTest;
import com.aDeAyme.superMarioBros.modelTest.*;
import com.aDeAyme.superMarioBros.viewTest.UIManagerTest;
import com.aDeAyme.superMarioBros.viewTest.AnimationTest;
import com.aDeAyme.superMarioBros.viewTest.MapSelectionItemTest;
import com.aDeAyme.superMarioBros.viewTest.MapSelectionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BricksTest.class,
        CoinTest.class,
        FireFlowerTest.class,
        MarioTest.class,
        SuperMushroomTest.class,
        KoopaTroopaTest.class,
        FireballTest.class,
        MarioFireTest.class,
        MarioNormalTest.class,
        MarioSuperTest.class,
        OneUpMushroomTest.class,
        EndFlagTest.class,
        BoostItemTest.class,
        GameObjectTest.class,
        MapTest.class,
        AnimationTest.class,
        UIManagerTest.class,
        MapSelectionItemTest.class,
        MapSelectionTest.class,
        CameraTest.class,
})
public class TestSuite {
}
