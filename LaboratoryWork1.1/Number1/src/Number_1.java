

class Connection {
    //переменная по которой проверяется единство обьекта
    private static Connection instance;

    private Connection() {}
    //вот этот метод проверяет на единство класс connection и при случае повторного создания класса,
// вернет сужествующий экзепляр
    //сколько бы мы не старались создать экзепляров классов, у нас всегда будет возвращаться один и тот же класс
    // при создании получится один классс, можно провенрить созданием приватной перменной count  и подставив его после
    //if он выдаст 1 потому что вернулся в connection2 connection1
    public static Connection getInstance() {
        if(null != instance)
            return instance;

        instance = new Connection();
        return instance;
    }


}

class SingletonService {
    //сервис который запускается пользователем
    //данный сервис пытается создать 2 обьекьа патерна одиночки, но в ответе им выдвется всегда один
    public void exec() {
        Connection connection1 = Connection.getInstance();
        Connection connection2 = Connection.getInstance();


        System.out.println(connection1.toString());
        System.out.println(connection2.toString());

    }

}

public class Number_1 {
    public static void main(String[] args) {

        SingletonService service = new SingletonService();
        service.exec();

    }
}