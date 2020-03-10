package com.nny.Demo.javaBasisLearn;

/**
 * 抛出异常
 */
public class CargoBoat {
    int realContent;
    int maxContent;

    public void setMaxContent(int c){
        maxContent = c;
    }

    public void loading(int m) throws DangerException{
        realContent += m;
        if(realContent > maxContent){
            throw new DangerException();
        }
        System.out.println("目前装载的重量"+realContent);
    }

    /**
     * 捕获异常
     * 处理异常
     * @param args
     */
    public static void main(String args[]){
        CargoBoat ship = new CargoBoat();
        ship.setMaxContent(1000);
        int m = 600;
        try{
            ship.loading(m);
            m = 400;
            ship.loading(m);
            m = 367;
            ship.loading(m);
        }catch(DangerException e){
            System.out.println(e.getMessage());
            return;
        }
        finally {
            System.out.println("起航");
            return;
        }
    }
}
