package io.github.forezp.context;

public class SwrContextHolder {

    static  ThreadLocal<SwrCoreContext> CONTEXTS = new ThreadLocal();

    public SwrContextHolder() {

    }

    public static SwrCoreContext get() {
        if (null == CONTEXTS.get()) {
            CONTEXTS.set(new SwrCoreContext());
        }
        return CONTEXTS.get();
    }

    public static void set(SwrCoreContext swrCoreContext) {
        CONTEXTS.set(swrCoreContext);
    }

    public static void remove() {
        CONTEXTS.remove();
    }

}
