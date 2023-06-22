package com.aDeAymee.superMarioBros;
import com.aDeAyme.superMarioBros.controllerTest.camera_test;
import com.aDeAyme.superMarioBros.controllerTest.game_engine_test;
import com.aDeAyme.superMarioBros.modelTest.*;
import com.aDeAyme.superMarioBros.viewTest.UI_manager_test;
import com.aDeAyme.superMarioBros.viewTest.animation_test;
import com.aDeAyme.superMarioBros.viewTest.map_selection_item_test;
import com.aDeAyme.superMarioBros.viewTest.map_selection_test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        bricks_test.class,
        coin_test.class,
        fire_flower_test.class,
        mario_test.class,
        super_mushroom_test.class,
        koopa_troopa_test.class,
        fireball_test.class,
        mario_fire_test.class,
        mario_normal_test.class,
        mario_super_test.class,
        one_up_mushroom_test.class,
        end_flag_test.class,
        boostItem_test.class,
        game_object_test.class,
        map_test.class,
        animation_test.class,
        UI_manager_test.class,
        map_selection_item_test.class,
        map_selection_test.class,
        camera_test.class,
        game_engine_test.class,
})
public class test_suite {
}
