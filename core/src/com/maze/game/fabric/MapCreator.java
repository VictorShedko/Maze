package com.maze.game.fabric;

import com.maze.game.entity.Point;

import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MapCreator {
    static public void createMap(String str)  {

        Integer[][] wall= {
                {2,2,2,2,2,2,2,2,0,2,2,2,2,2,2,2,2,2,2,2,2},
                {2,1,1,1,1,1,2,1,1,2,2,2,1,1,1,1,1,1,1,1,2},
                {2,1,2,1,1,1,2,1,1,2,1,1,1,1,2,1,2,2,1,1,2},
                {2,1,2,2,2,1,2,1,2,1,1,2,1,1,2,1,1,2,1,1,2},
                {2,1,1,1,2,1,1,1,1,1,2,2,2,1,2,1,1,1,1,1,2},
                {2,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,2,2,1,1,2},
                {2,1,2,1,2,1,1,1,2,1,1,2,1,1,2,1,1,1,1,1,2},
                {2,1,2,1,2,1,2,2,2,1,1,2,1,1,1,1,1,1,1,1,2},
                {2,1,1,1,1,1,1,1,1,1,2,1,2,2,2,2,2,2,1,1,2},
                {2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2},
                {2,1,1,1,1,1,1,1,2,2,2,2,2,1,1,2,1,1,1,1,2},
                {2,1,1,1,2,2,1,1,1,2,1,1,1,1,1,2,2,2,1,1,2},
                {2,1,2,1,2,2,1,2,2,2,1,2,2,2,1,1,1,1,1,1,2},
                {2,2,2,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2},
                {2,1,1,1,2,1,1,2,1,1,2,1,1,1,1,2,2,2,2,1,2},
                {2,1,1,1,2,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,2},
                {2,1,2,1,2,1,1,2,1,1,2,1,1,2,1,1,1,1,1,1,2},
                {2,1,2,1,1,1,1,1,1,1,1,1,1,2,1,2,2,2,1,1,2},
                {2,1,2,1,2,1,1,2,1,1,2,1,1,2,1,2,1,1,1,1,2},
                {2,1,1,1,2,1,1,1,0,1,1,1,1,2,1,1,1,1,1,2,2},
                {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
        };
        List<Instruction> list=new ArrayList<>();
        list.add(new Instruction(5,new Point(8,19),false,true,1,new Point(8,18),0));
        list.add(new Instruction(6,new Point(8,0),false,true,2,new Point(8,1),0));
        list.add(new Instruction(1,new Point(2,8),false,true,1,new Point(8,17),0));
        list.add(new Instruction(2,new Point(19,8),false,true,1,new Point(8,17),0));
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(str)))
        {

            oos.writeObject(wall);
            oos.writeObject(list);
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }


    }
}
