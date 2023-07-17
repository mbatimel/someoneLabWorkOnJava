//круглая дыра
class RoundHole {

    private int radius;

    public RoundHole(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }
//тут мы запрашиваем круглую палку, но в замен получаем адаптер который по факту и является круглой палкой, тут уже
    //смотрим как нам удобно, если хотим запихнуть круглую пихаем круглу/, только надо посмотреть как
    //работает наследование, потому что адаптер в своем кострукторе хочет сконструировать круглую палку
    public boolean fits(RoundStick stick) {
        return this.getRadius() >= stick.getRadius();
    }

}
//круглая палка
class RoundStick {

    private int radius;





    public int getRadius() {
        return radius;
    }

}
class SquareStick {

    private int width;

    public SquareStick(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }
}
//просто обьеденяет в данном случае два класса один через наследование, а другой просто обьявляет
class SquareStickAdapter extends RoundStick {

    private SquareStick stick;

    public SquareStickAdapter(SquareStick stick) {
        this.stick = stick;
    }



    public int getRadius() {
        // Вычислить половину диагонали квадратной палки по
        // теореме Пифагора.
        return (int) (stick.getWidth() * Math.sqrt(2) / 2);
    }

}

class Number1 {

    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5);
//      закоментил, чтобы SquareStick не вытался в конструкторе
//      использовать и конструктор для :RoundStick roundStick = new RoundStick(5);
//        System.out.println(hole.fits(roundStick)); // TRUE

        SquareStick smallSquareStick = new SquareStick(5);
        SquareStick largeSquareStick = new SquareStick(10);

        SquareStickAdapter smallSquareStickAdapter = new SquareStickAdapter(smallSquareStick);
        SquareStickAdapter largeSquareStickAdapter = new SquareStickAdapter(largeSquareStick);

        System.out.println(hole.fits(smallSquareStickAdapter)
        ); // TRUE
        System.out.println(hole.fits(largeSquareStickAdapter)
        ); // FALSE
    }

}