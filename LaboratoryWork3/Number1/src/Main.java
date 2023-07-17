public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
    Содержание задания
Стратегия - поведенческий шаблон проектирования, предназначенный для определения семейства алгоритмов, инкапсуляции каждого из них и обеспечения их взаимозаменяемости. Это позволяет выбирать алгоритм путём определения соответствующего класса. Шаблон Strategy позволяет менять выбранный алгоритм независимо от объектов-клиентов, которые его используют.

        В данном примере реализованы разные алгоритмы склеивания списка строк в одну строку. Для реализации данного примера напишем следующие интерфейсы

public interface Strategy {

    @NotNull
    String process(@NotNull List<String> stings);

}
public interface StrategyService {

    String exec(List<String> strings);

    StrategyService setStrategy(@NotNull Strategy strategy);

}
    и следующие классы

public class CommaSeparatedStrategy implements Strategy {

    @NotNull
    @Override
    public String process(
            @NotNull List<String> stings
    ) {
        return StringUtils.join(stings, ",");
    }

}
public class SemicolonSeparatedStrategy implements Strategy {

    @NotNull
    @Override
    public String process(
            @NotNull List<String> stings
    ) {
        return StringUtils.join(stings, ";");
    }

}
public class StrategyServiceImpl implements StrategyService {

    private Strategy strategy;

    public StrategyServiceImpl(
            @NotNull Strategy strategy
    ) {
        this.strategy = strategy;
    }

    @Override
    @NotNull
    public String exec(@NotNull List<String> strings) {
        String result = "";
        if(strings.isEmpty())
            return result;

        return strategy.process(strings);
    }

    public StrategyService setStrategy(
            @NotNull Strategy strategy
    ) {
        this.strategy = strategy;
        return this;
    }
}