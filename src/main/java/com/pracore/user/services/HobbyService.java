package com.pracore.user.services;

import com.pracore.user.models.Hobby;
import com.pracore.user.repositories.HobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class HobbyService {

    @Autowired
    HobbyRepository hobbyRepository;

    public List<Hobby> all() {
        return hobbyRepository.findAll();
    }

    /**
     * /propagation/required
     * Create if needed collaboration
     * Result: both entries did not inserted
     */
    public void transaction1() {
        Hobby hobby = new Hobby();
        hobby.setHobbyName("transaction1");
        hobbyRepository.save(hobby);
        throw new RuntimeException();
    }

    /**
     * /propagation/supported
     * I need support, I cannot start my transaction
     * If called from @transaction method both entries did not insert.
     * If called from non @Transactional method then caller db entry get inserted hobby insertion failed
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction2() {
        Hobby hobby = new Hobby();
        hobby.setHobbyName("transaction2");
        hobbyRepository.save(hobby);
        throw new RuntimeException();
    }

    /**
     * /propagation/unsupported
     * I will remain out of transaction
     * Result: User did not insert Hobby got added
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void transaction3() {
        Hobby hobby = new Hobby();
        hobby.setHobbyName("transaction3");
        hobbyRepository.save(hobby);
    }

    /**
     * /propagation/requiredNew
     * I will listen to myself only, I will create my own transaction,
     * Result: User did not insert Hobby got added
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void transaction4() {
        Hobby hobby = new Hobby();
        hobby.setHobbyName("transaction4");
        hobbyRepository.save(hobby);
    }

    /**
     * /propagation/mandatory
     * I need transaction or I will not run
     * User got created but hobby thrown exception
     * No existing transaction found for transaction marked with propagation 'mandatory'
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void transaction5() {
        Hobby hobby = new Hobby();
        hobby.setHobbyName("transaction5");
        hobbyRepository.save(hobby);
    }

    /**
     * /propagation/never
     * I will escalate my issue, will not allow to run within already given transaction
     * Never should be the first method can not be assigned to this method. so added to caller method
     * both entries got inserted even with exception as this will be out of transaction
     */
    public void transaction6() {
        Hobby hobby = new Hobby();
        hobby.setHobbyName("transaction6");
        hobbyRepository.save(hobby);
        throw new RuntimeException();
    }
}
