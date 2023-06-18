package com.AdeAyme.superMarioBros;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        com.AdeAyme.superMarioBros.modelTest.BricksTest.class,
        com.AdeAyme.superMarioBros.modelTest.CoinTest.class,
        com.AdeAyme.superMarioBros.modelTest.FireFlowerTest.class,
        com.AdeAyme.superMarioBros.modelTest.MarioTest.class,
        com.AdeAyme.superMarioBros.modelTest.SuperMushroomTest.class
})
public class TestSuite {
}
