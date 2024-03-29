package src.entity;

import java.util.Random;

import src.main.GamePanel;

public class NPC_Alan extends Entity{
    public NPC_Alan (GamePanel gp){
        super(gp);
        direction = "down";
        speed = 1;

        getImage();
        setDialouge();
    }

    public void getImage(){

        up1 = setup("res/npc/oldman_up_1");
        up2 = setup("res/npc/oldman_up_2");
        down1 = setup("res/npc/oldman_down_1");
        down2 = setup("res/npc/oldman_down_2");
        left1 = setup("res/npc/oldman_left_1");
        left2 = setup("res/npc/oldman_left_2");
        right1 = setup("res/npc/oldman_right_1");
        right2 = setup("res/npc/oldman_right_2");

    }

    public void setDialouge(){
        dialouges[0] = "Do you want an aderall?";
        dialouges[1] = "Do you want an perky?";
        dialouges[2] = "Charge it to the game";
        dialouges[3] = "got put me here to spite me";
        dialouges[4] = "sprite?";

    }

    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if(i <= 25){
                direction = "up";
            }
            else if(i <= 50 ){
                direction = "down";
            }
            else if(i <= 75){
                direction = "left";
            }
            else{
                direction = "right";
            }
            actionLockCounter = 0;
        }

    }

    public void speak(){
        super.speak();
    }
    
}
