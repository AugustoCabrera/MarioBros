package com.AdeAyme.superMarioBros;
import com.AdeAyme.superMarioBros.model.testsModel.FireFlowerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        com.AdeAyme.superMarioBros.model.testsModel.BricksTest.class,
        com.AdeAyme.superMarioBros.model.testsModel.CoinTest.class,
        com.AdeAyme.superMarioBros.model.testsModel.FireFlowerTest.class,
        com.AdeAyme.superMarioBros.model.testsModel.MarioTest.class,
        com.AdeAyme.superMarioBros.model.testsModel.SuperMushroomTest.class
})
public class TestSuite {
}
