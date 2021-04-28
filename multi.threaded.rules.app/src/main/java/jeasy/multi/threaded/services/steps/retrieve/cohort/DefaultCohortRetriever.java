package jeasy.multi.threaded.services.steps.retrieve.cohort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultCohortRetriever<K,T> {

    public RetrieveCohort<K,T> retrieveCohort(K k) {
        return (key) -> {
            return (T)key;
        };
    }
}
