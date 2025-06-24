package medrecords.dao.impl;

import medrecords.dao.IdGenerator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
@Scope("prototype")
public class LongIdGenerator implements IdGenerator<Long> {

    private AtomicLong nextId= new AtomicLong(0L);

    @Override
    public Long getnNextId() {
        return nextId.incrementAndGet();
    }

}
