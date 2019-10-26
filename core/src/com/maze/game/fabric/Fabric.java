package com.maze.game.fabric;

import com.maze.game.EventSystem;
import com.maze.game.MainController;
import com.maze.game.MazeEvent;
import com.maze.game.PlayField;
import com.maze.game.entity.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Fabric {
    EventSystem eventSystem;
    PlayField playField;
    File file;
    GameObject temp;
    FileReader reader;
    Integer[][] wallMap;
    List<Instruction> instructions;
    Predicate<Point> humanInEqualPosition =t->{
        return playField.getHuman().getPosition().equals(t);

    };

    public void generateMap(){

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("map.bat")))
        {
            wallMap=(Integer[][])ois.readObject();

            for(int i=0;i<playField.getSize();i++){
                for (int j=0;j<playField.getSize();j++){
                    switch (wallMap[i][j]){
                        case 0:{
                            int a=0;
                        };break;
                        case 1 : {
                            temp=new StaticObject(4, new Point(j,i),0,true,true);
                            playField.addObjectToField((StaticObject)temp);
                        };break;
                        case 2 : {
                            temp=new StaticObject(3, new Point(j,i),0,false,false);
                            playField.addObjectToField((StaticObject)temp);
                        };break;
                        default:  break;
                    }

                }

            }

            instructions=(List<Instruction>)ois.readObject();
            for(Instruction i:instructions){
                switch (i.getType()){
                    case 1 : {
                        temp=new Human(1,i.p,0,5,7,playField);
                        playField.addHuman((Human)temp);
                };break;
                    case 2 : {
                        temp=new Monster(2,i.p,0,10,5,playField);
                        playField.addMonster((Monster)temp);
                    };break;
                    case 5 : {

                        MazeEvent condition=new MazeEvent(0,i.eventPos,humanInEqualPosition);
                        temp=new UsableObject(5,i.p,0,false,true,condition,i.angle);
                        playField.addObjectToField((StaticObject) temp);
                        Consumer<MainController> effect= t->t.setChestFind(true);
                        eventSystem.registrate(condition,effect);

                    };break;
                    case 6 : {


                        MazeEvent condition=new MazeEvent(0,i.eventPos,humanInEqualPosition);
                        temp=new UsableObject(6,i.p,0,false,false,condition,i.angle);
                        playField.addObjectToField((StaticObject) temp);
                        Consumer<MainController> effect= t->t.setExitFind(true);
                        eventSystem.registrate(condition,effect);
                    };break;
                    default:  break;
                }


            }


        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }


    }

    public Fabric(EventSystem eventSystem, PlayField playField) {
        this.eventSystem = eventSystem;
        this.playField = playField;
    }
}
