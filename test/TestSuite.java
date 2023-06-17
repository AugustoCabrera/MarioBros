package test;
import model.testsModel.FireFlowerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        model.testsModel.BricksTest.class,
        model.testModel.CoinTest.class,
        FireFlowerTest.class,
        model.testsModel.MarioTest.class,
        model.testsModel.SuperMushroomTest.class
})
public class TestSuite {
    //no es necesario un contenido aqui
}
