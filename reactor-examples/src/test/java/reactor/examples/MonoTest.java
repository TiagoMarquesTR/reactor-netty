package reactor.examples;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoTest {

    @Test
    public void firstMono() {
        Mono.just("A")
                .log()
                .subscribe(s -> System.out.println(s));

    }

    @Test
    public void monoWithConsumer() {
        Mono.just("A")
                .log()
                .subscribe(s -> System.out.println(s));

    }

    @Test
    public void monoWithDoOn() {
        Mono.just("A")
                .log()
                .doOnSubscribe(subs -> System.out.println("Subscribed: " + subs))
                .doOnRequest(subs -> System.out.println("Request: " + subs))
                .doOnSuccess(subs -> System.out.println("Complete: " + subs))
                .subscribe(System.out::println);

    }

    @Test
    public void emptyMono() {
        Mono.empty()
                .log()
                .subscribe(System.out::println);

    }

    @Test
    public void emptyCompleteConsumerMono() {
        Mono.empty()
                .log()
                .subscribe(System.out::println,
                            null,
                        () -> System.out.println("Done")
                );
    }

    @Test
    public void errorRuntimeExcepetionMono() {
        Mono.error(new RuntimeException())
                .log()
                .subscribe();
    }

    @Test
    public void errorConsumerMono() {
        Mono.error(new RuntimeException())
                .log()
                .subscribe(System.out::println,
                        e -> System.out.println("Error: " + e));
    }

    @Test
    public void errorDoOnErrorMono() {
        Mono.error(new RuntimeException())
                .doOnError(e -> System.out.println("Error: " + e))
                .log()
                .subscribe();
    }

    @Test
    public void errorOnErrorResumeMono() {
        Mono.error(new RuntimeException())
                .onErrorResume(e -> {
                    System.out.println("Error: " + e);
                    return Mono.just("B");
                })
                .log()
                .subscribe();
    }
}
