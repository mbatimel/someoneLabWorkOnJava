import java.awt.image.RasterFormatException;

//для точности наша абстрактная фабрика используется как фабрика которая включает в себя все фабрики методы
//у нас есть 4 интерфейса которые мы должы реализовать в фабрике
interface Button {
    void render();
    void onClick();
}
interface Checkbox {
    void render();
    Checkbox state(boolean state);
    boolean state();
}
interface Input {
    void render();
    Input value(String value);
    String value();
}
//наша абстрактная фабрика, которая содержит в себе создание элементов
interface FormElementFactory {
    Button createButton();
    Checkbox createCheckbox();
    Input createInput();
}
//присваиваем данным интерфейчас какие то действия для одно из форм фабрики web
class WebButton implements Button {
    public void render() {
        System.out.println("render WebButton");
    }

    public void onClick() {
        System.out.println("Event on click WebButton");
    }
}
class WebCheckbox implements Checkbox {

    private boolean state;

    public void render() {
        System.out.println(
                String.format("render WebCheckbox. With state %s", state)
        );
    }

    public boolean state() {
        return state;
    }

    public Checkbox state(boolean state) {
        this.state = state;
        return this;
    }

}
class WebInput implements Input {

    private String value;

    public Input value(String value) {
        this.value = value;
        return this;
    }

    public String value() {
        return value;
    }

    public void render() {
        System.out.println(
                String.format("render WebInput. With value %s", value)
        );
    }

}
//одна из форм реализации фабрики web
class WebFormElementFactory implements FormElementFactory {

    public Button createButton() {
        return new WebButton();
    }

    public Checkbox createCheckbox() {
        return new WebCheckbox();
    }

    public Input createInput() {
        return new WebInput();
    }

}
//действия для другой реализации формы фабрики mob
class MobButton implements Button {
    public void render() {
        System.out.println("render MobButton");
    }

    public void onClick() {
        System.out.println("Event on click MobButton");
    }
}
class MobCheckbox implements Checkbox {

    private boolean state;

    public void render() {
        System.out.println(
                String.format("render MobCheckbox. With state %s", state)
        );
    }

    public boolean state() {
        return state;
    }

    public Checkbox state(boolean state) {
        this.state = state;
        return this;
    }

}
class MobInput implements Input {

    private String value;

    public Input value(String value) {
        this.value = value;
        return  this;
    }

    public String value() {
        return value;
    }

    public void render() {
        System.out.println(
                String.format("render MobInput. With value %s", value)
        );
    }

}
//пругая конфигурация фабрики mob
class MobFormElementFactory implements FormElementFactory {

    public Button createButton() {
        return new MobButton();
    }

    public Checkbox createCheckbox() {
        return new MobCheckbox();
    }

    public Input createInput() {
        return new MobInput();
    }

}
//класс в ктором мы создаем комфигурацию фабрики
class Configuration {

    public final static  int WEB = 1;
    public final static  int MOB = 2;

    private int platform;

    public int getPlaform() {
        return platform;
    }

    public Configuration setPlatform(int platform) {
        this.platform = platform;
        return this;
    }

    public static Configuration initWeb() {
        return new Configuration().setPlatform(WEB);
    }

    public static Configuration initMob() {
        return new Configuration().setPlatform(MOB);
    }

}
class AbstractFabricService {
    // тут мы выбираем какую фабрику мы будем вызывать
    private FormElementFactory initFactory() {
        Configuration configuration = Configuration.initMob();

        FormElementFactory factory;
        if(configuration.getPlaform()==1) {factory = new WebFormElementFactory();}
        else if(configuration.getPlaform()==2)
        {factory = new MobFormElementFactory();}
        else{throw new RasterFormatException("Не известный тип платформы");}
        return factory;

    }

    private void renderForm(FormElementFactory factory) {
        Input input = factory.createInput();
        Button button = factory.createButton();
        input.value("Тестовое значение поля ввода данных");
        input.render();
        button.render();
    }

    public void exec() {
        renderForm(initFactory());
    }

}
class Number_3 {

    public static void main(String[] args) {
        AbstractFabricService service = new AbstractFabricService();
        service.exec();
    }

}
