package com.maze.game.creation;

import com.maze.game.gamemodel.controlers.EventSystem;
import com.maze.game.gamemodel.controlers.MainController;
import com.maze.game.gamemodel.entity.MazeEvent;
import com.maze.game.gamemodel.PlayField;
import com.maze.game.gamemodel.entity.*;
import com.maze.game.gamemodel.entity.events.PlayerStateEventEffeact;
import com.maze.game.gamemodel.entity.events.SystemStateEventEffeact;
import com.maze.game.gamemodel.entity.playrs.Human;
import com.maze.game.gamemodel.entity.playrs.Monster;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Factory {
    EventSystem eventSystem;
    PlayField playField;
    File file;
    GameObject temp;
    FileReader reader;
    Integer[][] wallMap;
    List<Instruction> instructions;
    MainController mainController;
    Predicate<Point> humanInEqualPosition = t -> {
        return playField.getHuman().getPosition().equals(t);

    };

    public void generateMap(int side) {
        int startVisible=0;
        int startVisibleUsable=0;
        if(side==1){
            startVisible=1;
            startVisibleUsable=0;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("map.bat"))) {
            wallMap = (Integer[][]) ois.readObject();

            for (int i = 0; i < playField.getSize(); i++) {
                for (int j = 0; j < playField.getSize(); j++) {
                    switch (wallMap[i][j]) {
                        case 0: {

                        }
                        break;
                        case 1: {
                            temp = new StaticObject(4, new Point(j, i), startVisible, true, true);
                            playField.addObjectToField((StaticObject) temp);
                        }
                        break;
                        case 2: {
                            temp = new StaticObject(3, new Point(j, i), startVisible, false, false);
                            playField.addObjectToField((StaticObject) temp);
                        }
                        break;
                        default:
                            break;
                    }

                }

            }

            instructions = (List<Instruction>) ois.readObject();
            for (Instruction i : instructions) {
                switch (i.getType()) {
                    case 1: {
                        temp = new Human(1, i.p, 0, 5, 5, playField);
                        playField.addHuman((Human) temp);
                        ((Human) temp).refreshSteps();
                    }
                    ;
                    break;
                    case 2: {
                        temp = new Monster(2, i.p, 0, 10, 4, playField);
                        playField.addMonster((Monster) temp);
                    }
                    ;
                    break;
                    case 5: {

                        MazeEvent condition = new MazeEvent(0, i.eventPos, humanInEqualPosition);
                        temp = new UsableObject(5, i.p, startVisibleUsable, false, true, condition, i.angle);
                        playField.addObjectToField((StaticObject) temp);
                        Consumer<MainController> effect = t -> t.setChestFind(true);
                        SystemStateEventEffeact stateEffeact=new SystemStateEventEffeact(mainController,effect);
                        eventSystem.registrate(condition,  stateEffeact);

                    }
                    ;
                    break;
                    case 6: {


                        MazeEvent condition = new MazeEvent(0, i.eventPos, humanInEqualPosition);
                        temp = new UsableObject(6, i.p, startVisibleUsable, false, false, condition, i.angle);
                        playField.addObjectToField((StaticObject) temp);
                        Consumer<MainController> effect = t -> t.setExitFind(true);
                        SystemStateEventEffeact stateEffeact=new SystemStateEventEffeact(mainController,effect);
                        eventSystem.registrate(condition, stateEffeact);
                    }
                    ;
                    break;
                    case 7: {


                        MazeEvent condition = new MazeEvent(0, i.eventPos, humanInEqualPosition);
                        temp = new UsableObject(7, i.p, startVisibleUsable, true, true, condition, i.angle);
                        playField.addObjectToField((StaticObject) temp);
                        Consumer<Human> effect = Human::makeForceInvisible;
                        PlayerStateEventEffeact stateEffeact=new PlayerStateEventEffeact(playField.getHuman(),effect);
                        eventSystem.registrate(condition, stateEffeact);
                    }
                    break;
                    default:
                        break;
                }


            }


        } catch (Exception ex) {

            System.err.println(ex.getMessage());
        }


    }

    public Factory(EventSystem eventSystem, PlayField playField, MainController mainController) {
        this.eventSystem = eventSystem;
        this.playField = playField;
        this.mainController=mainController;
    }
}
