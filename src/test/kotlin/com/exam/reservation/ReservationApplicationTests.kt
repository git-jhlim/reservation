package com.exam.reservation

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ReservationApplicationTests {

    @Test
    fun contextLoads() {

    }

    @Test
    fun paramTest() {
        val temp = TestParam()

        println(temp.getSize())
    }

    data class TestParam (
        val query: String = "111",
        val sort: String ="222",
//        val page: Int,
//        val size: Int,
    ) {
        fun getSize(): Int {
            val params = javaClass.declaredFields
//                println("############## ${params}")
            params.forEach {

                println("############## ${it.name} ${it.get(this)}")
            }
            return params.size
        }
    }
}
