package Pieces;

/**
 * Created by AngelOfMercy on 14/01/2016.
 */
public class WizardPiece extends Piece{

    public WizardPiece(int OWNER_ID){
        super("Wizard", OWNER_ID);
        super.ATTACK_RANGE_MIN = 3;
        super.ATTACK_RANGE_MAX = 3;
        super.setDefaultHP(2);
    }
}
