package Store;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

import static java.util.Collections.max;

public class PQueue<T extends Comparable<T>> implements Iterable {
    ArrayList<T> data;

    public PQueue() {
        this.data = new ArrayList<T>();
    }

    void push(T t){
        data.add(t);
    }
    T pop() throws EmptyQueueException {
        if(data.size() == 0) throw new EmptyQueueException("Üres a sor!");
        T out = max(data);
        data.remove(out);
        return out;
    }
    T top() throws EmptyQueueException {
        if(data.size() == 0) throw new EmptyQueueException("Üres a sor!");
        return max(data);
    }
    int size() { return data.size(); }
    void clear() { data.clear(); }

    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }

    @Override
    public void forEach(Consumer action) {
        data.forEach(action);
    }
}
