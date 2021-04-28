package jeasy.multi.threaded.services.steps.retrieve.cohort;

@FunctionalInterface
public interface RetrieveCohort<K, T> {
    T retrieveCohort(K v);
}
