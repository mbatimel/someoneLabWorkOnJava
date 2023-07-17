

//implements-служит бля интефейсов и для их большого количества
//extends для наследования классов или абстрактных классов

interface Button {

    void render();
}
//интерфейс который создается для взаимосвязи с другими классами и интерфейсами
interface Dialog {
    void render();
}
class WebButton implements Button {
    public void render() {
        System.out.println("render WebButton");
    }
}
class MobButton implements Button {
    public void render() {
        System.out.println("render MobButton");
    }
}
abstract class AbstractDialog implements Dialog {

    // Фабричный метод
    //абстрактный метод класса который вызывает новый метод
    abstract Button getButton();
    //создан ие обекта класс
    public void render() {
        Button button = getButton();
        button.render();
    }

}
//класс в который наследует абстрактный класс который наследует интерфейм который вызывает метод вызыващий метод
class WebDialog extends AbstractDialog {
    Button getButton() {
        return new WebButton();
    }
}
class MobDialog extends AbstractDialog {
    Button getButton() {
        return new MobButton();
    }
}
class Configuration {
    //переменныте с final не имогут быть наследованы и их нельзя изменять типо const
    public final static   int WEB = 1;
    public final static  int MOB = 2;

    private int platform;

    public int getPlaform() {
        return platform;
    }

    public Configuration setPlatform(int platform) {
        this.platform = platform;
        return this;
    }
    //эти два метода создаются и присвают значение переменной platform
    public static Configuration initWeb() {
        return new Configuration().setPlatform(WEB);
    }

    public static Configuration initMob() {
        return new Configuration().setPlatform(MOB);
    }

}
class FabricMethodService {

    public void exec() {
        //изначально задаем здесь какое значнеие будет присвоено platform 1 или 2
        Configuration configuration =  Configuration.initWeb();

        Dialog dialog ;


        if (configuration.getPlaform() == 1) {

            dialog = new WebDialog();
            dialog.render();
        }
        else if (configuration.getPlaform()==2)
        {
            dialog = new MobDialog();
            dialog.render();
        }
        else{
            throw new RuntimeException (configuration+"Не известный тип платформы");}




    }

}
class Number_2 {

    public static void main(String[] args) {
        FabricMethodService service = new FabricMethodService();
        service.exec();
    }

}
