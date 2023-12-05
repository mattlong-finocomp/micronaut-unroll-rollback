package com.example

import io.micronaut.test.annotation.TransactionMode
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest(transactionMode = TransactionMode.SINGLE_TRANSACTION)
class UnrollRollbackSingleTransactionSpec extends Specification {

    @Inject
    MyEntityRepository myEntityRepository

    void "unroll rollback broken"() {
        given:
        MyEntity entity = new MyEntity()
        entity.setName(name)

        when:
        myEntityRepository.save(entity)

        then:
        myEntityRepository.count() == 1

        where:
        name << ["foo", "bar"]
    }
}
