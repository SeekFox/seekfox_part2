/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package modele;

/**
 * Classe statique de detection du curseur dans un rectangle
 */
public class Detection {

    public static boolean isPressed(int mouseX, int mouseY, int posX, int posY, int sizeX, int sizeY, boolean isCentered){
        if (!isCentered)
            return mouseX >= (posX) && mouseX <= (posX + sizeX) && mouseY >= (posY) && mouseY <= (posY + sizeY);
        else
            return mouseX >= (posX - sizeX / 2) && mouseX <= (posX + sizeX / 2) && mouseY >= (posY - sizeY / 2) && mouseY <= (posY + sizeY / 2);
    }
}
