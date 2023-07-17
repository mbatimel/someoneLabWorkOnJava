public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
public class Request {

    private String inputStream;

    private final Map<String, String> headers = new HashMap<>();

    private String method;

    private String session;

    private String path;

    public String getInputStream() {
        return inputStream;
    }

    public Request setInputStream(String inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public Request setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getSession() {
        return session;
    }

    public Request setSession(String session) {
        this.session = session;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Request setPath(String path) {
        this.path = path;
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

}
public interface ChainHandler {

    void process(@NotNull Request request);

    ChainHandler next();

    ChainHandler next(ChainHandler chainHandler);

}
public abstract class AbstractChainHandler implements ChainHandler {

    private ChainHandler chainHandler;

    @Override
    public void process(
            @NotNull Request request
    ) {
        validate(request);
        handle(request);
        if(Objects.nonNull(next()))
            next().process(request);
    }

    @Override
    public ChainHandler next() {
        return chainHandler;
    }

    @Override
    public ChainHandler next(ChainHandler chainHandler) {
        this.chainHandler = chainHandler;
        return chainHandler;
    }

    private void validate(
            @NotNull Request request
    ) {
        if (Objects.isNull(request))
            throw new RuntimeException("request must not be null");
    }

    abstract void handle(@NotNull Request request);

}
public class HeadersChainHandler extends AbstractChainHandler {

    private static final String DELIMITER = ":";

    @Override
    void handle(
            @NotNull Request request
    ) {
        String[] headers = StringUtils.split(request.getInputStream(), "\n");

        for(String header : headers) {
            if (header.isEmpty())
                continue;

            String[] arr = StringUtils.split(header, DELIMITER);
            if(0 == arr.length)
                continue;

            request.getHeaders().put(arr[0], arr[1]);
        }
    }

}
public class MethodChainHandler extends AbstractChainHandler {

    private static final String KEY = "method";

    @Override
    void handle(
            @NotNull Request request
    ) {
        if(request.getHeaders().containsKey(KEY))
            request.setMethod(request.getHeaders().get(KEY));
    }

}
public class PathChainHandler extends AbstractChainHandler {

    private static final String KEY = "path";

    @Override
    public void handle(
            @NotNull Request request
    ) {
        if(request.getHeaders().containsKey(KEY))
            request.setPath(request.getHeaders().get(KEY));
    }

}
public class SessionChainHandler extends AbstractChainHandler {

    private static final String KEY = "session";

    @Override
    public void handle(
            @NotNull Request request
    ) {
        if(request.getHeaders().containsKey(KEY))
            request.setSession(request.getHeaders().get(KEY));
    }

}
public class ChainService {

    public Request exec(String inputStream) {
        Request request = new Request();
        request.setInputStream(inputStream);

        ChainHandler handler = new HeadersChainHandler();
        handler.next(new MethodChainHandler())
                .next(new PathChainHandler())
                .next(new SessionChainHandler());

        handler.process(request);

        return request;
    }

}