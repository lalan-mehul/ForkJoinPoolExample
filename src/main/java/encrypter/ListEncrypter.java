package encrypter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class ListEncrypter extends RecursiveTask<List<String>> {
    private final List<String> input;

    private static final int THRESHOLD = 20;

    public ListEncrypter(List<String> input) {
        this.input = input;
    }

    @Override
    protected List<String> compute() {
        if (input.size() > THRESHOLD) {
            int midpoint = input.size() / 2;
            ListEncrypter firstHalf = new ListEncrypter(input.subList(0, midpoint));
            ListEncrypter secondHalf = new ListEncrypter(input.subList(midpoint, input.size()));
            ForkJoinTask.invokeAll(firstHalf,secondHalf);

            List<String> result = new ArrayList<>();
            result.addAll(firstHalf.join());
            result.addAll(secondHalf.join());
            return result;
        } else {
            return processing(input);
        }
    }

    private List<String> processing(List<String> input) {
        return input.stream().map(Encrypter::encrypt).collect(Collectors.toList());
    }
}
