package com.maze.game;

import com.badlogic.gdx.graphics.glutils.VertexArray;
import com.maze.game.entity.Point;
import com.maze.game.entity.StaticObject;

import java.util.ArrayList;
import java.util.List;

public class RenderControl {
    List<Point> oldVision;
    List<Point> newVision;
    int[][]  crystallineMatrix;
    PlayField playField;
    public void reRender(){
            List<Point> temp=oldVision;
            temp.removeAll(newVision);
            temp.forEach(t->playField.getObjectByKey(t).makeInvisible());
            temp=newVision;
            temp.removeAll(oldVision);
            temp.forEach(t->playField.getObjectByKey(t).makeVisible());
    }
    private int sign (double x) {
        return (x > 0) ? 1 : (x < 0) ? -1 : 0;
        //возвращает 0, если аргумент (x) равен нулю; -1, если x < 0 и 1, если x > 0.
    }

    protected void BresenhamLine (double xstart, double ystart, double xend, double yend)
    /**
     * xstart, ystart - начало;
     * xend, yend - конец;
     * "g.drawLine (x, y, x, y);" используем в качестве "setPixel (x, y);"
     * Можно писать что-нибудь вроде g.fillRect (x, y, 1, 1);
     */
    {
        double x, y, dx, dy, incx, incy, pdx, pdy, es, el, err;

        dx = xend - xstart;//проекция на ось икс
        dy = yend - ystart;//проекция на ось игрек

        incx = sign(dx);
        /*
         * Определяем, в какую сторону нужно будет сдвигаться. Если dx < 0, т.е. отрезок идёт
         * справа налево по иксу, то incx будет равен -1.
         * Это будет использоваться в цикле постороения.
         */
        incy = sign(dy);
        /*
         * Аналогично. Если рисуем отрезок снизу вверх -
         * это будет отрицательный сдвиг для y (иначе - положительный).
         */

        if (dx < 0) dx = -dx;//далее мы будем сравнивать: "if (dx < dy)"
        if (dy < 0) dy = -dy;//поэтому необходимо сделать dx = |dx|; dy = |dy|
        //эти две строчки можно записать и так: dx = Math.abs(dx); dy = Math.abs(dy);

        if (dx > dy)
        //определяем наклон отрезка:
        {
            /*
             * Если dx > dy, то значит отрезок "вытянут" вдоль оси икс, т.е. он скорее длинный, чем высокий.
             * Значит в цикле нужно будет идти по икс (строчка el = dx;), значит "протягивать" прямую по иксу
             * надо в соответствии с тем, слева направо и справа налево она идёт (pdx = incx;), при этом
             * по y сдвиг такой отсутствует.
             */
            pdx = incx;	pdy = 0;
            es = dy;	el = dx;
        }
        else//случай, когда прямая скорее "высокая", чем длинная, т.е. вытянута по оси y
        {
            pdx = 0;	pdy = incy;
            es = dx;	el = dy;//тогда в цикле будем двигаться по y
        }

        x = xstart;
        y = ystart;
        err = el/2;

        for (int t = 0; t < el; t++)//идём по всем точкам, начиная со второй и до последней
        {
            err -= es;
            if (err < 0)
            {
                err += el;
                x += incx;//сдвинуть прямую (сместить вверх или вниз, если цикл проходит по иксам)
                y += incy;//или сместить влево-вправо, если цикл проходит по y
            }
            else
            {
                x += pdx;//продолжить тянуть прямую дальше, т.е. сдвинуть влево или вправо, если
                y += pdy;//цикл идёт по иксу; сдвинуть вверх или вниз, если по y
            }
            Double d1=x;
            Double d2=y;
            if(crystallineMatrix[d1.intValue()][d2.intValue()]==0){
                this.newVision.add(new Point(d1.intValue(),d2.intValue()));
            }else {
                return;
            }

        }
    }

    protected void fillNewVision(int x,int y,int radius){
        for(double angle=0;angle<360;angle+=(0.02/radius)*360.0)
        {
            this.BresenhamLine(x+0.5,y+0.5,x+0.5+radius*Math.sin(angle),y+0.5+radius*Math.cos(angle));
        }
     //   this.BresenhamLine(x+0.5,y+0.5,)
    }

    public RenderControl(PlayField playField) {
        this.playField=playField;
       List<StaticObject> list=new ArrayList<>(this.playField.getGameObjects().values());
       this.oldVision=new ArrayList<>();
       this.newVision=new ArrayList<>();
       list
               .stream()
               .forEach(t->
                        crystallineMatrix[t.getPosition().getX()][t.getPosition().getY()]=t.isCrystallineAsInt());
    }

}
