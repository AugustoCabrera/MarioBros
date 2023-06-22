package com.aDeAyme.superMarioBros.model.prize;
import com.aDeAyme.superMarioBros.model.hero.Mario;
//Interfaz que establece los contextos donde se realizan los cambios de comportamiento en el Patron Strategy
//En este caso, establece el cambio de forma de Mario al tocar los objetos que implementan esta interfaz (FireFlower y SuperMushroom_
public interface MagicObject {
    public void setChangeMarioForm(Mario mario);    //Cambia la forma de Mario al tener contacto con el objeto


}
